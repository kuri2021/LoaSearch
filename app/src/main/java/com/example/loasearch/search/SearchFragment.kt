package com.example.loasearch.search

import android.content.Context
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.loasearch.api.LoaApi
import com.example.loasearch.databinding.FragmentSearchBinding


class SearchFragment : Fragment() {
    lateinit var mContext: Context
    lateinit var binding: FragmentSearchBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(layoutInflater)
        binding.statusTv.movementMethod = ScrollingMovementMethod()

        binding.nameBtn.setOnClickListener {
            val name = binding.nameEt.text.toString()
            if (name != "") {
                LoaApi().getCharacterData(name) {
                    if (it == "성공") {
                        binding.statusTv.text =
                            "${LoaApi.character.ArmoryProfile.CharacterName}" +
                                    "\n${LoaApi.character.ArmoryProfile.CharacterLevel}" +
//                                    "\n${LoaApi.character.ArmoryProfile.Stats}" +
                                    "\n${LoaApi.character.ArmoryProfile.CharacterClassName}" +
                                    "\n${LoaApi.character.ArmoryProfile.GuildName}" +
                                    "\n${LoaApi.character.ArmoryProfile.GuildMemberGrade}" +
                                    "\n${LoaApi.character.ArmoryProfile.ItemAvgLevel}" +
                                    "\n${LoaApi.character.ArmoryProfile.ServerName}"
                    } else {
                        binding.statusTv.text = "불러오기 실패"
                    }

                }
            }
        }

        return binding.root
    }
}