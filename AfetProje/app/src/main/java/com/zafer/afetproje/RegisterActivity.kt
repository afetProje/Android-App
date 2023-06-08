package com.zafer.afetproje

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.text.set
import com.zafer.afetproje.databinding.ActivityRegisterBinding

private lateinit var binding:ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.KayitButton.setOnClickListener {

            if (EditTextKontrol()){

                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
                Toast.makeText(this,"Başarıyla Kayıt Oldunuz :)",Toast.LENGTH_LONG).show()
            }else{

                Toast.makeText(this,"Lütfen İstenilen Değerleri Giriniz !",Toast.LENGTH_LONG).show()
            }
        }

        binding.TemizleButton.setOnClickListener {

            Temizle()
        }


    }

    private fun EditTextKontrol():Boolean{

        val KullaniciAd = binding.KullaniciAdText.text.toString().trim()
        val KullaniciSifre = binding.SifreText.text.toString().trim()
        val KullaniciTel = binding.TelNoText.text.toString().trim()

        return KullaniciAd.isNotEmpty() && KullaniciSifre.isNotEmpty() && KullaniciTel.isNotEmpty()
    }

    private fun Temizle(){

        binding.KullaniciAdText.setText("")
        binding.SifreText.setText("")
        binding.TelNoText.setText("")
    }

 }
