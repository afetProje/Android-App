package com.zafer.afetproje

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.zafer.afetproje.databinding.ActivityMain3Binding

private lateinit var binding: ActivityMain3Binding

class MainActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain3Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.YardimButton.setOnClickListener {

            if (EditTextKontrol()){

                //....
            }else{
                Toast.makeText(this,"Lütfen Boş Bırakmayınız !",Toast.LENGTH_LONG).show()
            }
        }

    }

    private fun EditTextKontrol():Boolean{

        val KullaniciAd = binding.KullAdText.text.toString().trim()
        val KullaniciSifre = binding.KullTelText.text.toString().trim()
        val KullaniciAdet = binding.KullTelText.text.toString().trim()

        return KullaniciAd.isNotEmpty() && KullaniciSifre.isNotEmpty() && KullaniciAdet.isNotEmpty()
    }
}