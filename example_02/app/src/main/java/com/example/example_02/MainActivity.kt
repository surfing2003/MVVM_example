package com.example.example_02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.example_02.Dao.AppDatabase
import com.example.example_02.factory.MailViewModelFactory
import com.example.example_02.model.InputMsg
import com.example.example_02.repository.InputMsgRepository

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        subscrobeUi()
    }

    // DI로 대체 가능
    private fun subscrobeUi(){
        val dao = AppDatabase.getInstance(this).inputMsgDao()
        val repository = InputMsgRepository.getInstance(dao)
        val factory = MailViewModelFactory(repository)
        val viewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)

        // 액티비티 생명주기와 함께
        viewModel.inputMsgs.observe(this, Observer {
            if(it == null || it.isEmpty())
                return@Observer

            var sb = StringBuffer()
            for (data in it){
                sb.append(data.msg).append("\n")
            }

            findViewById<TextView>(R.id.tv_result).text = sb.toString()
        })

        findViewById<Button>(R.id.btn_input).setOnClickListener {
            var input = findViewById<EditText>(R.id.et_input).text.toString()
            if (input == null || input.isEmpty())
                return@setOnClickListener

            findViewById<EditText>(R.id.et_input).setText("")
            viewModel.insertMsg(InputMsg(msg = input))
        }


    }
}