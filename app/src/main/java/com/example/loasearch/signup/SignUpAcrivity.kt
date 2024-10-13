package com.example.loasearch.signup

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.example.loasearch.databinding.ActivitySignUpAcrivityBinding
import com.example.loasearch.signup.viewmodel.SignUpViewmodel
import com.example.loasearch.util.page.PageMove


class SignUpAcrivity : AppCompatActivity() {

    private lateinit var signUpViewModel : SignUpViewmodel
    private lateinit var bindding : ActivitySignUpAcrivityBinding

    private val onBackPressedCallback: OnBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            PageMove(this@SignUpAcrivity).getBackActivity()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindding = ActivitySignUpAcrivityBinding.inflate(layoutInflater)
        setContentView(bindding.root)
        onBackPressedDispatcher.addCallback(this,onBackPressedCallback)
        signUpUiSetting()

        bindding.signupBack.setOnClickListener{
            PageMove(this).getBackActivity()
        }


        adjustHeightToContent(bindding.signupApiEt)

    }

    private fun signUpUiSetting(){
        val text = intent.getStringExtra("kind").toString()
        when(text){
            "normal"->{
                bindding.signupNormal.visibility = View.VISIBLE
                bindding.signupApi.visibility = View.GONE
            }
            "kakao"->{
                bindding.signupApi.visibility = View.VISIBLE
                bindding.signupNormal.visibility = View.GONE
            }
            "google"->{
                bindding.signupApi.visibility = View.VISIBLE
                bindding.signupNormal.visibility = View.GONE
            }
        }
    }


    private fun adjustHeightToContent(editText: EditText) {
        val widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(editText.width, View.MeasureSpec.EXACTLY)
        val heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
        editText.measure(widthMeasureSpec, heightMeasureSpec)
        val height = editText.measuredHeight
        editText.height = height
    }
}