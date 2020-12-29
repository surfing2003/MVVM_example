package com.example.example_01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import com.example.example_01.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    val liveText = MutableLiveData<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        binding.apply{
            lifecycleOwner = this@MainActivity
            activity = this@MainActivity

            btnChange1.setOnClickListener{
                liveText.value = "Hello LiveData!"
            }

            btnChange2.setOnClickListener{
                liveText.value = "Hello DataBinding!"
            }
        }

        liveText.value = "Hello DataBinding!"
    }
}


