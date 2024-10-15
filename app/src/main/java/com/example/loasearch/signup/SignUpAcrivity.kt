package com.example.loasearch.signup

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

    var kind =""
    lateinit var id:String
    private lateinit var pw:String
    var api: String? = null

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


        binding.signupIdPwDoneBtn.setOnClickListener {
            id = binding.signupIdEt.text.toString()
            pw = binding.signupPwEt.text.toString()
            signUpViewModel.idPwCheck(id,pw)
        }

        binding.signupApiDoneBtn.setOnClickListener {
            api = "1234567890"
            database.child("users").child(id).child("api").setValue(api)
        }
        binding.signupBack.setOnClickListener{
            PageMove(this).getBackActivity()
        }
        binding.signupGetApi.setOnClickListener {

        }

        signUpViewModel.idCheck.observe(this){
            binding.signupNormal.visibility = View.GONE
            binding.signupApi.visibility = View.VISIBLE
        }

    }


    private fun signUpUiSetting(){
        kind = intent.getStringExtra("kind").toString()
        when(kind){
            "normal"->{
                binding.signupNormal.visibility = View.VISIBLE
                binding.signupApi.visibility = View.GONE
            }
            "kakao","google"->{
                binding.signupApi.visibility = View.VISIBLE
                binding.signupNormal.visibility = View.GONE
            }
        }
    }
}