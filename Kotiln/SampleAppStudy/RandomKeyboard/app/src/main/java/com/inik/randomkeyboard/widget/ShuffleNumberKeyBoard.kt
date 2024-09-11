package com.inik.randomkeyboard.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.GridLayout
import android.widget.TextView
import androidx.core.view.children
import com.inik.randomkeyboard.databinding.WidgetShuffleNumberKeyboardBinding
import kotlin.random.Random

class ShuffleNumberKeyBoard @JvmOverloads
constructor(context: Context, attr: AttributeSet? = null, defstyleAttr: Int = 0
) : GridLayout(context, attr, defstyleAttr), View.OnClickListener{

    private var _binding: WidgetShuffleNumberKeyboardBinding? = null
    private val binding get() = _binding!!
    private var listener : KeyPadListener? = null

    init {
        _binding = WidgetShuffleNumberKeyboardBinding.inflate(LayoutInflater.from(context),this,true)
        binding.view = this
        binding.clickListener = this
        shuffle()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        _binding = null
    }
    fun shuffle(){
        val keyNumberArray = ArrayList<String>()
        for (i in 0..9){
            keyNumberArray.add(i.toString())
        }
        binding.gridLayout.children.forEach {view ->
            if(view is TextView && view.tag != null){
                val randIndex = Random.nextInt(keyNumberArray.size)
                view.text = keyNumberArray[randIndex]
                keyNumberArray.removeAt(randIndex)
            }
        }
    }
    fun onClickDelete(){
        listener?.onClickDelete()
    }
    fun onClickDone(){
        listener?.conClickDone()
    }
    fun setKeyPadListener(keyPadListener: KeyPadListener){
        this.listener = keyPadListener
    }
    interface KeyPadListener{
        fun onClickNum(num: String)
        fun onClickDelete()
        fun conClickDone()
    }

    override fun onClick(view: View) {
        if(view is TextView && view.tag != null){
            listener?.onClickNum(view.text.toString())
        }
    }
}