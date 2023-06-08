package com.zafer.afetproje

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.zafer.afetproje.databinding.ActivityMainBinding

private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Buton'a tıklandığında yapılacak işlemler...
        binding.button.setOnClickListener {

            if (EditTextKontrol()){

                val intent = Intent(applicationContext,MainActivity2::class.java)
                startActivity(intent)
            }else{

                Toast.makeText(this,"Kullanıcı Adı Veya Şifre Boş Bırakılamaz !",Toast.LENGTH_LONG).show()
            }

        }

        // Kayıt Ol Yazısına tıklandığında yapılacak işlemler...
        binding.registerTextView.setOnClickListener {

            binding.registerTextView.isClickable = true
            binding.registerTextView.movementMethod = LinkMovementMethod.getInstance()

            val registerIntent = Intent(this,RegisterActivity::class.java)
            startActivity(registerIntent)

        }

    }

    // EditText kontrolü yapan fonksiyon
    fun EditTextKontrol():Boolean{

        val KullaniciAd = binding.editText.text.toString().trim()
        val KullaniciSifre = binding.editTextPassword.text.toString().trim()

        return KullaniciAd.isNotEmpty() && KullaniciSifre.isNotEmpty()
    }
}