package com.example.loasearch.api

import android.util.Log
import com.example.loasearch.api.data.GlobalVariable
import com.example.loasearch.api.data.challenge_abyss.GetChallengeAbyssData
import com.example.loasearch.api.data.challenge_guardian.GetChallengeGuardianData
import com.example.loasearch.api.data.character.GetCharacterData
import com.example.loasearch.api.data.event.GetEventsData
import com.example.loasearch.api.data.get_auctions_option.GetAuctionsOptionsData
import com.example.loasearch.api.data.get_markets_options.GetMarketsOptionsData
import com.example.loasearch.api.data.market.PostMarketData
import com.example.loasearch.api.data.news.GetNewsData
import com.example.loasearch.api.data.post_auctions.PostAuctionsItemData
import com.example.loasearch.util.connet.Connect
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoaApi : LoaApiInf {
    val api: LoaApiInterface = Connect().connect().create(LoaApiInterface::class.java)

    override fun getCharacterData(name: String, callback: (GetCharacterData?,String) -> Unit) {
        api.getCharacter(name).enqueue(object : Callback<GetCharacterData> {
            override fun onResponse(call: Call<GetCharacterData>, response: Response<GetCharacterData>) {
                val body = response.body()
                val code = response.code()
                callback(body,code.toString())
            }

            override fun onFailure(call: Call<GetCharacterData>, t: Throwable) {
                Log.d("getCharacterData", "${call}/${t}")
                callback(null,t.toString())
            }

        })
    }

    override fun getNews(callback: (GetNewsData?,String) -> Unit) {
        api.getNews().enqueue(object : Callback<GetNewsData> {
            override fun onResponse(call: Call<GetNewsData>, response: Response<GetNewsData>) {
                val body = response.body()
                val code = response.code()
                callback(body,code.toString())
            }

            override fun onFailure(call: Call<GetNewsData>, t: Throwable) {
                Log.d("getNews", "${call}/${t}")
                callback(null,t.toString())
            }
        })
    }


    //    fun getEvents(callback: (String) -> Unit) {
    override fun getEvents(callback: (GetEventsData?,String) -> Unit) {
        api.getEvents().enqueue(object : Callback<GetEventsData> {
            override fun onResponse(call: Call<GetEventsData>, response: Response<GetEventsData>) {
                val body = response.body()
                val code = response.code()
                callback(body,code.toString())
            }

            override fun onFailure(call: Call<GetEventsData>, t: Throwable) {
                Log.d("getEvents", "${call}/${t}")
                callback(null,t.toString())
            }

        })
    }

    //    fun getChallengeGuardian(callback: (String) -> Unit) {
    override fun getChallengeGuardian(callback: (GetChallengeGuardianData?,String) -> Unit) {
        api.getChallengeGuardian().enqueue(object : Callback<GetChallengeGuardianData> {
            override fun onResponse(
                call: Call<GetChallengeGuardianData>,
                response: Response<GetChallengeGuardianData>
            ) {
                val body = response.body()
                val code = response.code()
                callback(body,code.toString())
            }

            override fun onFailure(call: Call<GetChallengeGuardianData>, t: Throwable) {
                Log.d("getChallengeGuardian", "${call}/${t}")
                callback(null,t.toString())
            }

        })
    }

    override fun getChallengeAbyss(callback: (GetChallengeAbyssData?, String) -> Unit) {
        api.getChallengeAbyssData().enqueue(object : Callback<GetChallengeAbyssData> {
            override fun onResponse(call: Call<GetChallengeAbyssData>,
                response: Response<GetChallengeAbyssData>
            ) {
                val body = response.body()
                val code = response.code()
                callback(body,code.toString())
            }

            override fun onFailure(call: Call<GetChallengeAbyssData>, t: Throwable) {
                Log.d("getChallengeAbyss", "${call}/${t}")
                callback(null,t.toString())
            }

        })
    }

    override fun getMarketOptions(callback: (GetMarketsOptionsData?,String) -> Unit) {
        api.getMarketsOptions().enqueue(object : Callback<GetMarketsOptionsData> {
            override fun onResponse(call: Call<GetMarketsOptionsData>, response: Response<GetMarketsOptionsData>) {
                val body = response.body()
                val code = response.code()
                callback(body,code.toString())
            }

            override fun onFailure(call: Call<GetMarketsOptionsData>, t: Throwable) {
                Log.d("getMarketOptions", "${call}/${t}")
                callback(null,t.toString())
            }

        })
    }


    override fun postMarkets(
        sort: String,
        categoryCode: Int,
        characterClass: String,
        itemTier: String,
        itemGrade: String,
        itemName: String,
        pageNo: Int,
        sortCondition: String,
        callback: (PostMarketData) -> Unit
    ) {
        val params: HashMap<String, Any> = HashMap()
        params["Sort"] = sort
        params["CategoryCode"] = categoryCode
        params["CharacterClass"] = characterClass
        if (itemTier == "전체 티어") {
            params["ItemTier"] = ""
        } else {
            params["ItemTier"] = itemTier.toInt()
        }
        if (itemGrade == "전체 등급") {
            params["ItemGrade"] = ""
        } else {
            params["ItemGrade"] = itemGrade
        }

        params["ItemName"] = itemName
        params["PageNo"] = pageNo
        params["SortCondition"] = sortCondition
        Log.d("postMarkets", "$sort/$categoryCode/$characterClass/$itemTier/$itemGrade/$itemName")
        api.postMarkets(params).enqueue(object : Callback<PostMarketData> {
            override fun onResponse(
                call: Call<PostMarketData>,
                response: Response<PostMarketData>
            ) {
                val body = response.body()
                val code = response.code()
                if (body != null && code == 200) {
                    callback(body)
                }
            }

            override fun onFailure(call: Call<PostMarketData>, t: Throwable) {
                Log.d("postMarkets확인", "${call}/$t")
            }

        })
    }

    override fun getAuctionsOptions(callback: (GetAuctionsOptionsData?,String) -> Unit) {
        api.getAuctionsOptions().enqueue(object : Callback<GetAuctionsOptionsData> {
            override fun onResponse(
                call: Call<GetAuctionsOptionsData>,
                response: Response<GetAuctionsOptionsData>
            ) {
                val body = response.body()
                val code = response.code()
                Log.d("getAuctionsOptions", "${body}/$code")
                callback(body,code.toString())
            }

            override fun onFailure(call: Call<GetAuctionsOptionsData>, t: Throwable) {
                Log.d("getAuctionsOptions", "${call}/${t}")
                callback(null,t.toString())
            }

        })
    }

    override fun postAuctions(
        sort: String,
        categoryCode: Int,
        characterClass: String,
        itemTier: String,
        itemGrade: String,
        itemName: String,
        pageNo: Int,
        sortCondition: String,
        levelMin:Int,
        levelMax:Int,
        gradeQuality:String?,
        callback: (PostAuctionsItemData) -> Unit
    ) {
        val params: HashMap<String, Any?> = HashMap()
        params["ItemLevelMin"] = levelMin
        params["ItemLevelMax"] = levelMax
        params["ItemGradeQuality"] = gradeQuality
        params["Sort"] = sort
        params["CategoryCode"] = categoryCode
        params["CharacterClass"] = characterClass
        if (itemTier == "전체 티어") {
            params["ItemTier"] = ""
        } else {
            params["ItemTier"] = itemTier.toInt()
        }
        if (itemGrade == "전체 등급") {
            params["ItemGrade"] = ""
        } else {
            params["ItemGrade"] = itemGrade
        }
        params["ItemName"] = itemName
        params["PageNo"] = pageNo
        params["SortCondition"] = sortCondition

        api.postAuctions(params).enqueue(object : Callback<PostAuctionsItemData> {
            override fun onResponse(
                call: Call<PostAuctionsItemData>,
                response: Response<PostAuctionsItemData>
            ) {
                val body = response.body()
                val code = response.code()
                Log.d("postAuctions확인", "${body}/$code")
                if (body != null && code == 200) {
                    callback(body)
                }
            }

            override fun onFailure(call: Call<PostAuctionsItemData>, t: Throwable) {
                Log.d("postMarkets확인", "${call}/$t")
            }

        })
    }

//    override fun postAuctions(
//        sort: String,
//        categoryCode: Int,
//        characterClass: String,
//        itemTier: String,
//        itemGrade: String,
//        itemName: String,
//        pageNo: Int,
//        sortCondition: String,
//        levelMin:Int,
//        levelMax:Int,
//        gradeQuality:String,
//        skillOptions: ArrayList<SkillOptions>,
//        etcOptions: ArrayList<EtcOptions>,
//        callback: (PostAuctionsItemData) -> Unit
//    ) {
//        val params: HashMap<String, Any> = HashMap()
//        params["Sort"] = sort
//        params["CategoryCode"] = categoryCode
//        params["CharacterClass"] = characterClass
//        if (itemTier == "전체 티어") {
//            params["ItemTier"] = ""
//        } else {
//            params["ItemTier"] = itemTier.toInt()
//        }
//        if (itemGrade == "전체 등급") {
//            params["ItemGrade"] = ""
//        } else {
//            params["ItemGrade"] = itemGrade
//        }
//
//        params["ItemName"] = itemName
//        params["PageNo"] = pageNo
//        params["SortCondition"] = sortCondition
//        Log.d("postMarkets", "$sort/$categoryCode/$characterClass/$itemTier/$itemGrade/$itemName")
//        api.postAuctions(params).enqueue(object : Callback<PostAuctionsItemData> {
//            override fun onResponse(
//                call: Call<PostAuctionsItemData>,
//                response: Response<PostAuctionsItemData>
//            ) {
//                val body = response.body()
//                val code = response.code()
//                Log.d("postMarkets확인", "${body}/$code")
//                if (body != null && code == 200) {
//                    callback(body)
//                }
//            }
//
//            override fun onFailure(call: Call<PostAuctionsItemData>, t: Throwable) {
//                Log.d("postMarkets확인", "${call}/$t")
//            }
//
//        })
//    }

}