package com.example.loasearch.search

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.loasearch.api.LoaApi
import com.example.loasearch.api.data.GlobalVariable
import com.example.loasearch.databinding.ActivitySearchBinding


class SearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.statusTv.movementMethod = ScrollingMovementMethod()


        binding.nameEt.setOnEditorActionListener{ v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
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
                                Glide.with(this).load(img).into(binding.characterIv)

                                binding.statusTv.text =
                                    "캐릭터 레벨 : $characterName" +
                                            "\n전투 레벨 : $level" +
                                            "\n캐릭터 이름 : ${className}" +
                                            "\n길드 이름 : ${guildName}" +
                                            "\n길드 등급 : ${guildMemberGrade}" +
                                            "\n아이템 레벨 : ${itemAvgLevel}" +
                                            "\n서버 이름 : ${serverName}"
                                binding.statusTv2.text = "엘릭서 : 진군 (Point : 40)\n" +
                                        "초월 : 78"
                            }
                        } else {
                            binding.statusTv.text = "불러오기 실패"
                        }

                    }
                }
                true
            } else {
                false
            }
        }
    }

}