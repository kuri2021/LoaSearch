package com.example.loasearch.splash.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.loasearch.util.connet.Connect
import com.google.firebase.Firebase
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database

class StartViewModel:ViewModel() {
    private var database: DatabaseReference = Firebase.database.reference

    private val _login = MutableLiveData<String>()
    val login: MutableLiveData<String> get() = _login

    private val _error = MutableLiveData<String>()
    val error: MutableLiveData<String> get() = _error

    private val _toast = MutableLiveData<String>()
    val toast : MutableLiveData<String> get() = _toast

    fun normalLogin(id:String,pw:String){
        database.child("users").child("normal").child(id).get().addOnSuccessListener {
            if (it.value!=null){
                if (pw == it.child("pw").value){
                    Connect.accessToken = it.child("api").value as String
                    _toast.postValue("로그인에 성공하셨습니다.")
                    _login.postValue("success")
                }else{
                    _toast.postValue("로그인에 실패하셨습니다.")
                    _error.postValue("fail")
                }
            }else{
                _toast.postValue("로그인에 실패하셨습니다.")
                _error.postValue("fail")
            }
        }.addOnFailureListener{
            _toast.postValue("로그인에 실패하셨습니다.")
            _error.postValue("fail")
            Log.d("firebase", "Error getting data", it)
        }
    }

    fun kakaoLogin(key:String){
        database.child("users").child("kakao").child(key).get().addOnSuccessListener {
            if (it.value!=null){
                Connect.accessToken = it.child("api").value as String
                _toast.postValue("로그인에 성공하셨습니다.")
                _login.postValue("success")
            }else{
                _toast.postValue("로그인에 실패하셨습니다.")
                _error.postValue("fail")
            }
        }.addOnFailureListener{
            _toast.postValue("로그인에 실패하셨습니다.")
            _error.postValue("fail")
            Log.d("firebase", "Error getting data", it)
        }
    }
}