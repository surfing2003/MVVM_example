package com.example.example_02

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.example_02.model.InputMsg
import com.example.example_02.repository.InputMsgRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainViewModel(
    private val inputMsgRepository: InputMsgRepository
) : ViewModel() {

    var TAG = javaClass.simpleName

    var inputMsgs: LiveData<List<InputMsg>> = inputMsgRepository.getAllMsgs()

    private val viewModelJob = Job()
    private val viewModelScope = CoroutineScope(Main + viewModelJob)

    fun insertMsg(inputMsg: InputMsg){
        viewModelScope.launch { inputMsgRepository.insert(inputMsg) }
    }
}