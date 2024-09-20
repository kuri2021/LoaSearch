package com.example.loasearch.main.event

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.loasearch.api.data.GlobalVariable
import com.example.loasearch.databinding.FragmentEventBinding
import com.example.loasearch.main.MainActivity
import com.example.loasearch.main.event.adapter.EventFragmentAdapter

class EventFragment : Fragment() {

    companion object {
        fun newInstance() = EventFragment()
    }

    private lateinit var binding : FragmentEventBinding
    private lateinit var adapter: EventFragmentAdapter
    private val viewModel: EventViewModel by viewModels()

    private lateinit var mContext:Context
    private lateinit var mActivity:Activity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
        mActivity = context as Activity
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentEventBinding.inflate(layoutInflater)
        eventSet()

        binding.eventBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
        return binding.root
    }

    private fun eventSet(){
        val events = GlobalVariable.events
        if (events != null) {
            adapter = EventFragmentAdapter(mContext,events)
            binding.eventList.adapter= adapter
            adapter.setOnItemClickListener(object : EventFragmentAdapter.OnItemClickListener {
                override fun webMove(position: Int) {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(events[position].Link))
                    startActivity(intent)
                }
            })
        }
    }
}