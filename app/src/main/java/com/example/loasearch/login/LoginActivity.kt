package com.example.loasearch.login

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.example.loasearch.api.LoaApi
import com.example.loasearch.databinding.ActivityLoginBinding
import com.example.loasearch.main.MainActivity
import com.example.loasearch.util.connet.Connect
import com.example.loasearch.util.page.PageMove
import com.example.loasearch.util.shared.SharedPreference

class LoginActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLoginBinding
    private var backPressedTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
        binding.apiBtn.setOnClickListener {
            val api = binding.apiEt.text.toString()
            SharedPreference(this).saveApiKey(api)
            LoaApi().getNews {
                if (it == "완료"){
                    Toast.makeText(this,"api가 확인 되었습니다.",Toast.LENGTH_SHORT).show()
                    PageMove(this).nextActivateActivity(MainActivity())
                    finish()
                }else{
                    SharedPreference(this).deleteApiKey()
                    Toast.makeText(this,"잘못된 api입니다. 다시 입력해주세요.",Toast.LENGTH_SHORT).show()
                }
            }
        }

//        binding.apiEt.setText("eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsIng1dCI6IktYMk40TkRDSTJ5NTA5NWpjTWk5TllqY2lyZyIsImtpZCI6IktYMk40TkRDSTJ5NTA5NWpjTWk5TllqY2lyZyJ9.eyJpc3MiOiJodHRwczovL2x1ZHkuZ2FtZS5vbnN0b3ZlLmNvbSIsImF1ZCI6Imh0dHBzOi8vbHVkeS5nYW1lLm9uc3RvdmUuY29tL3Jlc291cmNlcyIsImNsaWVudF9pZCI6IjEwMDAwMDAwMDAwNTExMjYifQ.hPLEYINABECIth-rTjJX_Xq8en-Bsq6aLZtnXbcIJkhE1EAkCsyShB7iRhsgVzgd1FrD6g5ZOnbLShxbmuyRDiyanmM1lzzjvKNn5N7rF_VPNHo3hVl1LG37HORBKmAf3vaX6IHb6JKMRmbhdfb3Jz34zkF6K5K6pizA2SnjYmHycmvqtVHXYwyhsx24nbfWn3J8JFrvKODlN1wcRxNQRsev1XZz_BRXl1x1D6hgbPvO9cCKPU092npfc3lWtR8_r6wFQ-P0A5Pb7ASKSO1cqg1gMeEjocCnhHj2ccxtBuxGGk0JiYrM7C5Jd0xis3aBiQ_BLSYP5foOZKlYQli7kg")

        binding.apiLink.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://developer-lostark.game.onstove.com/"))
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        val apikey = SharedPreference(this).getApiKey()
        if (apikey!=""){
            Connect.accessToken = apikey
            PageMove(this).nextActivateActivity(MainActivity())
            finish()
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

}