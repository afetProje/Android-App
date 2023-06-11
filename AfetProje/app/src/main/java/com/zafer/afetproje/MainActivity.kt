package com.zafer.afetproje

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.firebase.auth.FirebaseAuth
import com.zafer.afetproje.databinding.ActivityMainBinding
import com.zafer.afetproje.databinding.ActivityRegisterBinding
import java.lang.Exception

private lateinit var binding: ActivityMainBinding
private lateinit var auth: FirebaseAuth

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        auth = FirebaseAuth.getInstance()

        try {

            val SinifNesne = Fonksiyonlar(ActivityRegisterBinding.inflate(layoutInflater))  // Fonksiyonlar Sınıfından Nesne Türettik.

            // Arkaplan ve Buton Renklendirmesi...
            val myLayout: ConstraintLayout = findViewById(R.id.MyLayout)
            val btn = binding.GirisButton
            val txt1 = binding.EmailText
            val txt2 = binding.PassText

            SinifNesne.Renklendir(btn,myLayout, txt1, txt2)

            // Buton'a tıklandığında yapılacak işlemler...
            binding.GirisButton.setOnClickListener {

                val Email = binding.EmailText.text.toString()           // EditText Verileri Değişkenlere Atandı.
                val Sifre = binding.PassText.text.toString()
                val Kontrol = SinifNesne.EditTextKontrol(Email,Sifre)  //  Değişkenler Kontrol Fonksiyonuna Gönderilip Kontrol Edildi.

                if (Kontrol){                                          // Fonksiyondan dönen sonuç true ise if bloğu çalışacak.

                    auth.signInWithEmailAndPassword(Email,Sifre).addOnSuccessListener {     // Giriş yapılan mail ve şifre veri tabanında mevcut ise...

                        val Yonlendir = Intent(this,MainActivity2::class.java)    // Yönlendirmeler yapılıyor..
                        startActivity(Yonlendir)
                        finish()

                    }.addOnFailureListener {    // İşlem başarısız ise...

                        Toast.makeText(this,it.localizedMessage,Toast.LENGTH_LONG).show()
                    }

                }else{

                    txt1.error = "Lütfen Boş Bırakmayınız !"
                    txt2.error = "Lütfen Boş Bırakmayınız !"

                    Toast.makeText(this,"Kullanıcı Adı Veya Şifre Boş Bırakılamaz !",Toast.LENGTH_LONG).show()
                }

            }

            // Kayıt Ol Yazısına tıklandığında yapılacak işlemler...
            binding.KayitText.setOnClickListener {

                binding.KayitText.isClickable = true
                binding.KayitText.movementMethod = LinkMovementMethod.getInstance()

                val registerIntent = Intent(this,RegisterActivity::class.java)
                startActivity(registerIntent)

            }
        }catch (e: Exception){

            Toast.makeText(this,"Beklenmedik Bir Hata Oluştu !" + e.toString(),Toast.LENGTH_LONG).show()

        }

    }

}