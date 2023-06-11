package com.zafer.afetproje

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.zafer.afetproje.databinding.ActivityMain4Binding
import java.lang.Exception

private lateinit var binding: ActivityMain4Binding

class MainActivity4 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain4Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        try {
            binding.YardimAlBtn.setOnClickListener {
                val ad = binding.AdText.text.take(30).toString()
                val tel = binding.TelText.text.take(11).toString()
                val kontrol = EditTextKontrol(ad, tel)

                if (kontrol) {
                    FireStoreKaydet(ad, tel)
                } else {
                    Toast.makeText(this, "Tüm alanları doldurun!", Toast.LENGTH_SHORT).show()
                }
            }
        } catch (e: Exception) {
            Toast.makeText(this, "Beklenmedik bir hata oluştu: $e", Toast.LENGTH_SHORT).show()
        }
    }

    private fun EditTextKontrol(Kullanici_Ad: String, Kullanici_Tel: String): Boolean {

        return Kullanici_Ad.isNotEmpty() && Kullanici_Tel.isNotEmpty()
    }

    fun FireStoreKaydet(kulAd: String, kulTel: String) {  // Firestore Kayıt İşlemleri..

        val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
        val data = hashMapOf(
            "Kullanici-Adi" to kulAd,
            "Kullanici-Tel" to kulTel
        )

        firestore.collection("Yardim-Alacaklar").add(data)

            .addOnSuccessListener {

                Toast.makeText(
                    applicationContext,
                    "Yardım İsteğiniz Kaydedildi, En Kısa Sürede Sizinle İletişime Geçilecektir.",
                    Toast.LENGTH_SHORT
                ).show()
            }
            .addOnFailureListener {
                Toast.makeText(applicationContext, "Yardımınızı Kaydedemedik :(", Toast.LENGTH_SHORT).show()
            }
    }
}
