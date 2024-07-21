package com.example.loasearch.information

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loasearch.api.LoaApi
import com.example.loasearch.api.LoaApiInterface
import com.example.loasearch.util.Connect
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


class InformationViewModel : ViewModel() {
    private val _resultData = MutableLiveData<String>()
    val resultData: LiveData<String> get() = _resultData

    fun getInformationData() {
        viewModelScope.launch {
            LoaApi().getNews { newsIt->
                if (newsIt == "완료"){
                    LoaApi().getEvents{ eventIt->
                        if (eventIt == "완료"){
                            LoaApi().getChallengeAbyss{ abyssIt->
                                if (abyssIt == "완료"){
                                    LoaApi().getChallengeGuardian{ guardianIt->
                                        if (guardianIt == "완료"){
                                            _resultData.postValue("완료")
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}