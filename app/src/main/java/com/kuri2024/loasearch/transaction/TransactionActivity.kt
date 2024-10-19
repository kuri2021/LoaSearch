package com.kuri2024.loasearch.transaction

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.kuri2024.loasearch.R
import com.kuri2024.loasearch.databinding.ActivityTransactionBinding
import com.kuri2024.loasearch.transaction.auctions.AuctionsFragment
import com.kuri2024.loasearch.transaction.market.MarketFragment
import com.kuri2024.loasearch.util.page.PageMove

class TransactionActivity : AppCompatActivity() {

    private lateinit var binding : ActivityTransactionBinding
    private lateinit var selectTapId: Fragment
    private var tapStatus :String = ""
    private lateinit var bottomSheetDialog:BottomSheetDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTransactionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        onBackPressedDispatcher.addCallback(this, onBackPressedCallback)

        binding.trsBack.setOnClickListener {
            PageMove(this).getBackActivity()
        }
        binding.marketTap.setOnClickListener {
            tapStatus = "market"
            pageSet()
        }
        binding.auctionTap.setOnClickListener {
            tapStatus = "auction"
            pageSet()
        }
        binding.trsSearchImg.setOnClickListener {
            dialogSet()
        }
        pageSet()
        selectTapId = AuctionsFragment()
    }

    private fun pageSet(){
        if (tapStatus == "" ||  tapStatus == "market"){
            supportFragmentManager.beginTransaction().replace(R.id.transition_frg, MarketFragment()).commit()
            binding.marketTap.setBackgroundColor(resources.getColor(R.color.main))
            binding.auctionTap.setBackgroundColor(Color.parseColor("#00ffffff"))
        }else{
            supportFragmentManager.beginTransaction().replace(R.id.transition_frg, AuctionsFragment()).commit()
            binding.auctionTap.setBackgroundColor(resources.getColor(R.color.main))
            binding.marketTap.setBackgroundColor(Color.parseColor("#00ffffff"))
        }
    }

    @SuppressLint("MissingInflatedId")
    private fun dialogSet(){
        if(tapStatus == "" ||tapStatus == "market"){
            val fragment = supportFragmentManager.findFragmentById(R.id.transition_frg) as MarketFragment
            fragment.marketBottomDialogSet()
        }else{
            val fragment = supportFragmentManager.findFragmentById(R.id.transition_frg) as AuctionsFragment
            fragment.auctionBottomDialogSet()
        }
    }

    private val onBackPressedCallback: OnBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            PageMove(this@TransactionActivity).getBackActivity()
        }
    }

}