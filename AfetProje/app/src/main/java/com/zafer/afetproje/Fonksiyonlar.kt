package com.zafer.afetproje

import android.widget.EditText
import com.zafer.afetproje.databinding.ActivityRegisterBinding
import org.mindrot.jbcrypt.BCrypt

class Fonksiyonlar(private val binding: ActivityRegisterBinding) {

    //Textleri Kontrol Eden Fonksiyon
    fun EditTextKontrol(Kullanici_Mail: String, Kullanici_Sifre: String): Boolean {

        return Kullanici_Mail.isNotEmpty() && Kullanici_Sifre.isNotEmpty()
    }

    // Textleri Temizleme Fonksiyonu
    fun Temizle(Param1: EditText, Param2: EditText) {

        Param1.setText("")
        Param2.setText("")
    }

    // Hash Fonksiyonu
    fun HashPass(password: String): String {

        val salt = BCrypt.gensalt()
        return BCrypt.hashpw(password, salt)
    }

}

