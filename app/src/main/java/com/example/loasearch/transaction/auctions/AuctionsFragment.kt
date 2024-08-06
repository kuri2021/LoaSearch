package com.example.loasearch.transaction.auctions

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.loasearch.databinding.FragmentAuctionBinding
import com.example.loasearch.databinding.FragmentInformationBinding

class AuctionsFragment:Fragment() {

    private lateinit var binding : FragmentAuctionBinding
    private lateinit var mContext: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentAuctionBinding.inflate(layoutInflater)


        return binding.root
    }

}