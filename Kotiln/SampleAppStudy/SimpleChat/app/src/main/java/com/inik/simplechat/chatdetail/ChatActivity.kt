package com.inik.simplechat.chatdetail

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.database
import com.inik.simplechat.Key
import com.inik.simplechat.R
import com.inik.simplechat.databinding.ActivityChatDetailBinding
import com.inik.simplechat.userlist.UserItem
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttp
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import org.json.JSONObject
import java.io.IOException
import java.io.InputStream
import java.util.Collections

class ChatActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChatDetailBinding
    private lateinit var chatAdapter: ChatAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager

    private var myUserId: String = ""
    private var chatRoomId: String = ""
    private var otherUserId: String = ""
    private var myUserName: String = ""
    private var otherUserFcmToken: String = ""
    private var otherUserName: String = ""
    private var isInit = false
    private var message: String = ""
    private var serverKey: String = ""


    private val chatItemList = mutableListOf<ChatItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.e("채팅", "열림")
        serverKey = getString(R.string.fcm_server_key)

        chatAdapter = ChatAdapter()
        chatRoomId = intent.getStringExtra(EXTRA_CHAT_ROOM_ID) ?: return
        otherUserId = intent.getStringExtra(EXTRA_OTHER_USER_ID) ?: return
        Log.e("룸아이디 확인", "$chatRoomId")
        Log.e("유저 확인", "$otherUserId")
        myUserId = Firebase.auth.currentUser?.uid ?: ""
        linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)



        binding.chatRoomRecyclerView.apply {
            layoutManager = linearLayoutManager
            adapter = chatAdapter
        }

        chatAdapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                super.onItemRangeInserted(positionStart, itemCount)

                linearLayoutManager.smoothScrollToPosition(
                    binding.chatRoomRecyclerView,
                    null,
                    chatAdapter.itemCount
                )

            }
        })



        Firebase.database.reference.child(Key.DB_USERS).child(myUserId).get()
            .addOnSuccessListener {
                val myUserItem = it.getValue(UserItem::class.java)
                myUserName = myUserItem?.username ?: ""

                getOtherUserData()
            }




        binding.sendButton.setOnClickListener {
            message = binding.messageEditText.text.toString()
            if (isInit == false) {
                return@setOnClickListener
            }
            if (message.isEmpty()) {
                Toast.makeText(applicationContext, "빈 메세지를 보낼수 없습니다", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val newChatItem = ChatItem(
                message = message,
                userId = myUserId
            )
            Firebase.database.reference.child(Key.DB_CHATS).child(chatRoomId).push().apply {
                newChatItem.chatId = key
                setValue(newChatItem)
            }
            val updates: MutableMap<String, Any> = hashMapOf(
                "${Key.DB_CHAT_ROOMS}/$myUserId/$otherUserId/lastMessage" to message,
                "${Key.DB_CHAT_ROOMS}/$otherUserId/$myUserId/lastMessage" to message,
                "${Key.DB_CHAT_ROOMS}/$otherUserId/$myUserId/chatRoomId" to chatRoomId,
                "${Key.DB_CHAT_ROOMS}/$otherUserId/$myUserId/otherUserId" to myUserId,
                "${Key.DB_CHAT_ROOMS}/$otherUserId/$myUserId/otherUserName" to myUserName,
            )


            Firebase.database.reference.updateChildren(updates)

            sendFcm()

        }
    }

    private fun getOtherUserData() {
        Firebase.database.reference.child(Key.DB_USERS).child(otherUserId).get()
            .addOnSuccessListener {
                val otherUserItem = it.getValue(UserItem::class.java)
                otherUserFcmToken = otherUserItem?.fcmToken.orEmpty()
                otherUserName = otherUserItem?.username.orEmpty()
                chatAdapter.otherUserItem = otherUserItem

                isInit = true

                getChatData()
            }
    }

    private fun getChatData() {
        Firebase.database.reference.child(Key.DB_CHATS).child(chatRoomId).addChildEventListener(
            object : ChildEventListener {
                override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                    val chatItem = snapshot.getValue(ChatItem::class.java)
                    chatItem ?: return

                    chatItemList.add(chatItem)
                    chatAdapter.submitList(chatItemList.toMutableList())
                }

                override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {}
                override fun onChildRemoved(snapshot: DataSnapshot) {}
                override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {}
                override fun onCancelled(error: DatabaseError) {}

            }
        )
    }

    private fun refreshServerKey() {
        Log.e("FCM", "리프레시 시도")
        val serviceAccountStream: InputStream = resources.openRawResource(R.raw.fcmkey)
        val credentials = GoogleCredentials.fromStream(serviceAccountStream)
            .createScoped(Collections.singleton("https://www.googleapis.com/auth/cloud-platform"))
        credentials.refreshIfExpired()
        val accessToken = credentials.refreshAccessToken().tokenValue

        serverKey = "Bearer $accessToken"
        Log.d("FCM", "Access Token11: $serverKey")

        sendFcm()
    }

    private fun sendFcm() {
        val client = OkHttpClient()
        val root = JSONObject()
        val messageContent = JSONObject()
        messageContent.put("token", otherUserFcmToken)
        val notification = JSONObject()
        notification.put("body", message)
        notification.put("title", otherUserName)
        root.put("message", messageContent)
        messageContent.put("notification", notification)

        val requsetBody =
            root.toString().toRequestBody("application/json; charset=utf-8".toMediaType())
        val request = Request.Builder().post(requsetBody)
            .url("https://fcm.googleapis.com/v1/projects/studysimplechat/messages:send")
            .header("Authorization", serverKey).build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("FCM", "보내기실패")
            }

            override fun onResponse(call: Call, response: Response) {
                Log.e("FCM", "보내기성공 : ${root.toString()}")
                Log.e("FCM", "보내기성공 : ${response.body?.string()}")
                if (!response.isSuccessful) {
                    Log.e("FCM", "재시도 : ${root.toString()}")
                    refreshServerKey()
                }
            }
        })
        binding.messageEditText.text.clear()
        message = ""
    }

    companion object {
        const val EXTRA_CHAT_ROOM_ID = "CHAT_ROOM_ID"
        const val EXTRA_OTHER_USER_ID = "OTHER_USER_ID"
    }
}