package com.kuri2024.loasearch.main

import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.kuri2024.loasearch.R
import com.kuri2024.loasearch.databinding.ActivityMainBinding
import com.kuri2024.loasearch.main.event.EventFragment
import com.kuri2024.loasearch.main.information.InformationFragment
import com.kuri2024.loasearch.search.SearchFragment
import com.kuri2024.loasearch.transaction.TransactionActivity
import com.kuri2024.loasearch.util.page.PageMove
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

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

        Log.d("MainActivity", "keyhash : ${getKeyHash()}")

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
                    PageMove(this).nextActivateActivity(TransactionActivity(),false,null)
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
                    Toast.makeText(this@MainActivity, R.string.exit, Toast.LENGTH_SHORT).show()
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
                    supportFragmentManager.beginTransaction().replace(R.id.frg, InformationFragment()).commit()
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

    private fun getKeyHash() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            val packageInfo = this.packageManager.getPackageInfo(this.packageName, PackageManager.GET_SIGNING_CERTIFICATES)
            for (signature in packageInfo.signingInfo.apkContentsSigners) {
                try {
                    val md = MessageDigest.getInstance("SHA")
                    md.update(signature.toByteArray())
                    Log.d("getKeyHash", "key hash: ${Base64.encodeToString(md.digest(), Base64.NO_WRAP)}")
                } catch (e: NoSuchAlgorithmException) {
                    Log.w("getKeyHash", "Unable to get MessageDigest. signature=$signature", e)
                }
            }
        }
    }
}