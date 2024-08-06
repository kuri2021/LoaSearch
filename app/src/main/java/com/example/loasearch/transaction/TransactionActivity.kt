package com.example.loasearch.transaction

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.loasearch.R
import com.example.loasearch.databinding.ActivityTransactionBinding
import com.example.loasearch.transaction.auctions.AuctionsFragment
import com.example.loasearch.util.page.PageMove

class TransactionActivity : AppCompatActivity() {

    private lateinit var binding : ActivityTransactionBinding
    private lateinit var selectTapId: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTransactionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.trsBack.setOnClickListener {
            PageMove(this).getBackActivity()
        }

        supportFragmentManager.beginTransaction().replace(R.id.transition_frg, AuctionsFragment()).addToBackStack(null).commit()
        selectTapId = AuctionsFragment()
    }

}