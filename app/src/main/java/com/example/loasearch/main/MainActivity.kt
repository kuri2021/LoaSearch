package com.example.loasearch.main

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.example.loasearch.R
import com.example.loasearch.databinding.ActivityMainBinding
import com.example.loasearch.main.information.InformationFragment
import com.example.loasearch.search.SearchActivity
import com.example.loasearch.util.page.PageMove
import com.example.loasearch.util.shared.SharedPreference

class MainActivity : AppCompatActivity() {

    private lateinit var bindding : ActivityMainBinding
    private var backPressedTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindding.root)
        onBackPressedDispatcher.addCallback(this, onBackPressedCallback)


        bindding.bottomNav.setOnItemSelectedListener {
            when(it.itemId){
                R.id.nav_main ->{
                    supportFragmentManager.beginTransaction().replace(R.id.frg,
                        InformationFragment()
                    ).addToBackStack(null).commit()
                    true
                }

                R.id.nav_search ->{
                    PageMove(this).nextActivateActivity(SearchActivity())
//                    val intent = Intent(this,SearchActivity()::class.java)
//                    startActivity(intent)
                    true
                }
//                R.id.nav_auction_house ->{
//                    supportFragmentManager.beginTransaction().replace(R.id.frg, AuctionHouseFragment()).addToBackStack(null).commit()
//                    true
//                }

                else -> false
            }
        }
    }

    private val onBackPressedCallback: OnBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            if (backPressedTime + 2000 > System.currentTimeMillis()) {
                finish()
            } else {
                Toast.makeText(this@MainActivity, "한 번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show()
                backPressedTime = System.currentTimeMillis()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        Log.d("tagwe", SharedPreference(this).getApiKey())
        supportFragmentManager.beginTransaction().replace(R.id.frg, InformationFragment()).commit()
    }
}