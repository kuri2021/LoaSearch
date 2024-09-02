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

    override fun getCharacterData(name: String, callback: (String) -> Unit) {
        api.getCharacter(name).enqueue(object : Callback<GetCharacterData> {
            override fun onResponse(
                call: Call<GetCharacterData>,
                response: Response<GetCharacterData>
            ) {
                val body = response.body()
                val code = response.code()
                Log.d("확인", code.toString())
                if (body != null) {
                    GlobalVariable.character = body
                    callback("성공")
                } else {
                    callback("실패")
                }
            }

            override fun onFailure(call: Call<GetCharacterData>, t: Throwable) {
                callback("통신 실패 : ${call}/${t}")
            }

        })
    }

    override fun getNews(callback: (String) -> Unit) {
        api.getNews().enqueue(object : Callback<GetNewsData> {
            override fun onResponse(call: Call<GetNewsData>, response: Response<GetNewsData>) {
                val body = response.body()
                val code = response.code()
                Log.d("확인", code.toString())
                if (body != null && code == 200) {
                    GlobalVariable.news = body
                    callback(code.toString())
                } else {
                    callback(code.toString())
                }
            }

            override fun onFailure(call: Call<GetNewsData>, t: Throwable) {
                Log.d("getNews", "${call}/${t}")
                callback("실패")
            }

        })
    }

    //    fun getEvents(callback: (String) -> Unit) {
    override fun getEvents(callback: (String) -> Unit) {
        api.getEvents().enqueue(object : Callback<GetEventsData> {
            override fun onResponse(call: Call<GetEventsData>, response: Response<GetEventsData>) {
                val body = response.body()
                val code = response.code()
                Log.d("확인", code.toString())
                if (body != null && code == 200) {
                    GlobalVariable.events = body
                    callback("완료")
                }
            }

            override fun onFailure(call: Call<GetEventsData>, t: Throwable) {
                Log.d("getEvents", "${call}/${t}")
                callback("실패")
            }

        })
    }

    //    fun getChallengeGuardian(callback: (String) -> Unit) {
    override fun getChallengeGuardian(callback: (String) -> Unit) {
        api.getChallengeGuardian().enqueue(object : Callback<GetChallengeGuardianData> {
            override fun onResponse(
                call: Call<GetChallengeGuardianData>,
                response: Response<GetChallengeGuardianData>
            ) {
                val body = response.body()
                val code = response.code()
                Log.d("확인", code.toString())
                if (body != null && code == 200) {
                    GlobalVariable.challengeGuardian = body
                    callback("완료")
                }
            }

            override fun onFailure(call: Call<GetChallengeGuardianData>, t: Throwable) {
                Log.d("getChallengeGuardian", "${call}/${t}")
                callback("실패")
            }

        })
    }

    //    fun getChallengeAbyssData(callback: (String) -> Unit) {
    override fun getChallengeAbyss(callback: (String) -> Unit) {
        api.getChallengeAbyssData().enqueue(object : Callback<GetChallengeAbyssData> {
            override fun onResponse(
                call: Call<GetChallengeAbyssData>,
                response: Response<GetChallengeAbyssData>
            ) {
                val body = response.body()
                val code = response.code()
                Log.d("확인", code.toString())
                if (body != null && code == 200) {
                    GlobalVariable.challengeAbyss = body
                    callback("완료")
                }
            }

            override fun onFailure(call: Call<GetChallengeAbyssData>, t: Throwable) {
                callback("실패")
            }

        })
    }

    override fun getMarketOptions(callback: (String) -> Unit) {
        api.getMarketsOptions().enqueue(object : Callback<GetMarketsOptionsData> {
            override fun onResponse(
                call: Call<GetMarketsOptionsData>,
                response: Response<GetMarketsOptionsData>
            ) {
                val body = response.body()
                val code = response.code()
                Log.d("getMarket확인", "${body}/$code")
                if (body != null && code == 200) {
                    GlobalVariable.marketOption = body
                    callback("성공")
                }
            }

            override fun onFailure(call: Call<GetMarketsOptionsData>, t: Throwable) {
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

    override fun getAuctionsOptions(callback: (String) -> Unit) {
        api.getAuctionsOptions().enqueue(object : Callback<GetAuctionsOptionsData> {
            override fun onResponse(
                call: Call<GetAuctionsOptionsData>,
                response: Response<GetAuctionsOptionsData>
            ) {
                val body = response.body()
                val code = response.code()
                Log.d("getAuctionsOptions", "${body}/$code")
                if (body != null && code == 200) {
                    GlobalVariable.auctionOption = body
                    callback(code.toString())
                }
            }

            override fun onFailure(call: Call<GetAuctionsOptionsData>, t: Throwable) {

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