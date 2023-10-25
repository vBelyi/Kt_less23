package com.example.kt_less23

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import javax.inject.Inject
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@HiltViewModel
class ViewModel @Inject constructor(private val repo: Repository): ViewModel() {
    private val _uiState = MutableLiveData<UIState>(UIState.Empty)
    val uiState: LiveData<UIState> = _uiState

    fun getData() {
        _uiState.value = UIState.Processing
        MainScope().launch {
            withContext(Dispatchers.IO) {
                try {
                    val bitcoin = repo.getCrypto("bitcoin")
                    if (bitcoin.isSuccessful) {
                        val dataCrypto = bitcoin.body()?.data
                        _uiState.postValue(UIState.Result("${dataCrypto?.id} ${dataCrypto?.currencySymbol} ${dataCrypto?.rateUsd}"))
                    } else _uiState.postValue(UIState.Error("Error code ${bitcoin.code()}"))
                } catch (e: Exception) {
                    _uiState.postValue(UIState.Error(e.localizedMessage))
                }
            }
        }
    }

    sealed class UIState {
        data object Empty:UIState()
        data object Processing: UIState()
        class Result(val title: String): UIState()
        class Error (val description: String) : UIState()
    }
}