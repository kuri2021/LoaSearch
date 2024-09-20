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
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.loasearch.R
import com.example.loasearch.api.data.character.GetCharacterData
import com.example.loasearch.databinding.FragmentSearchBinding
import com.example.loasearch.main.MainActivity
import com.example.loasearch.search.adapter.ArmoryAdapter
import com.example.loasearch.search.data.ArmoryData
import com.example.loasearch.search.viewmodel.SearchViewModel
import com.example.loasearch.util.page.PageMove
import com.example.loasearch.util.regex.RegexUtil
import java.util.regex.Pattern


class SearchFragment : Fragment() {
    private lateinit var searchViewModel: SearchViewModel
    private lateinit var binding: FragmentSearchBinding
    private lateinit var mContext: Context
    private lateinit var mActivity: Activity
    private lateinit var dialog: Dialog
    private var etFlag = 0

    private var armoryItem = ArrayList<ArmoryData>()
    private lateinit var armoryAdapter : ArmoryAdapter

    private var recordData: GetCharacterData? = null

    companion object {
        fun newInstance() = SearchFragment()
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
        searchViewModel = ViewModelProvider(this)[SearchViewModel::class.java]

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
                    searchViewModel.getSearchCharacter(name)
                }
                true
            } else {
                false
            }
        }

        binding.searchBack.setOnClickListener {
            PageMove(mActivity).getBackActivity()
        }

        searchViewModel.characterData.observe(viewLifecycleOwner){
            logLineBreak(it.toString())
            edittextEnable()
            binding.searchDefaultRegion.visibility = View.GONE
            binding.profileRegion.visibility = View.VISIBLE
            profileSetting(it)
        }

        searchViewModel.error.observe(viewLifecycleOwner){
            binding.profileRegion.visibility = View.INVISIBLE
            binding.searchDefaultRegion.visibility = View.VISIBLE
            binding.resultTv.text = resources.getText(R.string.search_not_find)
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

    private fun profileSetting(data: GetCharacterData?){
        if (data!=null){
            recordData = data
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

            getClassificationData(data.ArmoryEquipment[0].Tooltip)
            armorySetting(data)
        }
    }

    private fun armorySetting(data: GetCharacterData){
        for (i in 0..<data.ArmoryEquipment.size) {
            val type = data.ArmoryEquipment[i].Type
            val icon = data.ArmoryEquipment[i].Icon
            val name = data.ArmoryEquipment[i].Name
            val grade = data.ArmoryEquipment[i].Grade
            val transcendenceValue:String
            when(type){
                "무기","투구","상의","하의","장갑","어깨"->{
                    val transcendence = RegexUtil().transcendence(data.ArmoryEquipment[i].Tooltip)
                    transcendenceValue = if (transcendence.isNotEmpty()) "초월 : ${transcendence[0]}" else ""
                }
                else->{
                    transcendenceValue = ""
                }
            }
//            val quality = "품질 : ${RegexUtil().qualityValue(data.ArmoryEquipment[i].Tooltip)[0].replace(",","")}"
//            Log.d("quality",quality)

            armoryItem.add(ArmoryData(type,icon,name,transcendenceValue,grade))
        }
        armoryAdapter = ArmoryAdapter(mContext,armoryItem)
        binding.armoryList.adapter = armoryAdapter
    }

    private fun getClassificationData(data:String){
//        val jsonData = data.trimIndent()
//        val gson = Gson()
//        val jsonObject = gson.fromJson(jsonData, JsonObject::class.java)
//        logLineBreak("$jsonData\n============================================================================================================")

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

    override fun onResume() {
        super.onResume()
        if (recordData!=null){
            profileSetting(recordData)
        }
    }


}