package com.example.loasearch.search

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.loasearch.R
import com.example.loasearch.api.LoaApi
import com.example.loasearch.api.data.GlobalVariable
import com.example.loasearch.databinding.FragmentSearchBinding
import com.example.loasearch.main.MainActivity
import com.example.loasearch.main.information.InformationFragment
import com.example.loasearch.util.page.PageMove
import com.google.gson.Gson
import com.google.gson.JsonObject


class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private lateinit var mContext: Context
    private lateinit var mActivity: Activity
    private lateinit var dialog: Dialog
    private var etFlag = 0

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
        binding = FragmentSearchBinding.inflate(layoutInflater)

        binding.statusTv.movementMethod = ScrollingMovementMethod()
        binding.searchImg.setOnClickListener {
            edittextEnable()
        }
        binding.searchDefaultRegion.setOnClickListener {
            edittextEnable()
        }
        binding.nameEt.setOnEditorActionListener{ _, actionId, _ ->
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
                            binding.resultTv.text = resources.getText(R.string.search_not_find)
                        }
                    }
                }
                true
            } else {
                false
            }
        }

        binding.searchBack.setOnClickListener {
            PageMove(mActivity).getBackActivity()
        }

        return binding.root
    }

    private fun edittextEnable(){
        if (etFlag == 0){
            binding.nameEt.visibility = View.VISIBLE
            binding.searchImg.visibility = View.GONE
            binding.searchTitle.visibility = View.GONE
            binding.nameEt.requestFocus()
            val imm = mContext.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(binding.nameEt, InputMethodManager.SHOW_IMPLICIT)
            etFlag = 1
        }else{
            binding.searchImg.visibility = View.VISIBLE
            binding.nameEt.visibility = View.GONE
            binding.searchTitle.visibility = View.VISIBLE
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
            for (i in 0..<data.ArmoryEquipment.size){
                getClassificationData(data.ArmoryEquipment[0].Tooltip)
            }


        }
    }
    private fun getClassificationData(data:String){
        val jsonData = data.trimIndent()
        val gson = Gson()
        val jsonObject = gson.fromJson(jsonData, JsonObject::class.java)
        logLineBreak("$jsonData\n============================================================================================================")

//        val element000 = gson.fromJson(jsonObject.get("Element_000"), Element000::class.java)
//        val element001 = gson.fromJson(jsonObject.get("Element_001"), Element001::class.java)
//        val element002 = gson.fromJson(jsonObject.get("Element_002"), Element002::class.java)
//
//        Log.d("확인"," ${HtmlCompat.fromHtml(element000.value, HtmlCompat.FROM_HTML_MODE_LEGACY)}/${
//            HtmlCompat.fromHtml(
//                element001.value.toString(), HtmlCompat.FROM_HTML_MODE_LEGACY)
//        }/${HtmlCompat.fromHtml(element002.value, HtmlCompat.FROM_HTML_MODE_LEGACY)}/")
// 주요 정보 추출
//        val itemName = jsonObject.getJSONObject("Element_000").getString("value")
//        val itemType = jsonObject.getJSONObject("Element_001").getJSONObject("value").getString("leftStr0")
//        val itemQuality = jsonObject.getJSONObject("Element_001").getJSONObject("value").getInt("qualityValue")
//        val itemLevel = jsonObject.getJSONObject("Element_001").getJSONObject("value").getString("leftStr2")
//        val exclusiveClass = jsonObject.getJSONObject("Element_002").getString("value")
//        val boundStatus = jsonObject.getJSONObject("Element_003").getString("value")
//        val tradeStatus = jsonObject.getJSONObject("Element_004").getString("value")
//        val enhancementStage = jsonObject.getJSONObject("Element_005").getString("value")
//        val baseEffect = jsonObject.getJSONObject("Element_006").getJSONObject("value").getString("Element_001")
//        val additionalEffect = jsonObject.getJSONObject("Element_007").getJSONObject("value").getString("Element_001")
//        val currentReinforcementExp = jsonObject.getJSONObject("Element_008").getJSONObject("value").getInt("value")
//        val maxReinforcementExp = jsonObject.getJSONObject("Element_008").getJSONObject("value").getInt("maximum")
//        val additionalAttackPower = jsonObject.getJSONObject("Element_009").getJSONObject("value").getJSONObject("Element_000").getJSONObject("contentStr").getString("Element_000")

//        Log.d("확인","$itemName/$itemType/$itemQuality/$itemLevel/$exclusiveClass/$boundStatus/$tradeStatus/$enhancementStage/$baseEffect/$additionalEffect/$currentReinforcementExp/$maxReinforcementExp/$additionalAttackPower")
    }

    private fun logLineBreak(str: String) {
        if (str.length > 3000) {
            Log.d("확인", str.substring(0, 3000))
            logLineBreak(str.substring(3000))
        } else {
            Log.d("확인", str)
        }
    }


}