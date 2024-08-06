package com.example.loasearch.transaction

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.loasearch.R
import com.example.loasearch.databinding.ActivityTransactionBinding
import com.example.loasearch.main.information.InformationFragment
import com.example.loasearch.transaction.auctions.AuctionsFragment

class TransactionActivity : AppCompatActivity() {

    lateinit var binding : ActivityTransactionBinding
    private lateinit var selectTapId: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTransactionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.trsToolbar.title = "거래소"

//        if ()

//        enableEdgeToEdge()

//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
    }

    override fun onResume() {
        super.onResume()
        supportFragmentManager.beginTransaction().replace(R.id.transition_frg,
            AuctionsFragment()
        ).addToBackStack(null).commit()
        selectTapId = AuctionsFragment()
    }

}