package com.example.loasearch.auction_house

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import com.example.loasearch.databinding.FragmentAuctionHouseBinding

class AuctionHouseFragment : Fragment() {
    private lateinit var mContext: Context
    private lateinit var binding : FragmentAuctionHouseBinding
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAuctionHouseBinding.inflate(layoutInflater)
        binding.itemSearchEt.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {

                true
            } else {
                false
            }
        }

        return binding.root
    }
}