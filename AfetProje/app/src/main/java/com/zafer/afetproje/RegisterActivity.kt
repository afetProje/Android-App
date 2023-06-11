package com.zafer.afetproje

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.firebase.auth.FirebaseAuth
import com.zafer.afetproje.databinding.ActivityRegisterBinding
import java.lang.Exception

private lateinit var binding: ActivityRegisterBinding
private lateinit var auth: FirebaseAuth

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        auth = FirebaseAuth.getInstance()       // FireBase Authentication İşlemleri İçin Bir Nesne Türettik.

        try {

            val SinifNesne = Fonksiyonlar(binding)  //Fonksiyonlar Sınıfından Nesne Türettik.
            val myLayout: ConstraintLayout = findViewById(R.id.MyLayout2)
            val txt1 = binding.MailText             // Arayüzdeki bileşenlerin id'leri değişkenlere atandı.
            val txt2 = binding.SifreText
            val btn = binding.KayitButton
            val temizleBtn = binding.TemizleButton

            SinifNesne.Renklendir(btn,myLayout,txt1,txt2)   // Değişkenler sınıf içerisindeki fonksiyonlara gönderildi.
            SinifNesne.temizleBtnSekil(temizleBtn)

            binding.KayitButton.setOnClickListener { // Kayıt Ol Butonuna Tıklandığında Yapılacak İşlemler

                val KullMail = binding.MailText.text.toString()
                val KullSifre = binding.SifreText.text.toString()
                val Kontrol = SinifNesne.EditTextKontrol(KullMail,KullSifre)

                if (Kontrol) {

                    auth.createUserWithEmailAndPassword(KullMail,KullSifre).addOnSuccessListener { // Verilen Parametrelerle Kullanıcı Oluşturuldu.

                        // Kayıt İşlemi Bittikten Sonra MainActivity, Yani Giriş Sayfasına Yönlendirildi.
                        val intent = Intent(this, MainActivity::class.java)                       // Giriş Sayfasına Yönlendirme İşlemi.
                        startActivity(intent)
                        Toast.makeText(this, "Başarıyla Kayıt Oldunuz :)", Toast.LENGTH_LONG).show()  // Ekranın Altında Çıkan Bildirim mesajı.
                        finish()

                    }.addOnFailureListener {
                        Toast.makeText(this,it.localizedMessage, Toast.LENGTH_LONG).show()  // Kayıt Ekleme İşlemi Başarısızsa Çalışacak.

                    }


                } else {
                    // Textler Boş İse Verilecek Hata Bildirimi.
                    txt1.error = "Lütfen Boş Bırakmayınız !"
                    txt2.error = "Lütfen Boş Bırakmayınız !"
                    Toast.makeText(this, "Lütfen İstenilen Değerleri Giriniz !", Toast.LENGTH_LONG).show()
                }
            }

            binding.TemizleButton.setOnClickListener {

                SinifNesne.Temizle(binding.MailText,binding.SifreText)
            }

        }catch (e: Exception){

            Toast.makeText(this, "Beklenmedik Bir Hata Oluştu !" + e.toString(), Toast.LENGTH_LONG).show()

        }

    }
}

