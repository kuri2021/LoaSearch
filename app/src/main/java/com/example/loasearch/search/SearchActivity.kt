package com.example.loasearch.search

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.loasearch.api.LoaApi
import com.example.loasearch.api.data.GlobalVariable
import com.example.loasearch.databinding.ActivitySearchBinding
import com.example.loasearch.util.page.PageMove


class SearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchBinding
    private var etFlag = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        onBackPressedDispatcher.addCallback(this, onBackPressedCallback)

        binding.searchBack.setOnClickListener {
            PageMove(this).getBackActivity()
        }

        binding.statusTv.movementMethod = ScrollingMovementMethod()

        binding.searchImg.setOnClickListener {
            edittextEnable()
        }
        binding.searchDefaultRegion.setOnClickListener {
            edittextEnable()
        }


        binding.nameEt.setOnEditorActionListener{ v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                val name = binding.nameEt.text.toString()
                if (name != "") {
                    LoaApi().getCharacterData(name) {
                        edittextEnable()
                        if (it == "성공") {
                            binding.searchDefaultRegion.visibility = View.GONE
                            binding.profileRegion.visibility = View.VISIBLE
                            profileSetting()
                        } else {
                            binding.profileRegion.visibility = View.GONE
                            binding.searchDefaultRegion.visibility = View.VISIBLE
                            binding.resultTv.text = "검색 결과가 없습니다.\n 다시 검색을 진행해 주세요"
                        }
                    }
                }
                true
            } else {
                false
            }
        }
    }

    private val onBackPressedCallback: OnBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            PageMove(this@SearchActivity).getBackActivity()
        }
    }

    private fun edittextEnable(){
        if (etFlag == 0){
            binding.nameEt.visibility = View.VISIBLE
            binding.searchImg.visibility = View.GONE
            binding.nameEt.requestFocus()
            val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(binding.nameEt, InputMethodManager.SHOW_IMPLICIT)
            etFlag = 1
        }else{
            binding.searchImg.visibility = View.VISIBLE
            binding.nameEt.visibility = View.GONE
            etFlag = 0
        }
    }

    private fun profileSetting(){
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
            binding.profileName.text = characterName
            binding.statusTv.text =
                        "전투 레벨 : $level" +
                        "\n캐릭터 이름 : $className" +
                        "\n길드 이름 : $guildName" +
                        "\n길드 등급 : $guildMemberGrade" +
                        "\n아이템 레벨 : $itemAvgLevel" +
                        "\n서버 이름 : $serverName"
//            binding.statusTv2.text = "엘릭서 : 진군 (Point : 40)\n" +
//                    "초월 : 78"
        }
    }

}