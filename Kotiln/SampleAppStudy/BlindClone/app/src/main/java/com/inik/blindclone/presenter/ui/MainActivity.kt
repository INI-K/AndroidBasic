package com.inik.blindclone.presenter.ui

import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import com.inik.blindclone.R
import com.inik.blindclone.databinding.ActivityMainBinding
import com.inik.blindclone.domain.model.Content
import com.inik.blindclone.presenter.ui.list.viewholder.ListAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val adapter by lazy { ListAdapter(Handler()) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
            view = this@MainActivity
            recyclerView.adapter = adapter
            recyclerView.addItemDecoration(
                DividerItemDecoration(this@MainActivity,LinearLayout.VERTICAL)
            )
        }
    }

    fun onClickAdd(){

    }

    inner class Handler{
        fun onClickItem(item: Content){

        }
        fun onLongClickItem(item: Content): Boolean {
            AlertDialog.Builder(this@MainActivity)
                .setTitle("정말 삭제하시겠습니까?")
                .setPositiveButton("네"){_,_ ->

                }
                .setNegativeButton("아니요"){_,_ ->

                }.show()
            return false
        }
    }
}