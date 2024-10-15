package com.example.loasearch.login


import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.loasearch.databinding.ActivityLoginBinding
import com.example.loasearch.login.viewmodel.LoginViewModel
import com.example.loasearch.main.MainActivity
import com.example.loasearch.signup.SignUpActivity
import com.example.loasearch.util.page.PageMove
import com.example.loasearch.util.page.PageMoveExtraData
import com.example.loasearch.util.shared.SharedPreference
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.kakao.sdk.user.UserApiClient


class LoginActivity : AppCompatActivity() {

    private lateinit var loginViewModel : LoginViewModel
    private lateinit var binding : ActivityLoginBinding
    private var backPressedTime: Long = 0

    private var TAG = "LoginActivityTAG"
    private var signupKind  = ArrayList<PageMoveExtraData>()
    private lateinit var database: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]
//        kakaoCheck()

        database = FirebaseDatabase.getInstance().getReference()

        binding.loginSignupNormalBtn.setOnClickListener {
            signupKind.add(PageMoveExtraData("kind","normal"))
            PageMove(this).nextActivateActivity(SignUpActivity(),signupKind)
        }
        onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
        binding.loginBtn.setOnClickListener {
            val id = binding.idEt.text.toString()
            val pw = binding.pwEt.text.toString()
            loginViewModel.login(id,pw)
        }
        loginViewModel.login.observe(this){
            PageMove(this).nextActivateActivity(MainActivity(),null)
            finish()
        }
        loginViewModel.toast.observe(this){
            Toast.makeText(this,it,Toast.LENGTH_SHORT).show()
        }
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
        autoLogin()
    }

    private fun autoLogin(){
        val id = SharedPreference(this).getId()
        val pw = SharedPreference(this).getPw()
        if (id!=""&&pw!=""){
            loginViewModel.login(id,pw)
        }
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