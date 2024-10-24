package com.kuri2024.loasearch.splash

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.kuri2024.loasearch.databinding.ActivitySplashBinding
import com.kuri2024.loasearch.login.LoginActivity
import com.kuri2024.loasearch.main.MainActivity
import com.kuri2024.loasearch.splash.viewmodel.StartViewModel
import com.kuri2024.loasearch.util.page.PageMove
import com.kuri2024.loasearch.util.shared.SharedPreference

class SplashActivity : AppCompatActivity() {

    private lateinit var startViewModel : StartViewModel
    lateinit var binding : ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        startViewModel = ViewModelProvider(this)[StartViewModel::class.java]

        startViewModel.login.observe(this){
            PageMove(this).nextActivateActivity(MainActivity(),true,null)
        }

        startViewModel.toast.observe(this){
            Toast.makeText(this,it, Toast.LENGTH_SHORT).show()
        }
        startViewModel.error.observe(this){
            PageMove(this).nextActivateActivity(LoginActivity(),true,null)
        }
    }

    override fun onResume() {
        super.onResume()
        autoLogin()
    }

    private fun autoLogin(){
        val type = SharedPreference(this).getType()
        when(type){
            "normal"->{
                val id = SharedPreference(this).getId()
                val pw = SharedPreference(this).getPw()
                if (id!=""&&pw!=""){
                    startViewModel.normalLogin(id,pw)
                }else{
                    PageMove(this).nextActivateActivity(LoginActivity(),true,null)
                }
            }
            "kakao"->{
                val key = SharedPreference(this).getKey()
                if (key!=""){
                    startViewModel.kakaoLogin(key)
                }else{
                    PageMove(this).nextActivateActivity(MainActivity(),true,null)
                }
            }
            else->{
                PageMove(this).nextActivateActivity(LoginActivity(),true,null)
            }
        }
    }

}