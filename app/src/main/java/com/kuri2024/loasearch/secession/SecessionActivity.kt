package com.kuri2024.loasearch.secession

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.kuri2024.loasearch.databinding.ActivitySecessionBinding
import com.kuri2024.loasearch.login.LoginActivity
import com.kuri2024.loasearch.util.kakao.KakaoUtil
import com.kuri2024.loasearch.util.page.PageMove
import com.kuri2024.loasearch.util.shared.SharedPreference

class SecessionActivity : AppCompatActivity() {

    lateinit var binding : ActivitySecessionBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecessionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = FirebaseDatabase.getInstance().getReference()

        binding.secessionBack.setOnClickListener {
            PageMove(this).getBackActivity()
        }

        binding.secessionDone.setOnClickListener {
            secession()
        }
    }

    private fun secession(){
        val type = SharedPreference(this).getType()
        when(type){
            "normal"->{
                val id = SharedPreference(this).getId()
                database.child("users").child("normal").child(id).child("id").removeValue()
                database.child("users").child("normal").child(id).child("pw").removeValue()
                database.child("users").child("normal").child(id).child("api").removeValue()
                Toast.makeText(this,"탈퇴 완료 되었습니다",Toast.LENGTH_SHORT).show()
                PageMove(this).nextActivateActivity(LoginActivity(),true,null)
            }
            "kakao"->{
                KakaoUtil(this).kakaoNotConnect {
                    if (it == "success"){
                        val kakaoId = SharedPreference(this).getKey()
                        database.child("users").child("kakao").child(kakaoId).child("key").removeValue()
                        database.child("users").child("kakao").child(kakaoId).child("api").removeValue()

                        Toast.makeText(this,"탈퇴 완료 되었습니다",Toast.LENGTH_SHORT).show()
                        PageMove(this).nextActivateActivity(LoginActivity(),true,null)
                    }else{
                        Toast.makeText(this,"탈퇴에 문제가 생겨 실패했습니다",Toast.LENGTH_SHORT).show()
                    }
                }

            }
        }
    }
}