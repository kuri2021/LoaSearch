package com.example.loasearch.information

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.loasearch.api.LoaApi
import com.example.loasearch.api.data.GlobalVariable
import com.example.loasearch.databinding.FragmentInformationBinding
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class InformationFragment: Fragment() {
    private lateinit var mContext: Context
    private lateinit var binding : FragmentInformationBinding
    private lateinit var inforViewModel:InformationViewModel
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentInformationBinding.inflate(layoutInflater)
        inforViewModel = ViewModelProvider(this).get(InformationViewModel::class.java)
        inforViewModel.getInformationData()
        inforViewModel.resultData.observe(viewLifecycleOwner) { _ ->
            val news = GlobalVariable.news
            val events = GlobalVariable.events
            val challengeAbyss = GlobalVariable.challengeAbyss
            val challengeGuardian = GlobalVariable.challengeGuardian
            if (news != null) {
                Log.d("news확인 데이터 ", news[0].Title)
            }else{
                Log.d("news확인 데이터 ", "데이터 없음")
            }
            if (events != null){
                Log.d("events확인 데이터 ", events[0].Title)
            }else{
                Log.d("events확인 데이터 ", "데이터 없음")
            }
            if (challengeAbyss != null){
                Log.d("challengeAbyss확인 데이터 ", challengeAbyss[0].Name)
            }else{
                Log.d("challengeAbyss확인 데이터 ", "데이터 없음")
            }
            if (challengeGuardian != null){
                Log.d("challengeGuardian확인 데이터 ", challengeGuardian.Raids[0].Name)
            }else{
                Log.d("challengeGuardian확인 데이터 ", "데이터 없음")
            }


        }
        return binding.root
    }

    override fun onResume() {
        super.onResume()

    }
}