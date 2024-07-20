package com.example.loasearch.information

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.loasearch.databinding.FragmentInformationBinding

class InformationFragment: Fragment() {
    private lateinit var mContext: Context
    private lateinit var binding : FragmentInformationBinding
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentInformationBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onResume() {
        super.onResume()

    }
    fun listSetting(){

    }
}