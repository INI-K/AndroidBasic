package com.inik.simplechat.userlist

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import com.inik.simplechat.Key.Companion.DB_CHAT_ROOMS
import com.inik.simplechat.Key.Companion.DB_USERS

import com.inik.simplechat.R
import com.inik.simplechat.chatdetail.ChatActivity
import com.inik.simplechat.chatlist.ChatRoomItem
import com.inik.simplechat.databinding.FragmentUserlistBinding
import java.util.UUID

class UserFragment : Fragment(R.layout.fragment_userlist) {

    private lateinit var binding: FragmentUserlistBinding
    private var otherUserId = ""
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentUserlistBinding.bind(view)

        val userListAdapter = UserAdapter() {otherUser ->
            val myUserId = Firebase.auth.currentUser?.uid ?: ""
            val chatRoomDB =Firebase.database.reference.child(DB_CHAT_ROOMS).child(myUserId).child(otherUser.userId ?: "")

            chatRoomDB.get().addOnSuccessListener {
                Log.e("데이터 로드","성공?")
                var chatRoomId = ""
                if(it.value != null){
                    //데이터 있음
                    val chatRoom = it.getValue(ChatRoomItem::class.java)
                    chatRoomId = chatRoom?.chatRoomId ?: ""
                }else{
                    chatRoomId = UUID.randomUUID().toString()
                    val newChatRoom = ChatRoomItem(
                        chatRoomId = chatRoomId,
                        otherUserName = otherUser.username,
                        otherUserId = otherUser.userId,
                    )
                    chatRoomDB.setValue(newChatRoom)
                }
                val intent = Intent(context,ChatActivity::class.java)
                if(otherUserId.isNullOrEmpty()){
                    intent.putExtra(ChatActivity.EXTRA_OTHER_USER_ID,otherUserId)
                }else{
                    intent.putExtra(ChatActivity.EXTRA_OTHER_USER_ID,otherUser.userId)
                }
                intent.putExtra(ChatActivity.EXTRA_CHAT_ROOM_ID,chatRoomId)

                startActivity(intent)
            }
        }
        binding.userListRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = userListAdapter
        }

        val currentUserId = Firebase.auth.currentUser?.uid ?: ""

        Firebase.database.reference.child(DB_USERS).addListenerForSingleValueEvent(
            object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {

                    val userItemList = mutableListOf<UserItem>()

                    snapshot.children.forEach {
                        val user = it.getValue(UserItem::class.java)
                        user ?: return

                        if (user.userId != currentUserId) {
                            userItemList.add(user)
                            otherUserId = user.userId ?: ""
                            Log.e("유저 확인 ", "유저리스트 아이디 : ${user.userId}")
                        }
                    }
                    userListAdapter.submitList(userItemList)
                }

                override fun onCancelled(error: DatabaseError) {

                }
            }
        )
    }

}