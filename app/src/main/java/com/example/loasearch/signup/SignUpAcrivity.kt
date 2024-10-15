package com.example.loasearch.signup

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.loasearch.databinding.ActivitySignUpAcrivityBinding
import com.example.loasearch.signup.viewmodel.SignUpViewmodel
import com.example.loasearch.util.page.PageMove
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database


class SignUpAcrivity : AppCompatActivity() {

    private lateinit var signUpViewModel : SignUpViewmodel
    private lateinit var binding : ActivitySignUpAcrivityBinding
    private lateinit var database: DatabaseReference
    private var userData = UserData()

    var kind =""
    private lateinit var id:String
    private lateinit var pw:String
    private lateinit var api: String

    private val onBackPressedCallback: OnBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            PageMove(this@SignUpAcrivity).getBackActivity()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpAcrivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        onBackPressedDispatcher.addCallback(this,onBackPressedCallback)
        signUpUiSetting()

        signUpViewModel = ViewModelProvider(this)[SignUpViewmodel::class.java]

        database = Firebase.database.reference


        binding.signupDoneBtn.setOnClickListener {
            id = binding.signupIdEt.text.toString()
            pw = binding.signupPwEt.text.toString()
            api = binding.signupApiEt.text.toString()
            signUpViewModel.inputCheck(id,pw,api)
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
            database.child("users").child(id).setValue(userData)
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
                binding.signupIdInput.visibility = View.INVISIBLE
                binding.signupPwInput.visibility = View.INVISIBLE
            }
        }
    }
}