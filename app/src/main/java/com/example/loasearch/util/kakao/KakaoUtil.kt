package com.example.loasearch.util.kakao

import android.content.Context
import android.util.Log
import com.example.loasearch.util.page.PageMoveExtraData
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient

class KakaoUtil(val context:Context):KakaoUtilInterface {

    fun kakaoGetToken(callback: (String, String?) -> Unit){
        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if (error != null) {
                callback("fail", error.toString())
            } else if (token != null) {
                callback("success", token.accessToken)
            }
        }

        if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
            UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
                if (error != null) {
                    Log.d("KakaoUtil", "카카오톡으로 로그인 실패", error)
                    // 사용자가 카카오톡 설치 후 디바이스 권한 요청 화면에서 로그인을 취소한 경우,
                    // 의도적인 로그인 취소로 보고 카카오계정으로 로그인 시도 없이 로그인 취소로 처리 (예: 뒤로 가기)
                    if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                        return@loginWithKakaoTalk
                    }

                    // 카카오톡에 연결된 카카오계정이 없는 경우, 카카오계정으로 로그인 시도
                    UserApiClient.instance.loginWithKakaoAccount(context, callback = callback)
                } else if (token != null) {
                    callback("success", token.accessToken)
                }
            }
        } else {
            UserApiClient.instance.loginWithKakaoAccount(context, callback = callback)
        }
    }

    fun kakaoGetData(callback: (String, String?) -> Unit){
        UserApiClient.instance.accessTokenInfo { tokenInfo, error ->
            if (error != null) {
                Log.e("kakaoGetData", "토큰 정보 보기 실패", error)
                callback("fail", error.toString())
            }
            else if (tokenInfo != null) {
                callback("success", tokenInfo.id.toString())
                Log.i("kakaoGetData", "토큰 정보 보기 성공" +
                        "\n회원번호: ${tokenInfo.id}" +
                        "\n만료시간: ${tokenInfo.expiresIn} 초")
            }
        }
    }

    fun kakaoLogout(){
        UserApiClient.instance.logout { error ->
            if (error != null) {
                Log.e("KakaoUtil", "로그아웃 실패. SDK에서 토큰 삭제됨", error)
            }
            else {
                Log.i("KakaoUtil", "로그아웃 성공. SDK에서 토큰 삭제됨")
            }
        }
    }

    fun kakaoNotConnect(){
        UserApiClient.instance.unlink { error ->
            if (error != null) {
                Log.e("KakaoUtil", "연결 끊기 실패", error)
            }
            else {
                Log.i("KakaoUtil", "연결 끊기 성공. SDK에서 토큰 삭제 됨")
            }
        }
    }
}