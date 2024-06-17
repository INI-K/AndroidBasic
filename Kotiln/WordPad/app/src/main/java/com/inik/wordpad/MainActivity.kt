package com.inik.wordpad

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.inik.wordpad.databinding.ActivityMainBinding
import java.io.File

class MainActivity : AppCompatActivity(), WordAdapter.ItemClickListner {
    private lateinit var binding: ActivityMainBinding
    private lateinit var wordAdapter: WordAdapter
    private var selectedWord: Word? = null
    private val updateAddWordResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        val isUpdated = result.data?.getBooleanExtra("isUpdated", false)
        if (result.resultCode == RESULT_OK && isUpdated == true) {
            updateAddWord()
        }
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private val updateEditWordResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        val editWord = result.data?.getParcelableExtra("editWord", Word::class.java) ?: null
        if (result.resultCode == RESULT_OK && editWord != null) {
            updateEditWord(editWord)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dexOutputDir: File = codeCacheDir
        dexOutputDir.setReadOnly()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()

        binding.addBtn.setOnClickListener {
            Intent(this, AddActivity::class.java).let {
                updateAddWordResult.launch(it)
            }
        }

        binding.deleteImageView.setOnClickListener {
            Log.d("삭제","버튼눌림")
            delete()
        }

        binding.editImageView.setOnClickListener {
            edit()
        }
    }

    //    private fun initViews(){
//        val types = listOf(
//            "명사", "동사", "대명사", "형용사", "부사", "감탄사", "전치사", "접속사"
//        )
//    }
    private fun initRecyclerView() {
//        val dummyList = mutableListOf(
//            Word("Weather", "날씨", "명사"),
//            Word("Honey", "꿀", "명사"),
//            Word("Run", "실행하다", "동사")
//        )
        wordAdapter = WordAdapter(mutableListOf(), this)
        binding.wordRecyclerView.apply {
            adapter = wordAdapter
            layoutManager =
                LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
            val dividerItemDecoration =
                DividerItemDecoration(applicationContext, LinearLayoutManager.VERTICAL)
            addItemDecoration(dividerItemDecoration)
        }
        Thread {
            val list = AppDatabase.getInstance(this)?.wordDao()?.getAll() ?: emptyList()
            wordAdapter.list.addAll(list)
            runOnUiThread { wordAdapter.notifyDataSetChanged() }
        }.start()
    }

    private fun updateAddWord() {
        Thread {
            AppDatabase.getInstance(this)?.wordDao()?.getLatestWord()?.let { word ->
                wordAdapter.list.add(0, word)
                runOnUiThread { wordAdapter.notifyDataSetChanged() }
            }
        }.start()
    }

    private fun updateEditWord(word: Word){
        val index = wordAdapter.list.indexOfFirst { it.id == word.id}
        wordAdapter.list[index] = word
        runOnUiThread{
//            selectedWord = word
            wordAdapter.notifyItemChanged(index)
            binding.textTextView.text = word.text
            binding.meanTextView.text = word.mean
        }
    }
    private fun delete() {
        if (selectedWord == null) {
            Log.d("삭제", "없음")
        }

        Thread {
            selectedWord?.let { word ->
                AppDatabase.getInstance(this)?.wordDao()?.delete(word)
                runOnUiThread {
                    wordAdapter.list.remove(word)
                    wordAdapter.notifyDataSetChanged()
                    binding.textTextView.text = ""
                    binding.meanTextView.text = ""
                    Toast.makeText(this, "삭제가 완료됬습니다.", Toast.LENGTH_SHORT).show()

                }
            }
        }.start()
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun edit(){
        if(selectedWord == null) return

        val intent = Intent(this, AddActivity::class.java).putExtra("originWord", selectedWord)
        updateEditWordResult.launch(intent)

    }

    override fun onClick(word: Word) {
        selectedWord = word
        binding.textTextView.text = word.text
        binding.meanTextView.text = word.mean

    }
}