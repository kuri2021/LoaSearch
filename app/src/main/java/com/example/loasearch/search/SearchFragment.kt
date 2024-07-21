package com.example.loasearch.search

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.loasearch.api.LoaApi
import com.example.loasearch.api.data.GlobalVariable
import com.example.loasearch.databinding.FragmentSearchBinding


class SearchFragment : Fragment() {
    private lateinit var mContext: Context
    private lateinit var binding: FragmentSearchBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSearchBinding.inflate(layoutInflater)
        binding.statusTv.movementMethod = ScrollingMovementMethod()

        binding.nameBtn.setOnClickListener {
            val name = binding.nameEt.text.toString()
            if (name != "") {
                LoaApi().getCharacterData(name) {
                    if (it == "성공") {
                        val data =GlobalVariable.character
                        if (data!=null){
                            val img = data.ArmoryProfile.CharacterImage
                            val characterName = data.ArmoryProfile.CharacterName
                            val level = data.ArmoryProfile.CharacterLevel
                            val className = data.ArmoryProfile.CharacterClassName
                            val guildName = data.ArmoryProfile.GuildName
                            val guildMemberGrade = data.ArmoryProfile.GuildMemberGrade
                            val itemAvgLevel = data.ArmoryProfile.ItemAvgLevel
                            val serverName = data.ArmoryProfile.ServerName
                            Glide.with(mContext).load(img).into(binding.characterIv)

                                binding.statusTv.text =
                                    "$characterName\n$level" +
//                                    "\n${data.ArmoryProfile.Stats}" +
                                            "\n${className}" +
                                            "\n${guildName}" +
                                            "\n${guildMemberGrade}" +
                                            "\n${itemAvgLevel}" +
                                            "\n${serverName}"
                            Log.d("states",data.ArmoryProfile.Stats.toString())
                        }

                    } else {
                        binding.statusTv.text = "불러오기 실패"
                    }

                }
            }
        }

        return binding.root
    }
}