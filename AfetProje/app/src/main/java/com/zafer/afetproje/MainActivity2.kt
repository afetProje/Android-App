package com.zafer.afetproje

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.zafer.afetproje.databinding.ActivityMain2Binding
import com.zafer.afetproje.databinding.ActivityMain3Binding


private lateinit var binding: ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        Toast.makeText(this@MainActivity2,"Hos Geldiniz :)",Toast.LENGTH_LONG).show()

        binding.YardimEtButton.setBackgroundColor(Color.BLUE)
        binding.YardimAlButton.setBackgroundColor(Color.RED)

        binding.YardimEtButton.setOnClickListener {

            val intent2 = Intent(this,MainActivity3::class.java)
            startActivity(intent2)
        }

        binding.YardimAlButton.setOnClickListener {

            val intent3 = Intent(this,MainActivity4::class.java)
            startActivity(intent3)
        }


    }
}