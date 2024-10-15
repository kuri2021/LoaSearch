package com.example.loasearch.login

import android.R.id
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.example.loasearch.databinding.ActivityLoginBinding
import com.example.loasearch.main.MainActivity
import com.example.loasearch.signup.SignUpAcrivity
import com.example.loasearch.util.connet.Connect
import com.example.loasearch.util.page.PageMove
import com.example.loasearch.util.page.PageMoveExtraData
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.getValue
import com.kakao.sdk.user.UserApiClient


class LoginActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLoginBinding
    private var backPressedTime: Long = 0

    var TAG = "LoginActivityTAG"
    var signupKind  = ArrayList<PageMoveExtraData>()
    private var fAuth: FirebaseAuth? = null // 파이어베이스 인증
    private lateinit var database: DatabaseReference



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        kakaoCheck()

        database = FirebaseDatabase.getInstance().getReference();

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
            val id = binding.idEt.text.toString()
            val pw = binding.pwEt.text.toString()
            database.child("users").child(id).get().addOnSuccessListener {
                if (pw == it.child("pw").value){
//                    Connect.accessToken = it.child("api").value as String
                    Connect.accessToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsIng1dCI6IktYMk40TkRDSTJ5NTA5NWpjTWk5TllqY2lyZyIsImtpZCI6IktYMk40TkRDSTJ5NTA5NWpjTWk5TllqY2lyZyJ9.eyJpc3MiOiJodHRwczovL2x1ZHkuZ2FtZS5vbnN0b3ZlLmNvbSIsImF1ZCI6Imh0dHBzOi8vbHVkeS5nYW1lLm9uc3RvdmUuY29tL3Jlc291cmNlcyIsImNsaWVudF9pZCI6IjEwMDAwMDAwMDAwNTExMjYifQ.hPLEYINABECIth-rTjJX_Xq8en-Bsq6aLZtnXbcIJkhE1EAkCsyShB7iRhsgVzgd1FrD6g5ZOnbLShxbmuyRDiyanmM1lzzjvKNn5N7rF_VPNHo3hVl1LG37HORBKmAf3vaX6IHb6JKMRmbhdfb3Jz34zkF6K5K6pizA2SnjYmHycmvqtVHXYwyhsx24nbfWn3J8JFrvKODlN1wcRxNQRsev1XZz_BRXl1x1D6hgbPvO9cCKPU092npfc3lWtR8_r6wFQ-P0A5Pb7ASKSO1cqg1gMeEjocCnhHj2ccxtBuxGGk0JiYrM7C5Jd0xis3aBiQ_BLSYP5foOZKlYQli7kg"
                    val intent = Intent(this,MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                Log.d("firebase", "Got value ${it.value}/${it.child("pw").value}/${it.child("api").value as String}")
            }.addOnFailureListener{
                Log.d("firebase", "Error getting data", it)
            }
//            fAuth!!.signInWithEmailAndPassword(id, pw).addOnCompleteListener(this) { task ->
//                Log.d("MainActivity", "로그인 시도 완료")
//                if (task.isSuccessful) {
//                    Log.d("MainActivity", "로그인 성공")
//                    val intent: Intent = Intent(this, MainActivity::class.java)
//                    startActivity(intent)
//                } else {
//                    Log.d("MainActivity", "로그인 실패", task.exception)
//                    Toast.makeText(
//                        this,
//                        "로그인 실패: " + task.exception!!.message,
//                        Toast.LENGTH_SHORT
//                    ).show()
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