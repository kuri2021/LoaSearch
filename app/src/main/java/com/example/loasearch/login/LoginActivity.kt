package com.example.loasearch.login

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.loasearch.R
import com.example.loasearch.api.LoaApi
import com.example.loasearch.databinding.ActivityLoginBinding
import com.example.loasearch.databinding.ActivityMainBinding
import com.example.loasearch.databinding.ActivitySearchBinding
import com.example.loasearch.main.MainActivity
import com.example.loasearch.util.shared.SharedPreference

class LoginActivity : AppCompatActivity() {
    lateinit var bindding : ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindding = ActivityLoginBinding.inflate(layoutInflater)
//        enableEdgeToEdge()
        setContentView(bindding.root)

//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }

        bindding.apiBtn.setOnClickListener {
            val api = bindding.apiEt.text.toString()
            SharedPreference(this).saveApiKey(api)
            LoaApi().getNews {
                if (it == "성공"){
                    Toast.makeText(this,"api가 확인 되었습니다.",Toast.LENGTH_SHORT).show()
                    val intent = Intent(this,MainActivity::class.java)
                    startActivity(intent)
                }else{
                    SharedPreference(this).deleteApiKey()
                    Toast.makeText(this,"잘못된 api입니다. 다시 입력해주세요.",Toast.LENGTH_SHORT).show()
                }
            }
        }

        bindding.apiLink.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://developer-lostark.game.onstove.com/"))
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        if (SharedPreference(this).getApiKey()!="null"){
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }
}