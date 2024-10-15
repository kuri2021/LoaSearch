package com.example.loasearch.signup.viewmodel

import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.loasearch.signup.UserData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener

class SignUpViewmodel: ViewModel() {
    private lateinit var database: DatabaseReference
    private var userData = UserData()

    private val _toast = MutableLiveData<String>()
    val toast : MutableLiveData<String> get() = _toast

    private val _idCheck = MutableLiveData<String>()
    val idCheck: MutableLiveData<String> get() = _idCheck

    private val _error = MutableLiveData<String>()
    val error: MutableLiveData<String> get() = _error

    fun idPwCheck(id:String,pw:String){
        userData.id = id
        userData.pw = pw
        if (id!=""){
            if (pw!=""){
                database.child("users").child(id).addListenerForSingleValueEvent(object :
                    ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        val value = dataSnapshot.value
                        if (value!=null) {
                            _toast.postValue("이미 등록된 아이디 입니다")
                        } else {
                            _idCheck.postValue("성공")
                        }
                    }

                    override fun onCancelled(databaseError: DatabaseError) {
                        _toast.postValue(databaseError.message)
                    }
                })
            }else{
                _toast.postValue("비밀번호를 입력해 주세요")
            }
        }else{
            _toast.postValue("아이디를 입력해 주세요")
        }
    }
}