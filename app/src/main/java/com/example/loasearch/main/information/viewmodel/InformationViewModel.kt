package com.example.loasearch.main.information.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loasearch.api.LoaApi
import com.example.loasearch.api.data.GlobalVariable
import com.example.loasearch.api.data.challenge_abyss.GetChallengeAbyssData
import com.example.loasearch.api.data.challenge_guardian.GetChallengeGuardianData
import com.example.loasearch.api.data.event.GetEventsData
import com.example.loasearch.api.data.news.GetNewsData
import com.example.loasearch.main.MainActivity
import com.example.loasearch.util.dialog.custom.CustomDialog
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch


class InformationViewModel : ViewModel() {

    private val _newsData = MutableLiveData<GetNewsData?>()
    val newsData: LiveData<GetNewsData?> get() = _newsData

    private val _eventsData = MutableLiveData<GetEventsData?>()
    val eventsData: LiveData<GetEventsData?> get() = _eventsData

    private val _challengeAbyssData = MutableLiveData<GetChallengeAbyssData?>()
    val challengeAbyssData: LiveData<GetChallengeAbyssData?> get() = _challengeAbyssData

    private val _challengeGuardianData = MutableLiveData<GetChallengeGuardianData?>()
    val challengeGuardianData: LiveData<GetChallengeGuardianData?> get() = _challengeGuardianData

    private val _error = MutableLiveData<String>()
    val error: MutableLiveData<String> get() = _error

    fun getInformationData() {
        viewModelScope.launch {
            if (GlobalVariable.news == null) {
                LoaApi().getNews { newsIt,code ->
                    if (newsIt != null) {
                        _newsData.postValue(newsIt)
                    }else{
                        _error.postValue(code)
                    }
                }
            }else{
                _newsData.postValue(GlobalVariable.news)
            }

            if (GlobalVariable.events == null) {
                LoaApi().getEvents { eventIt, code ->
                    if (eventIt != null) {
                        _eventsData.postValue(eventIt)
                    }else{
                        _error.postValue(code)
                    }
                }
            }else{
                _eventsData.postValue(GlobalVariable.events)
            }

            if (GlobalVariable.challengeAbyss == null) {
                LoaApi().getChallengeAbyss { abyssIt,code ->
                    if (abyssIt != null) {
                        _challengeAbyssData.postValue(abyssIt)
                    }else{
                        _error.postValue(code)
                    }
                }
            }else{
                _challengeAbyssData.postValue(GlobalVariable.challengeAbyss)
            }

            if (GlobalVariable.challengeGuardian == null) {
                LoaApi().getChallengeGuardian { guardianIt,code ->
                    if (guardianIt != null) {
                        _challengeGuardianData.postValue(guardianIt)
                    }else{
                        _error.postValue(code)
                    }

                }
            }else{
                _challengeGuardianData.postValue(GlobalVariable.challengeGuardian)
            }
        }
    }

}
