package com.example.loasearch.main.information

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.loasearch.R
import com.example.loasearch.api.data.GlobalVariable
import com.example.loasearch.databinding.FragmentInformationBinding
import com.example.loasearch.login.LoginActivity
import com.example.loasearch.main.MainActivity
import com.example.loasearch.main.event.EventFragment
import com.example.loasearch.main.information.adapter.abyss.AbyssAdapter
import com.example.loasearch.main.information.adapter.event.EventAdapter
import com.example.loasearch.main.information.adapter.guardian.GuardianAdapter
import com.example.loasearch.main.information.adapter.news.NewsAdapter
import com.example.loasearch.main.information.viewmodel.InformationViewModel
import com.example.loasearch.secession.SecessionActivity
import com.example.loasearch.util.dialog.custom.CustomDialog
import com.example.loasearch.util.page.PageMove
import com.example.loasearch.util.shared.SharedPreference


class InformationFragment : Fragment() {
    private lateinit var mContext: Context
    private lateinit var mActivity: Activity
    private lateinit var binding: FragmentInformationBinding
    private lateinit var infoViewModel: InformationViewModel
    private lateinit var newsAdapter: NewsAdapter
    private lateinit var eventAdapter: EventAdapter
    private lateinit var abyssAdapter: AbyssAdapter
    private lateinit var guardianAdapter: GuardianAdapter

    private var guardianFlag: Int = 0
    private var abyssFlag: Int = 0
    private var currentPage: Int = 0

    companion object {
        fun newInstance() = InformationFragment()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
        mActivity = context as MainActivity
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentInformationBinding.inflate(layoutInflater)
        infoViewModel = ViewModelProvider(this)[InformationViewModel::class.java]

        infoViewModel.newsData.observe(viewLifecycleOwner) {
            GlobalVariable.news = it
            newSetting()
        }

        infoViewModel.eventsData.observe(viewLifecycleOwner) {
            GlobalVariable.events = it
            eventSetting()
        }

        infoViewModel.challengeAbyssData.observe(viewLifecycleOwner) {
            GlobalVariable.challengeAbyss = it
            abyssSetting()
        }

        infoViewModel.challengeGuardianData.observe(viewLifecycleOwner) {
            GlobalVariable.challengeGuardian = it
            guardianSetting()
        }

        infoViewModel.error.observe(viewLifecycleOwner){
            CustomDialog(mContext).errorDialog(it,mActivity){}
        }

        binding.eventAll.setOnClickListener {
            val eventFragment = EventFragment.newInstance()
            val trans = parentFragmentManager.beginTransaction()
            trans.replace(R.id.frg,eventFragment)
            trans.addToBackStack(null)
            MainActivity.selectedFragment = "Event"
            trans.commit()
        }

        binding.weeklyGuardianTitle.setOnClickListener {
            if (guardianFlag == 0) {
                Glide.with(mContext).load(R.drawable.arrow_up).into(binding.weeklyGuardianActive)
                binding.weeklyGuardianList.visibility = View.VISIBLE
                guardianFlag = 1
            } else {
                Glide.with(mContext).load(R.drawable.arrow_down).into(binding.weeklyGuardianActive)
                binding.weeklyGuardianList.visibility = View.GONE
                guardianFlag = 0
            }
        }


        binding.weeklyAbyssTitle.setOnClickListener {
            if (abyssFlag == 0) {
                Glide.with(mContext).load(R.drawable.arrow_up).into(binding.weeklyAbyssActive)
                binding.weeklyAbyssList.visibility = View.VISIBLE
                abyssFlag = 1
            } else {
                Glide.with(mContext).load(R.drawable.arrow_down).into(binding.weeklyAbyssActive)
                binding.weeklyAbyssList.visibility = View.GONE
                abyssFlag = 0
            }
        }

        binding.logoutTv.setOnClickListener {
            loginMove()
        }
        binding.secessionTv.setOnClickListener {
            PageMove(mActivity).nextActivateActivity(SecessionActivity(),false,null)
        }


        return binding.root
    }

    private fun newSetting(){
        val news = GlobalVariable.news
        if (news!=null){
            newsAdapter = NewsAdapter(mContext, news)
            binding.updateList.adapter = newsAdapter
            newsAdapter.setOnItemClickListener(object : NewsAdapter.OnItemClickListener {
                override fun webMove(position: Int) {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(news[position].Link))
                    startActivity(intent)
                }
            })
        }
    }

    private fun eventSetting(){
        val event = GlobalVariable.events
        if (event != null) {
            eventAdapter = EventAdapter(mContext, event)
            binding.eventList.adapter = eventAdapter
            binding.eventList.registerOnPageChangeCallback(pageChangeCallback)
            eventAdapter.setOnItemClickListener(object : EventAdapter.OnItemClickListener {
                override fun webMove(position: Int) {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(event[position].Link))
                    startActivity(intent)
                }
            })
            eventSlideStart()
        }
    }

    private fun abyssSetting(){
            val challengeAbyss = GlobalVariable.challengeAbyss
            if (challengeAbyss != null) {
                abyssAdapter = AbyssAdapter(mContext, challengeAbyss)
                binding.weeklyAbyssList.adapter = abyssAdapter
            }
    }

    private fun guardianSetting(){
        val challengeGuardian = GlobalVariable.challengeGuardian
        if (challengeGuardian != null) {
            guardianAdapter = GuardianAdapter(mContext, challengeGuardian.Raids)
            binding.weeklyGuardianList.adapter = guardianAdapter
        }
    }

    private val handler = Handler(Looper.getMainLooper())
    private val time: Long = 3000
    private var scrollFlag = false

    private val updatePage = object : Runnable {
        override fun run() {
            if (currentPage == eventAdapter.itemCount) {
                currentPage = 0
            }
            binding.eventList.setCurrentItem(currentPage++, true)
            handler.postDelayed(this, time)
        }
    }

    override fun onResume() {
        super.onResume()
        infoViewModel.getInformationData()
    }

    override fun onPause() {
        super.onPause()
        eventSlideStop()
    }


    private fun eventSlideStart() {
        if (!scrollFlag) {
            handler.postDelayed(updatePage, time)
            scrollFlag = true
        }
    }

    private fun loginMove(){
        SharedPreference(mContext).deleteData()
        Toast.makeText(mContext,"로그아웃 되었습니다", Toast.LENGTH_SHORT).show()
        PageMove(requireActivity()).nextActivateActivity(LoginActivity(),true,null)
    }

    private fun eventSlideStop() {
        if (scrollFlag) {
            handler.removeCallbacks(updatePage)
            scrollFlag = false
        }
    }
    private val pageChangeCallback = object :ViewPager2.OnPageChangeCallback(){
        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            currentPage = position
        }
    }
}