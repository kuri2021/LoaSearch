package com.example.loasearch.signup

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.loasearch.R
import com.example.loasearch.databinding.ActivitySignUpAcrivityBinding
import com.example.loasearch.main.MainActivity
import com.example.loasearch.signup.viewmodel.SignUpViewmodel
import com.example.loasearch.util.connet.Connect
import com.example.loasearch.util.dialog.custom.CustomDialog
import com.example.loasearch.util.page.PageMove
import com.example.loasearch.util.shared.SharedPreference
import com.google.firebase.Firebase
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database


class SignUpActivity : AppCompatActivity() {

    private lateinit var signUpViewModel : SignUpViewmodel
    private lateinit var binding : ActivitySignUpAcrivityBinding
    private lateinit var database: DatabaseReference
    private var userData = UserData()
    private var kakaoUserData = KakaoUserData()

    private var kind = ""
    private var key = ""
    private lateinit var id:String
    private lateinit var pw:String
    private lateinit var api: String

    private val onBackPressedCallback: OnBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            PageMove(this@SignUpActivity).getBackActivity()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpAcrivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        onBackPressedDispatcher.addCallback(this,onBackPressedCallback)
        signUpViewModel = ViewModelProvider(this)[SignUpViewmodel::class.java]
        database = Firebase.database.reference

        kind = intent.getStringExtra("type").toString()
        signUpUiSetting()

        binding.signupDoneBtn.setOnClickListener {
            when(kind){
                "normal"->{
                    id = binding.signupIdEt.text.toString()
                    pw = binding.signupPwEt.text.toString()
                    api = binding.signupApiEt.text.toString()
                    signUpViewModel.signUpNormal(id,pw,api)
                }
                "kakao"->{
                    api = binding.signupApiEt.text.toString()
                    signUpViewModel.signUpKakao(key,api)
                }
            }

        }
        binding.signupBack.setOnClickListener{
            PageMove(this).getBackActivity()
        }
        binding.signupGetApi.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://developer-lostark.game.onstove.com/"))
            startActivity(intent)
        }

        signUpViewModel.inputCheck.observe(this){
            userData.id = id
            userData.pw = pw
            userData.api = api
            database.child("users").child("normal").child(id).setValue(userData).addOnSuccessListener {
                SharedPreference(this).saveType("normal")
                SharedPreference(this).saveIdPw(id,pw)
                Connect.accessToken = api
                CustomDialog(this).defaultSetting(R.layout.signup_success_dialog){
                    PageMove(this).nextActivateActivity(MainActivity(),true,null)
                }
            }.addOnFailureListener{
                CustomDialog(this).errorDialog("signupFail",this){}
            }
        }

        signUpViewModel.kakaoCheck.observe(this){
            kakaoUserData.key = key
            kakaoUserData.api = api
            database.child("users").child("kakao").child(key).setValue(userData).addOnSuccessListener {
                SharedPreference(this).saveType("key")
                SharedPreference(this).saveKey(key)
                Connect.accessToken = api
                CustomDialog(this).defaultSetting(R.layout.signup_success_dialog){
                    PageMove(this).nextActivateActivity(MainActivity(),true,null)
                }
            }.addOnFailureListener{
                CustomDialog(this).errorDialog("signupFail",this){}
            }
        }

        signUpViewModel.toast.observe(this){
            Toast.makeText(this,it,Toast.LENGTH_SHORT).show()
        }
    }


    private fun signUpUiSetting(){
        kind = intent.getStringExtra("kind").toString()
        when(kind){
            "normal"->{
                binding.signupIdInput.visibility = View.VISIBLE
                binding.signupPwInput.visibility = View.VISIBLE
            }
            "kakao","google"->{
                binding.signupIdInput.visibility = View.GONE
                binding.signupPwInput.visibility = View.GONE
                key = intent.getStringExtra("key").toString()
                Log.d("signUpUiSetting",key)
            }
        }
    }
}