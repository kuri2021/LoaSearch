package com.kuri2024.loasearch.signup

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.Firebase
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database
import com.kuri2024.loasearch.R
import com.kuri2024.loasearch.databinding.ActivitySignUpAcrivityBinding
import com.kuri2024.loasearch.main.MainActivity
import com.kuri2024.loasearch.signup.viewmodel.SignUpViewmodel
import com.kuri2024.loasearch.util.connet.Connect
import com.kuri2024.loasearch.util.dialog.custom.CustomDialog
import com.kuri2024.loasearch.util.page.PageMove
import com.kuri2024.loasearch.util.shared.SharedPreference


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

    private var doneCheck1:Boolean = false
    private var doneCheck2:Boolean = false
    private var doneCheck3:Boolean = false
    private var doneCheck4:Boolean = false

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
            if (doneCheck4){
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
            }else{
                Toast.makeText(this,"위 항목을 모두 완료해주세요",Toast.LENGTH_SHORT).show()
            }
        }

        binding.signupIdEt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                val id = binding.signupIdEt.text.toString()
                if (id!=""){
                    binding.signupIdInfo.visibility = View.VISIBLE
                    signUpViewModel.idCheck(id)
                }else{
                    binding.signupIdInfo.visibility = View.INVISIBLE
                }
            }
        })

        binding.signupPwEt.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                val pw = binding.signupPwEt.text.toString()
                val checkPw = binding.signupPwCheckEt.text.toString()
                if (pw!=""){
                    binding.signupPwInfo.visibility = View.VISIBLE
                    signUpViewModel.pwCheck(pw)
                    signUpViewModel.pwSameCheck(pw,checkPw)
                }else{
                    binding.signupPwInfo.visibility = View.INVISIBLE
                }
            }
        })
        binding.signupPwCheckEt.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                val pw = binding.signupPwEt.text.toString()
                val checkPw = binding.signupPwCheckEt.text.toString()
                if (checkPw!=""){
                    binding.signupPwCheckInfo.visibility = View.VISIBLE
                    signUpViewModel.pwCheck(pw)
                    signUpViewModel.pwSameCheck(pw,checkPw)
                }else{
                    binding.signupPwCheckInfo.visibility = View.INVISIBLE
                }
            }
        })


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
            database.child("users").child("kakao").child(key).setValue(kakaoUserData).addOnSuccessListener {
                SharedPreference(this).saveType("kakao")
                SharedPreference(this).saveKey(key)
                Connect.accessToken = api
                CustomDialog(this).defaultSetting(R.layout.signup_success_dialog){
                    PageMove(this).nextActivateActivity(MainActivity(),true,null)
                }
            }.addOnFailureListener{
                CustomDialog(this).errorDialog("signupFail",this){}
            }
        }

        signUpViewModel.idCheck.observe(this){
            doneCheck1 = it
            if (it){
                binding.signupIdInfo.text = "아이디 형식에 일치합니다"
                binding.signupIdInfo.setTextColor(ContextCompat.getColor(this,R.color.infoSuccess))
            }else{
                binding.signupIdInfo.text = "아이디는 영문대소문자와 숫자 조합으로 최소 7자에서 15자 사이로 가능합니다"
                binding.signupIdInfo.setTextColor(ContextCompat.getColor(this,R.color.infoFail))
            }
            doneBtnStatus()
        }

        signUpViewModel.pwCheck.observe(this){
            doneCheck2 = it
            if (it){
                binding.signupPwInfo.text = "비밀번호 형식에 일치합니다"
                binding.signupPwInfo.setTextColor(ContextCompat.getColor(this,R.color.infoSuccess))
            }else{
                binding.signupPwInfo.text = "비밀번호는 영문대소문자와 숫자 조합으로 최소 7자에서 15자 사이로 가능합니다"
                binding.signupPwInfo.setTextColor(ContextCompat.getColor(this,R.color.infoFail))
            }
            doneBtnStatus()
        }

        signUpViewModel.pwSameCheck.observe(this){
            doneCheck3 = it
            if (it){
                binding.signupPwCheckInfo.text = "비밀번호가 일치합니다"
                binding.signupPwCheckInfo.setTextColor(ContextCompat.getColor(this,R.color.infoSuccess))
            }else{
                binding.signupPwCheckInfo.text = "비밀번호가 일치하지 않습니다."
                binding.signupPwCheckInfo.setTextColor(ContextCompat.getColor(this,R.color.infoFail))
            }
            doneBtnStatus()
        }

        signUpViewModel.toast.observe(this){
            Toast.makeText(this,it,Toast.LENGTH_SHORT).show()
        }
    }

    private fun doneBtnStatus(){
        doneCheck4 = doneCheck1&&doneCheck2&&doneCheck3
    }


    private fun signUpUiSetting(){
        kind = intent.getStringExtra("kind").toString()
        when(kind){
            "normal"->{
                binding.signupIdInput.visibility = View.VISIBLE
                binding.signupPwInput.visibility = View.VISIBLE
                binding.signupPwCheckInput.visibility = View.VISIBLE
            }
            "kakao","google"->{
                binding.signupIdInput.visibility = View.GONE
                binding.signupPwInput.visibility = View.GONE
                binding.signupPwCheckInput.visibility = View.GONE
                doneCheck1 = true
                doneCheck2 = true
                doneCheck3 = true
                doneCheck4 = true
                key = intent.getStringExtra("key").toString()
                Log.d("signUpUiSetting",key)
            }
        }
    }
}