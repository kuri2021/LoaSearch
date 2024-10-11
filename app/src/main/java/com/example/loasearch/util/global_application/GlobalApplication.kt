package com.example.loasearch.util.global_application

import android.app.Application
import com.kakao.sdk.common.KakaoSdk

class GlobalApplication : Application() {
    companion object{
        const val kakaoAppKey = "1b28a251c35300fe4479ce5099aae9bd"
    }

    override fun onCreate() {
        super.onCreate()
        KakaoSdk.init(this, kakaoAppKey)
    }
}