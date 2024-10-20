package com.kuri2024.loasearch.search.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kuri2024.loasearch.api.LoaApi
import com.kuri2024.loasearch.api.data.character.GetCharacterData

class SearchViewModel: ViewModel() {
    private val _characterData = MutableLiveData<GetCharacterData?>()
    val characterData: LiveData<GetCharacterData?> get() = _characterData

    private val _error = MutableLiveData<String>()
    val error: MutableLiveData<String> get() = _error

    fun getSearchCharacter(name:String){
      LoaApi().getCharacterData(name) { data, code ->
            if (code == "200"&&data != null){
                _characterData.postValue(data)
            }else{
                _error.postValue(code)
            }
        }
    }
}