package com.example.example_02.repository

import com.example.example_02.Dao.InputMsgDao
import com.example.example_02.model.InputMsg
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class InputMsgRepository private constructor(private val inputMsgDao: InputMsgDao) {
    fun getAllMsgs() = inputMsgDao.getAll()

    suspend fun insert(inputMsg: InputMsg) {
        withContext(Dispatchers.IO) {
            inputMsgDao.insertMsg(inputMsg)
        }
    }

    companion object {
        @Volatile
        private var instance: InputMsgRepository? = null

        fun getInstance(inputMsgDao: InputMsgDao) =
            instance ?: synchronized(this) {
                instance ?: InputMsgRepository(inputMsgDao).also { instance = it }
            }

    }
}