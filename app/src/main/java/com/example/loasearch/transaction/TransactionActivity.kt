package com.example.loasearch.transaction

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.loasearch.R
import com.example.loasearch.databinding.ActivityTransactionBinding
import com.example.loasearch.transaction.auctions.AuctionsFragment
import com.example.loasearch.transaction.market.MarketFragment
import com.example.loasearch.util.page.PageMove
import com.google.android.material.bottomsheet.BottomSheetDialog

class TransactionActivity : AppCompatActivity() {

    private lateinit var binding : ActivityTransactionBinding
    private lateinit var selectTapId: Fragment
    private var tapStatus :String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTransactionBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
            supportFragmentManager.beginTransaction().replace(R.id.transition_frg, MarketFragment()).addToBackStack(null).commit()
            binding.marketTap.setBackgroundColor(resources.getColor(R.color.main))
            binding.auctionTap.setBackgroundColor(Color.parseColor("#00ffffff"))
        }else{
            supportFragmentManager.beginTransaction().replace(R.id.transition_frg, AuctionsFragment()).addToBackStack(null).commit()
            binding.auctionTap.setBackgroundColor(resources.getColor(R.color.main))
            binding.marketTap.setBackgroundColor(Color.parseColor("#00ffffff"))
        }
    }

    @SuppressLint("MissingInflatedId")
    private fun dialogSet(){

        val bottomSheetDialog = BottomSheetDialog(this)

        val view = layoutInflater.inflate(R.layout.market_dialog, null)

        view.findViewById<Button>(R.id.result).setOnClickListener {
            val fragment = supportFragmentManager.findFragmentById(R.id.transition_frg) as MarketFragment
            fragment.toastease()
        }

        bottomSheetDialog.setContentView(view)
        bottomSheetDialog.show()
    }

}