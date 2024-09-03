package com.example.loasearch.main.event

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.loasearch.R
import com.example.loasearch.databinding.FragmentEventBinding
import com.example.loasearch.databinding.FragmentInformationBinding

class EventFragment : Fragment() {

    companion object {
        fun newInstance() = EventFragment()
    }

    private lateinit var binding : FragmentEventBinding
    private val viewModel: EventViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEventBinding.inflate(layoutInflater)
        return binding.root
    }
}