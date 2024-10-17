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
import com.example.loasearch.util.kakao.KakaoUtil
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

    private var signupKind  = ArrayList<PageMoveExtraData>()
    private lateinit var database: DatabaseReference

    lateinit var id :String
    lateinit var pw : String
    lateinit var key : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        database = FirebaseDatabase.getInstance().getReference()

        binding.loginSignupNormalBtn.setOnClickListener {
            signupKind.add(PageMoveExtraData("kind","normal"))
            PageMove(this).nextActivateActivity(SignUpActivity(),false,signupKind)
        }

        onBackPressedDispatcher.addCallback(this, onBackPressedCallback)

//        일반 로그인
        binding.loginBtn.setOnClickListener {
            id = binding.idEt.text.toString()
            pw = binding.pwEt.text.toString()
            loginViewModel.normalLogin(id,pw)
        }

        loginViewModel.login.observe(this){
            SharedPreference(this).saveIdPw(id,pw)
            SharedPreference(this).saveType("normal")
            PageMove(this).nextActivateActivity(MainActivity(),true,null)
        }

//        카카오 로그인
        binding.loginKakao.setOnClickListener {
            KakaoUtil(this).kakaoGetToken { status, token ->
                if (status == "success"&&token!=null){
                    KakaoUtil(this).kakaoGetData{status2, kakaoId->
                        if (status2 == "success"&&kakaoId!=null){
                            key = kakaoId
                            loginViewModel.kakaoLogin(kakaoId)
                        }else{
                            Toast.makeText(this,"로그인 실패",Toast.LENGTH_SHORT).show()
                        }
                    }
                }else{
                    Toast.makeText(this,"로그인 실패",Toast.LENGTH_SHORT).show()
                }
            }
        }

        loginViewModel.kakaoLogin.observe(this){
            SharedPreference(this).saveKey(key)
            SharedPreference(this).saveType("kakao")
            PageMove(this).nextActivateActivity(MainActivity(),true,null)
        }

        loginViewModel.kakaoSignUp.observe(this){
            signupKind.add(PageMoveExtraData("kind","kakao"))
            signupKind.add(PageMoveExtraData("key",key))
            PageMove(this).nextActivateActivity(SignUpActivity(),true,signupKind)
        }

//        알림 토스트
        loginViewModel.toast.observe(this){
            Toast.makeText(this,it,Toast.LENGTH_SHORT).show()
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

    override fun onResume() {
        super.onResume()
        signupKind.clear()
    }

}