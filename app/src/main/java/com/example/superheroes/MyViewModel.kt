package com.example.superheroes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MyViewModel : ViewModel() {
    private val _uiState = MutableLiveData<UIState>(UIState.Empty)
    val uiState: LiveData<UIState> = _uiState
    private val repo = MyApplication.getApp().repo
    fun getData() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val response = repo.getSuperheroes()
                if (response.isNotEmpty()) {
                    withContext(Dispatchers.Main) {
                        _uiState.postValue(
                            UIState.Result(response))
                    }
                } else
                    _uiState.postValue(UIState.Error("Error"))
            }
        }
    }
    sealed class UIState {
        data object Empty : UIState()
        class Result(val superheroes: List<Superhero>) : UIState()
        class Error(val description: String) : UIState()
    }
}
