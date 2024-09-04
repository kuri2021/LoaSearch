package com.example.loasearch.main.information

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.replace
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.loasearch.R
import com.example.loasearch.api.data.GlobalVariable
import com.example.loasearch.databinding.FragmentInformationBinding
import com.example.loasearch.main.MainActivity
import com.example.loasearch.main.event.EventFragment
import com.example.loasearch.main.information.adapter.abyss.AbyssAdapter
import com.example.loasearch.main.information.adapter.event.EventAdapter
import com.example.loasearch.main.information.adapter.guardian.GuardianAdapter
import com.example.loasearch.main.information.adapter.news.NewsAdapter
import com.example.loasearch.util.dialog.custom.CustomDialog


class InformationFragment : Fragment() {
    private lateinit var mContext: Context
    private lateinit var mActivity: Activity
    private lateinit var binding: FragmentInformationBinding
    private lateinit var infoViewModel: InformationViewModel
    private lateinit var newsAdapter: NewsAdapter
    private lateinit var eventAdapter: EventAdapter
    private lateinit var abyssAdapter: AbyssAdapter
    private lateinit var guardianAdapter: GuardianAdapter

    private lateinit var dialog: Dialog

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
        dialog = Dialog(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInformationBinding.inflate(layoutInflater)
        infoViewModel = ViewModelProvider(this)[InformationViewModel::class.java]
        infoViewModel.getInformationData()
        infoViewModel.newsData.observe(viewLifecycleOwner) {
            if (it == "200") {
                val news = GlobalVariable.news
                if (news != null) {
                    newsAdapter = NewsAdapter(mContext, news)
                    binding.updateList.adapter = newsAdapter
                    newsAdapter.setOnItemClickListener(object : NewsAdapter.OnItemClickListener {
                        override fun webMove(position: Int) {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(news[position].Link))
                            startActivity(intent)
                        }
                    })
                }
            } else {
                CustomDialog(dialog).errorDialog(it, mActivity)
            }
        }
        infoViewModel.eventsData.observe(viewLifecycleOwner) {
            if (it == "200") {
                val events = GlobalVariable.events
                if (events != null) {
                    eventAdapter = EventAdapter(mContext, events)
                    binding.eventList.adapter = eventAdapter
                    eventAdapter.setOnItemClickListener(object : EventAdapter.OnItemClickListener {
                        override fun webMove(position: Int) {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(events[position].Link))
                            startActivity(intent)
                        }
                    })
                }
            } else {
                CustomDialog(dialog).errorDialog(it, mActivity)
            }
        }
        infoViewModel.challengeAbyssData.observe(viewLifecycleOwner) {
            if (it == "완료") {
                val challengeAbyss = GlobalVariable.challengeAbyss
                if (challengeAbyss != null) {
                    abyssAdapter = AbyssAdapter(mContext, challengeAbyss)
                    binding.weeklyAbyssList.adapter = abyssAdapter
                }
            }
        }
        infoViewModel.challengeGuardianData.observe(viewLifecycleOwner) {
            if (it == "완료") {
                val challengeGuardian = GlobalVariable.challengeGuardian
                if (challengeGuardian != null) {
                    guardianAdapter = GuardianAdapter(mContext, challengeGuardian.Raids)
                    binding.weeklyGuardianList.adapter = guardianAdapter
                }
            }
        }

        binding.eventAll.setOnClickListener {
            val eventFragment = EventFragment.newInstance()
            val trans = parentFragmentManager.beginTransaction()
            trans.replace(R.id.frg,eventFragment)
            trans.addToBackStack(null)
            trans.commit()
        }

        binding.weeklyGuardianTitle.setOnClickListener {
            if (guardianFlag == 0) {
                Glide.with(mContext).load(R.drawable.arrow_up).into(binding.weeklyGuardianActive)
                binding.weeklyGuardianListCard.visibility = View.VISIBLE
                guardianFlag = 1
            } else {
                Glide.with(mContext).load(R.drawable.arrow_down).into(binding.weeklyGuardianActive)
                binding.weeklyGuardianListCard.visibility = View.GONE
                guardianFlag = 0
            }
        }


        binding.weeklyAbyssTitle.setOnClickListener {
            if (abyssFlag == 0) {
                Glide.with(mContext).load(R.drawable.arrow_up).into(binding.weeklyAbyssActive)
                binding.weeklyAbyssListCard.visibility = View.VISIBLE
                abyssFlag = 1
            } else {
                Glide.with(mContext).load(R.drawable.arrow_down).into(binding.weeklyAbyssActive)
                binding.weeklyAbyssListCard.visibility = View.GONE
                abyssFlag = 0
            }
        }

        eventSlideStart()

        return binding.root
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
            Log.d("currentPage",currentPage.toString())
            handler.postDelayed(this, time)
        }
    }

    override fun onResume() {
        super.onResume()
        eventSlideStart()
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

    private fun eventSlideStop() {
        if (scrollFlag) {
            handler.removeCallbacks(updatePage)
            scrollFlag = false
        }
    }

}