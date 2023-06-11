package com.zafer.afetproje

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
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
    fun Renklendir(btn: Button,layout: ConstraintLayout, txt1: EditText, txt2 : EditText){

        /*val shape = GradientDrawable()
        shape.shape = GradientDrawable.RECTANGLE
        shape.cornerRadius = 50f                // Buton radius
        shape.setColor(Color.parseColor("#3F4E4F"))*/

        val txt = GradientDrawable()
        txt.shape = GradientDrawable.RECTANGLE
        txt.cornerRadius = 20f                  // Yuvarlaklık değerini burada belirleyin
        txt.setColor(Color.WHITE)               // Arka plan rengini burada belirleyin
        txt.setStroke(3, Color.BLACK)     // Kenarlık rengini ve kalınlığını burada belirleyin

        txt1.background = txt
        txt2.background = txt
        btn.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#3F4E4F"))
        layout.setBackgroundColor(Color.parseColor("#F3EFE8"))

    }

    fun Renklendir2(btn: Button,layout: ConstraintLayout, txt1: EditText, txt2 : EditText, txt3 : EditText,txt4 : EditText, txt5 : EditText, txt6 : EditText){

        /*val shape = GradientDrawable()
        shape.shape = GradientDrawable.RECTANGLE
        shape.cornerRadius = 50f                // Buton radius
        shape.setColor(Color.parseColor("#3F4E4F"))*/

        val txt = GradientDrawable()
        txt.shape = GradientDrawable.RECTANGLE
        txt.cornerRadius = 20f                  // Yuvarlaklık değerini burada belirleyin
        txt.setColor(Color.WHITE)               // Arka plan rengini burada belirleyin
        txt.setStroke(3, Color.BLACK)     // Kenarlık rengini ve kalınlığını burada belirleyin

        txt1.background = txt
        txt2.background = txt
        txt3.background = txt
        txt4.background = txt
        txt5.background = txt
        txt6.background = txt
        btn.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#3F4E4F"))
        layout.setBackgroundColor(Color.parseColor("#F3EFE8"))

    }

    fun temizleBtnSekil(btn : Button){

        btn.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#3F4E4F"))
    }

    fun YardimBtnRenk(yardimEt: Button, yardimAl : Button, yardimListele : Button, cikisYap: Button){

        yardimEt.setBackgroundColor(Color.parseColor("#E3CAA5"))
        yardimAl.setBackgroundColor(Color.parseColor("#CEAB93"))
        yardimListele.setBackgroundColor(Color.parseColor("#BC9D86"))
        cikisYap.setBackgroundColor(Color.parseColor("#E3CAA5"))

    }

}

