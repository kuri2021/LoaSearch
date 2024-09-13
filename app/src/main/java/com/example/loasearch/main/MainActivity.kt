package com.example.loasearch.main

import android.os.Bundle
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.example.loasearch.R
import com.example.loasearch.databinding.ActivityMainBinding
import com.example.loasearch.main.information.InformationFragment
import com.example.loasearch.search.SearchFragment
import com.example.loasearch.transaction.TransactionActivity
import com.example.loasearch.util.page.PageMove

class MainActivity : AppCompatActivity() {

    private lateinit var bindding : ActivityMainBinding
    private var backPressedTime: Long = 0
    private var selectedItemId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindding.root)
        onBackPressedDispatcher.addCallback(this, onBackPressedCallback)

        bindding.bottomNav.setOnItemSelectedListener {
            when(it.itemId){
                R.id.nav_main ->{
                    if (it.itemId == selectedItemId) {
                        false
                    }else{
                        supportFragmentManager.beginTransaction().replace(R.id.frg, InformationFragment()).addToBackStack(null).commit()
                        selectedItemId = it.itemId
                        true
                    }
                }

                R.id.nav_search ->{
                    if (it.itemId == selectedItemId) {
                        false
                    }else{
                        supportFragmentManager.beginTransaction().replace(R.id.frg, SearchFragment()).addToBackStack(null).commit()
                        selectedItemId = it.itemId
                        true
                    }
                }
                R.id.nav_transaction ->{
                    if (it.itemId == selectedItemId) {
                        false
                    }else{
                        PageMove(this).nextActivateActivity(TransactionActivity())
                        true
                    }
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
        supportFragmentManager.beginTransaction().replace(R.id.frg, InformationFragment()).commit()
        if (selectedItemId == 0){
            selectedItemId = R.id.nav_main
        }
    }
}