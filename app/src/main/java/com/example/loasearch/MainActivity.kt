package com.example.loasearch

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.loasearch.databinding.ActivityMainBinding
import com.example.loasearch.information.InformationFragment
import com.example.loasearch.search.SearchFragment

class MainActivity : AppCompatActivity() {

    lateinit var bindding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        bindding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindding.root)

        bindding.bottomNav.setOnItemSelectedListener {
            when(it.itemId){
                R.id.nav_main ->{
                    supportFragmentManager.beginTransaction().replace(R.id.frg,InformationFragment()).addToBackStack(null).commit()
                    true
                }

                R.id.nav_search ->{
                    supportFragmentManager.beginTransaction().replace(R.id.frg,SearchFragment()).addToBackStack(null).commit()
                    true
                }

                else -> false
            }
        }
    }
}