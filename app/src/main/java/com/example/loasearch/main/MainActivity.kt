package com.example.loasearch.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.loasearch.R
import com.example.loasearch.auction_house.AuctionHouseFragment
import com.example.loasearch.databinding.ActivityMainBinding
import com.example.loasearch.information.InformationFragment
import com.example.loasearch.search.SearchActivity
import com.example.loasearch.util.page.PageMove

class MainActivity : AppCompatActivity() {

    lateinit var bindding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindding.root)

        bindding.bottomNav.setOnItemSelectedListener {
            when(it.itemId){
                R.id.nav_main ->{
                    supportFragmentManager.beginTransaction().replace(R.id.frg,InformationFragment()).addToBackStack(null).commit()
                    true
                }

                R.id.nav_search ->{
                    PageMove(this).nextActivateActivity(SearchActivity())
//                    val intent = Intent(this,SearchActivity()::class.java)
//                    startActivity(intent)
                    true
                }
                R.id.nav_auction_house ->{
                    supportFragmentManager.beginTransaction().replace(R.id.frg, AuctionHouseFragment()).addToBackStack(null).commit()
                    true
                }

                else -> false
            }
        }
    }

    override fun onResume() {
        super.onResume()
        supportFragmentManager.beginTransaction().replace(R.id.frg,InformationFragment()).commit()
    }
}