package com.example.loasearch.signup.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database

class SignUpViewmodel: ViewModel() {
    private lateinit var database: DatabaseReference

    private val _toast = MutableLiveData<String>()
    val toast : MutableLiveData<String> get() = _toast

    private val _inputCheck = MutableLiveData<String>()
    val inputCheck: MutableLiveData<String> get() = _inputCheck

    private val _error = MutableLiveData<String>()
    val error: MutableLiveData<String> get() = _error

    fun inputCheck(id:String,pw:String,api:String){
        database = Firebase.database.reference
        if (id!=""){
            if (pw!=""){
                if (api!=""){
                    database.child("users").child(id).addListenerForSingleValueEvent(object :
                        ValueEventListener {
                        override fun onDataChange(dataSnapshot: DataSnapshot) {
                            val value = dataSnapshot.value
                            if (value!=null) {
                                _toast.postValue("이미 등록된 아이디 입니다")
                            } else {
                                _inputCheck.postValue("성공")
                            }
                        }

                        override fun onCancelled(databaseError: DatabaseError) {
                            _toast.postValue(databaseError.message)
                        }
                    })
                }else{
                    _toast.postValue("API Key를 입력해 주세요")
                }
            }else{
                _toast.postValue("비밀번호를 입력해 주세요")
            }
        }else{
            _toast.postValue("아이디를 입력해 주세요")
        }
    }
}