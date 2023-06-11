package com.zafer.afetproje

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.zafer.afetproje.databinding.ActivityMain2Binding
import com.zafer.afetproje.databinding.ActivityMain3Binding
import com.zafer.afetproje.databinding.ActivityRegisterBinding
import java.lang.Exception


private lateinit var binding: ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        try {

            val sinifNesne = Fonksiyonlar(ActivityRegisterBinding.inflate(layoutInflater))

            val btn = binding.YardimEtButton
            val btn2 = binding.YardimAlButton
            val btn3 = binding.ListeleButton

            sinifNesne.YardimBtnRenk(btn,btn2,btn3)

            Toast.makeText(this,"Hos Geldiniz :)",Toast.LENGTH_LONG).show()


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