package com.example.loasearch.transaction.market

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.loasearch.R
import com.example.loasearch.databinding.FragmentAuctionBinding
import com.example.loasearch.databinding.FragmentMarketBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

class MarketFragment:Fragment() {

    private lateinit var binding : FragmentMarketBinding
    private lateinit var mContext: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentMarketBinding.inflate(layoutInflater)


        return binding.root
    }

    fun toastease(){
        Toast.makeText(mContext,"작동",Toast.LENGTH_SHORT).show()
    }

}