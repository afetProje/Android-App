package com.zafer.afetproje

import android.graphics.Color
import android.widget.Button
import android.widget.EditText
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet.Constraint
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

    // Butonlar ve Arkaplan Renklendirme Fonksiyonu
    fun Renklendir(btn: Button, layout: ConstraintLayout){

        btn.setBackgroundColor(Color.parseColor("#3F4E4F"))
        layout.setBackgroundColor(Color.parseColor("#F3EFE8"))

    }

    fun YardimBtnRenk(yardimEt: Button, yardimAl : Button, yardimListele : Button){

        yardimEt.setBackgroundColor(Color.parseColor("#E3CAA5"))
        yardimAl.setBackgroundColor(Color.parseColor("#CEAB93"))
        yardimListele.setBackgroundColor(Color.parseColor("#BC9D86"))

    }

}

