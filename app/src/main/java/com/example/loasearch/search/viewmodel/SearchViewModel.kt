package com.example.loasearch.search.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.loasearch.api.LoaApi
import com.example.loasearch.api.data.GlobalVariable
import com.example.loasearch.api.data.character.GetCharacterData

class SearchViewModel: ViewModel() {
    private val _characterData = MutableLiveData<GetCharacterData?>()
    val characterData: LiveData<GetCharacterData?> get() = _characterData

    private val _error = MutableLiveData<String>()
    val error: MutableLiveData<String> get() = _error

    fun getSearchCharacter(name:String){
        LoaApi().getCharacterData(name) { data, code ->
            if (code == "200"){
                _characterData.postValue(data)
            }else{
                _error.postValue(code)
            }
        }
    }
}