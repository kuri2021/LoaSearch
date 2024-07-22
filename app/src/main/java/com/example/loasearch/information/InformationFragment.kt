package com.example.loasearch.information

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.loasearch.R
import com.example.loasearch.api.data.GlobalVariable
import com.example.loasearch.databinding.FragmentInformationBinding
import com.example.loasearch.information.adapter.abyss.AbyssAdapter
import com.example.loasearch.information.adapter.event.EventAdapter
import com.example.loasearch.information.adapter.guardian.GuardianAdapter
import com.example.loasearch.information.adapter.news.NewsAdapter


class InformationFragment: Fragment() {
    private lateinit var mContext: Context
    private lateinit var binding : FragmentInformationBinding
    private lateinit var inforViewModel:InformationViewModel
    private lateinit var newsAdapter:NewsAdapter
    private lateinit var eventAdapter: EventAdapter
    private lateinit var abyssAdapter: AbyssAdapter
    private lateinit var guardianAdapter: GuardianAdapter

    private var evnetFlag:Int = 0
    private var guardianFlag:Int = 0
    private var abyssFlag:Int = 0

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentInformationBinding.inflate(layoutInflater)
        inforViewModel = ViewModelProvider(this)[InformationViewModel::class.java]
        inforViewModel.getInformationData()
        inforViewModel.newsData.observe(viewLifecycleOwner){
            if (it == "완료"){
                val news = GlobalVariable.news
                if (news != null) {
                    newsAdapter = NewsAdapter(mContext,news)
                    binding.updateList.adapter = newsAdapter
                    newsAdapter.setOnItemClickListener(object : NewsAdapter.OnItemClickListener{
                        override fun webMove(position: Int) {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(news[position].Link))
                            startActivity(intent)
                        }
                    })
                }
            }
        }
        inforViewModel.eventsData.observe(viewLifecycleOwner){
            if (it == "완료"){
                val events = GlobalVariable.events
                if (events != null){
                    eventAdapter = EventAdapter(mContext,events)
                    binding.eventList.adapter = eventAdapter
                    eventAdapter.setOnItemClickListener(object : EventAdapter.OnItemClickListener{
                        override fun webMove(position: Int) {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(events[position].Link))
                            startActivity(intent)
                        }
                    })
                }
            }
        }
        inforViewModel.challengeAbyssData.observe(viewLifecycleOwner){
            if (it == "완료"){
                val challengeAbyss = GlobalVariable.challengeAbyss
                if (challengeAbyss != null){
                    abyssAdapter = AbyssAdapter(mContext,challengeAbyss)
                    binding.weeklyAbyssList.adapter = abyssAdapter
                }
            }
        }
        inforViewModel.challengeGuardianData.observe(viewLifecycleOwner){
            if (it == "완료"){
                val challengeGuardian = GlobalVariable.challengeGuardian
                if (challengeGuardian != null){
                    guardianAdapter = GuardianAdapter(mContext,challengeGuardian.Raids)
                    binding.weeklyGuardianList.adapter = guardianAdapter
                }
            }
        }

        binding.eventTitle.setOnClickListener {
            if (evnetFlag == 0){
                Glide.with(mContext).load(R.drawable.arrow_up).into(binding.eventActive)
                binding.eventListCard.visibility = View.VISIBLE
                evnetFlag = 1
            }else{
                Glide.with(mContext).load(R.drawable.arrow_down).into(binding.eventActive)
                binding.eventListCard.visibility = View.GONE
                evnetFlag = 0
            }
        }


        binding.weeklyGuardianTitle.setOnClickListener {
            if (guardianFlag == 0){
                Glide.with(mContext).load(R.drawable.arrow_up).into(binding.weeklyGuardianActive)
                binding.weeklyGuardianListCard.visibility = View.VISIBLE
                guardianFlag = 1
            }else{
                Glide.with(mContext).load(R.drawable.arrow_down).into(binding.weeklyGuardianActive)
                binding.weeklyGuardianListCard.visibility = View.GONE
                guardianFlag = 0
            }
        }


        binding.weeklyAbyssTitle.setOnClickListener {
            if (abyssFlag == 0){
                Glide.with(mContext).load(R.drawable.arrow_up).into(binding.weeklyAbyssActive)
                binding.weeklyAbyssListCard.visibility  = View.VISIBLE
                abyssFlag = 1
            }else{
                Glide.with(mContext).load(R.drawable.arrow_down).into(binding.weeklyAbyssActive)
                binding.weeklyAbyssListCard.visibility  = View.GONE
                abyssFlag = 0
            }
        }

        return binding.root
    }

}