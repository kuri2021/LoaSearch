package com.example.loasearch.login.viewmodel

import android.content.Intent
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.loasearch.main.MainActivity
import com.example.loasearch.util.connet.Connect
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database

class LoginViewModel: ViewModel()  {
    private var database: DatabaseReference = Firebase.database.reference

    private val _login = MutableLiveData<String>()
    val login: MutableLiveData<String> get() = _login

    private val _toast = MutableLiveData<String>()
    val toast : MutableLiveData<String> get() = _toast

    fun login(id:String,pw:String){
        database.child("users").child(id).get().addOnSuccessListener {
            if (it.value!=null){
                if (pw == it.child("pw").value){
                    Connect.accessToken = it.child("api").value as String
                    _toast.postValue("로그인에 성공하셨습니다.")
                    _login.postValue("success")
                }else{
                    _toast.postValue("로그인에 실패하셨습니다.")
                }
            }else{
                _toast.postValue("로그인에 실패하셨습니다.")
            }
        }.addOnFailureListener{
            _toast.postValue("로그인에 실패하셨습니다.")
            Log.d("firebase", "Error getting data", it)
        }
    }

}