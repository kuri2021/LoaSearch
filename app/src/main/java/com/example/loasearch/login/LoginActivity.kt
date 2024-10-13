package com.example.loasearch.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.example.loasearch.databinding.ActivityLoginBinding
import com.example.loasearch.signup.SignUpAcrivity
import com.example.loasearch.util.page.PageMove
import com.example.loasearch.util.page.PageMoveExtraData
import com.kakao.sdk.user.UserApiClient

class LoginActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLoginBinding
    private var backPressedTime: Long = 0

    var TAG = "LoginActivityTAG"
    var signupKind  = ArrayList<PageMoveExtraData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        kakaoCheck()

        binding.loginSignupNormalBtn.setOnClickListener {
            signupKind.add(PageMoveExtraData("kind","normal"))
            PageMove(this).nextActivateActivity(SignUpAcrivity(),signupKind)
        }
        onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
        binding.apiBtn.setOnClickListener {
//            val api = binding.apiEt.text.toString()
//            Connect.accessToken = api
//            LoaApi().getNews { _, code ->
//                if (code == "200"){
//                    Toast.makeText(this,"api가 확인 되었습니다.",Toast.LENGTH_SHORT).show()
//                    SharedPreference(this).saveApiKey(api)
//                    PageMove(this).nextActivateActivity(MainActivity())
//                    finish()
//                }else{
//                    SharedPreference(this).deleteApiKey()
//                    Toast.makeText(this,"잘못된 api입니다. 다시 입력해주세요.",Toast.LENGTH_SHORT).show()
//                }
//            }
        }

//        binding.apiLink.setOnClickListener {
//            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://developer-lostark.game.onstove.com/"))
//            startActivity(intent)
//        }
    }

    private fun kakaoCheck(){
        UserApiClient.instance.loginWithKakaoTalk(this) { token, error ->
            if (error != null) {
                Log.e(TAG, "로그인 실패", error)
            }
            else if (token != null) {
                Log.i(TAG, "로그인 성공 ${token.accessToken}")
            }
        }
    }

    override fun onResume() {
        super.onResume()

//        val apikey = SharedPreference(this).getApiKey()
//        if (apikey!=""){
//            Connect.accessToken = apikey
//            PageMove(this).nextActivateActivity(MainActivity())
//            finish()
//        }
    }

    fun moveActivity(){
        val intent = Intent(this,SignUpAcrivity::class.java)
        startActivity(intent)
    }

    private val onBackPressedCallback: OnBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            if (backPressedTime + 2000 > System.currentTimeMillis()) {
                finish()
            } else {
                Toast.makeText(this@LoginActivity, "한 번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show()
                backPressedTime = System.currentTimeMillis()
            }
        }
    }

}