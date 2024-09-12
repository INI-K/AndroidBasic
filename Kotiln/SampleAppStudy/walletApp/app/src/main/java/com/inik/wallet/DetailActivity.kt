package com.inik.wallet

import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import com.inik.wallet.databinding.ActivityDetailBinding
import com.inik.wallet.model.DetailItem
import com.inik.wallet.model.Type
import java.time.Year
import java.util.Calendar

class DetailActivity: AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private val adapter = DetailListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()

        adapter.submitList(mockData())
    }

    private fun initView(){
        binding.cardTitleTextView.text = intent.getStringExtra(CARD_NAME)
        binding.cardLayout.backgroundTintList = intent.getParcelableExtra(CARD_COLOR) as? ColorStateList
        binding.recyclerView.adapter = adapter
    }

    private fun mockData(): List<DetailItem>{
        fun createDate(year: Int, month: Int, day:Int) = Calendar.getInstance().apply {
            set(year,month,day)
        }.time
        val list = mutableListOf<DetailItem>().apply {
            add(
                DetailItem(
                1,
                createDate(2032,1,6 ),
                "상점1",
                25100,
                Type.PAY
            )
            )
            add(
                DetailItem(
                    2,
                    createDate(2032,1,6 ),
                    "상점2",
                    253400,
                    Type.PAY
                )
            )
            add(
                DetailItem(
                    3,
                    createDate(2032,1,6 ),
                    "상점2",
                    253400,
                    Type.CANCEL
                )
            )
            add(
                DetailItem(
                    4,
                    createDate(2032,1,6 ),
                    "상점 23",
                    253400,
                    Type.PAY
                )
            )
            add(
                DetailItem(
                    5,
                    createDate(2032,1,6 ),
                    "상점5",
                    253400,
                    Type.CANCEL
                )
            )
            add(
                DetailItem(
                    6,
                    createDate(2032,1,6 ),
                    "상점234",
                    253400,
                    Type.POINT
                )
            )
        }
        return list
    }

    companion object{
        private const val CARD_NAME = "CARD_NAME"
        private const val CARD_COLOR = "CARD_COLOR"

        fun start(
            context: Context,
            cardName: String,
            cardColor : ColorStateList?,
            optionCompat: ActivityOptionsCompat
        ){
            Intent(context,DetailActivity::class.java).apply {
                putExtra(CARD_NAME,cardName)
                putExtra(CARD_COLOR,cardColor)
            }.run {
                context.startActivity(this,optionCompat.toBundle())
            }
        }
    }
}