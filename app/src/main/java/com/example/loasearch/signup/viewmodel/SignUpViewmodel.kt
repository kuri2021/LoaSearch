package com.example.loasearch.signup.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.loasearch.api.LoaApi
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

    private val _kakaoCheck = MutableLiveData<String>()
    val kakaoCheck: MutableLiveData<String> get() = _kakaoCheck


    private val _error = MutableLiveData<String>()
    val error: MutableLiveData<String> get() = _error

    fun signUpNormal(id:String,pw:String,api:String){
        database = Firebase.database.reference
        if (id!=""){
            if (pw!=""){
                if (api!=""){
                    LoaApi().checkApi(api){
                        if (it=="200"){
                            database.child("users").child("normal").child(id).addListenerForSingleValueEvent(object :
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
                            _toast.postValue("API가 맞지 않습니다. 다시 확인 후 시도해주세요")
                        }
                    }

                }else{
                    _toast.postValue("API를 입력해 주세요")
                }
            }else{
                _toast.postValue("비밀번호를 입력해 주세요")
            }
        }else{
            _toast.postValue("아이디를 입력해 주세요")
        }
    }

    fun signUpKakao(key:String,api:String){
        database = Firebase.database.reference
        if (api!=""){
            LoaApi().checkApi(api){
                if (it=="200"){
                    database.child("users").child("kakao").child(key).addListenerForSingleValueEvent(object :
                        ValueEventListener {
                        override fun onDataChange(dataSnapshot: DataSnapshot) {
                            val value = dataSnapshot.value
                            if (value!=null) {
                                _toast.postValue("이미 등록된 아이디 입니다")
                            } else {
                                _kakaoCheck.postValue("성공")
                            }
                        }

                        override fun onCancelled(databaseError: DatabaseError) {
                            _toast.postValue(databaseError.message)
                        }
                    })
                }else{
                    _toast.postValue("API가 맞지 않습니다. 다시 확인 후 시도해주세요")
                }
            }
        }else{
            _toast.postValue("API를 입력해 주세요")
        }
    }
}