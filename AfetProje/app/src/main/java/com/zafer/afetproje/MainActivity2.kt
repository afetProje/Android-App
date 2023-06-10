package com.zafer.afetproje

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.zafer.afetproje.databinding.ActivityMain2Binding
import com.zafer.afetproje.databinding.ActivityMain3Binding
import java.lang.Exception


private lateinit var binding: ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        try {

            Toast.makeText(this@MainActivity2,"Hos Geldiniz :)",Toast.LENGTH_LONG).show()

            binding.YardimEtButton.setBackgroundColor(Color.BLUE)
            binding.YardimAlButton.setBackgroundColor(Color.RED)
            binding.ListeleButton.setBackgroundColor(Color.YELLOW)

            binding.YardimEtButton.setOnClickListener {

                val intent = Intent(this,MainActivity3::class.java)
                startActivity(intent)
            }

            binding.YardimAlButton.setOnClickListener {

                val intent = Intent(this,MainActivity4::class.java)
                startActivity(intent)
            }

            binding.ListeleButton.setOnClickListener {

                val intent = Intent(this,YardimlariListele::class.java)
                startActivity(intent)
            }

        }catch (e: Exception){

            Toast.makeText(this,"Beklenmedik Bir HATA Oluştu !" + e.toString(),Toast.LENGTH_LONG).show()

        }



    }
}