package com.example.loasearch.main.information

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loasearch.api.LoaApi
import com.example.loasearch.api.data.GlobalVariable
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch


class InformationViewModel : ViewModel() {
    private val _newsData = MutableLiveData<String>()
    val newsData: LiveData<String> get() = _newsData
    private val _eventsData = MutableLiveData<String>()
    val eventsData: LiveData<String> get() = _eventsData
    private val _challengeAbyssData = MutableLiveData<String>()
    val challengeAbyssData: LiveData<String> get() = _challengeAbyssData
    private val _challengeGuardianData = MutableLiveData<String>()
    val challengeGuardianData: LiveData<String> get() = _challengeGuardianData

    fun getInformationData() {
        viewModelScope.launch {
            if (GlobalVariable.news == null) {
                LoaApi().getNews { newsIt ->
                    if (newsIt!="200"){
                        this.cancel()
                        return@getNews
                    }else{
                        _newsData.postValue(newsIt)
                    }
                }
            }else{
                _newsData.postValue("200")
            }
            if (GlobalVariable.events == null) {
                LoaApi().getEvents { eventIt ->
                    if (eventIt!="200"){
                        this.cancel()
                        return@getEvents
                    }else{
                        _eventsData.postValue(eventIt)
                    }
                }
            }else{
                _eventsData.postValue("200")
            }
            if (GlobalVariable.challengeAbyss == null) {
                LoaApi().getChallengeAbyss { abyssIt ->
                    if (abyssIt!="200"){
                        this.cancel()
                        return@getChallengeAbyss
                    }else{
                        _challengeAbyssData.postValue(abyssIt)
                    }

                }
            }else{
                _challengeAbyssData.postValue("200")
            }
            if (GlobalVariable.challengeGuardian == null) {
                LoaApi().getChallengeGuardian { guardianIt ->
                    if (guardianIt!="200"){
                        this.cancel()
                        return@getChallengeGuardian
                    }else{
                        _challengeGuardianData.postValue(guardianIt)
                    }
                }
            }else{
                _challengeGuardianData.postValue("200")
            }
        }
    }

}
