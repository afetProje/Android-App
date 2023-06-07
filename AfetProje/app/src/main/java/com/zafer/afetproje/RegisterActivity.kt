package com.zafer.afetproje

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.zafer.afetproje.databinding.ActivityRegisterBinding

private lateinit var binding:ActivityRegisterBinding
private lateinit var sharedPreferences:SharedPreferences
var KaydedilenKullanici: String ? = null

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


    }


    }
