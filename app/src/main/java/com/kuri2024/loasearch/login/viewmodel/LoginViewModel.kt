package com.kuri2024.loasearch.login.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database
import com.kuri2024.loasearch.util.connet.Connect

class LoginViewModel : ViewModel() {
    private var database: DatabaseReference = Firebase.database.reference

    private val _login = MutableLiveData<String>()
    val login: MutableLiveData<String> get() = _login

    private val _kakaoLogin = MutableLiveData<String>()
    val kakaoLogin: MutableLiveData<String> get() = _kakaoLogin

    private val _kakaoSignUp = MutableLiveData<String>()
    val kakaoSignUp: MutableLiveData<String> get() = _kakaoSignUp

    private val _toast = MutableLiveData<String>()
    val toast: MutableLiveData<String> get() = _toast

    fun normalLogin(id: String, pw: String) {
        database.child("users").child("normal").child(id).get().addOnSuccessListener {
            if (it.value != null) {
                if (pw == it.child("pw").value) {
                    Connect.accessToken = it.child("api").value as String
                    _toast.postValue("로그인에 성공하셨습니다.")
                    _login.postValue("success")
                } else {
                    _toast.postValue("로그인에 실패하셨습니다.")
                }
            } else {
                _toast.postValue("로그인에 실패하셨습니다.")
            }
        }.addOnFailureListener {
            _toast.postValue("로그인에 실패하셨습니다.")
            Log.d("firebase", "Error getting data", it)
        }
    }

    fun kakaoLogin(key: String) {
        database.child("users").child("kakao").child(key).get().addOnSuccessListener {
            if (it.value != null) {
                Connect.accessToken = it.child("api").value as String
                _toast.postValue("로그인에 성공하셨습니다.")
                _kakaoLogin.postValue("success")
            } else {
                _kakaoSignUp.postValue("success")
            }
        }.addOnFailureListener {
            _toast.postValue("로그인에 실패하셨습니다.")
            Log.d("firebase", "Error getting data", it)
        }
    }

}