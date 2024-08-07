package com.inik.simplechat.chatlist

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import com.inik.simplechat.Key.Companion.DB_CHAT_ROOMS
import com.inik.simplechat.R
import com.inik.simplechat.chatdetail.ChatActivity
import com.inik.simplechat.databinding.FragmentChatlistBinding

class ChatListFragment :Fragment(R.layout.fragment_chatlist) {

    private lateinit var binding: FragmentChatlistBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentChatlistBinding.bind(view)

        val chatListAdapter = ChatListAdapter{ chatRoomItem ->
            val intent = Intent(context, ChatActivity::class.java)
            intent.putExtra(ChatActivity.EXTRA_OTHER_USER_ID,chatRoomItem.otherUserId)
            Log.e("아이디 확인", "상대방 아이디 : $chatRoomItem.otherUserId")
            intent.putExtra(ChatActivity.EXTRA_CHAT_ROOM_ID,chatRoomItem.chatRoomId)
            startActivity(intent)
        }
        binding.chatListRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = chatListAdapter
        }

        val currentUserId = Firebase.auth.currentUser?.uid?: return
        val chatRoomsDB = Firebase.database.reference.child(DB_CHAT_ROOMS).child(currentUserId)

        chatRoomsDB.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val chatRoomList = snapshot.children.map{
                    it.getValue(ChatRoomItem::class.java)

                }
                Log.e("채팅방 룸아이디 확인","${chatRoomList[0]?.chatRoomId}")
                Log.e("채팅방 상대방 아이디 확인","${chatRoomList[0]?.otherUserName}")
                chatListAdapter.submitList(chatRoomList)
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }
}