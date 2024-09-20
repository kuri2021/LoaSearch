package com.example.loasearch.main

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.loasearch.R
import com.example.loasearch.api.data.challenge_abyss.GetChallengeAbyssData
import com.example.loasearch.api.data.challenge_guardian.GetChallengeGuardianData
import com.example.loasearch.api.data.event.GetEventsData
import com.example.loasearch.api.data.news.GetNewsData
import com.example.loasearch.databinding.ActivityMainBinding
import com.example.loasearch.main.event.EventFragment
import com.example.loasearch.main.information.InformationFragment
import com.example.loasearch.search.SearchFragment
import com.example.loasearch.transaction.TransactionActivity
import com.example.loasearch.util.page.PageMove

class MainActivity : AppCompatActivity() {

    companion object{
        var selectedFragment: String = ""
    }

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
                    if ("Information" == selectedFragment) {
                        false
                    }else{
                        supportFragmentManager.beginTransaction().replace(R.id.frg, InformationFragment()).addToBackStack(null).commit()
                        selectedFragment = "Information"
                        true
                    }
                }

                R.id.nav_search ->{
                    if ("Search" == selectedFragment) {
                        false
                    }else{
                        supportFragmentManager.beginTransaction().replace(R.id.frg, SearchFragment()).addToBackStack(null).commit()
                        selectedFragment = "Search"
                        true
                    }
                }
                R.id.nav_transaction ->{
                    PageMove(this).nextActivateActivity(TransactionActivity())
                    true
                }

                else -> false
            }
        }
    }

    private val onBackPressedCallback: OnBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            val fragmentManager = supportFragmentManager
            if (fragmentManager.backStackEntryCount>0){
                fragmentManager.popBackStack()
            }else{
                if (backPressedTime + 2000 > System.currentTimeMillis()) {
                    finish()
                } else {
                    Toast.makeText(this@MainActivity, "한 번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show()
                    backPressedTime = System.currentTimeMillis()
                }
            }

        }
    }

    override fun onResume() {
        super.onResume()
        if (selectedFragment!=""){
            when(selectedFragment){
                "Information" ->{
                    supportFragmentManager.beginTransaction().replace(R.id.frg, InformationFragment()).addToBackStack(null).commit()
                }

                "Search" ->{
                    supportFragmentManager.beginTransaction().replace(R.id.frg, SearchFragment()).addToBackStack(null).commit()
                }

                "Event" ->{
                    supportFragmentManager.beginTransaction().replace(R.id.frg, EventFragment()).addToBackStack(null).commit()
                }
                else -> supportFragmentManager.beginTransaction().replace(R.id.frg, InformationFragment()).commit()
            }
        }else{
            supportFragmentManager.beginTransaction().replace(R.id.frg, InformationFragment()).commit()
        }

    }
}