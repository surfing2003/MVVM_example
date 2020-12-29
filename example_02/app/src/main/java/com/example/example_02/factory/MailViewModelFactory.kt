package com.example.example_02.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.example_02.MainViewModel
import com.example.example_02.repository.InputMsgRepository


class MailViewModelFactory(
    private val inputMsgRepository: InputMsgRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(inputMsgRepository) as T
    }
}