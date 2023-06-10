package com.zafer.afetproje

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.*
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
                val kontrol = EditTextKontrol(ad,tel)

                if (kontrol) {

                    FireBaseKaydet(ad,tel)

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

    fun FireBaseKaydet(kulAd: String, kulTel: String) {  // Realtime Database Kayıt İşlemleri..

        val database = FirebaseDatabase.getInstance()             // Database örneği oluşturuldu.
        val reference = database.getReference("Yardim-Alacaklar")  // Düğüm ismi oluşturuldu, Yani yardımlar adı altında veriler toplanacak.
        val key = reference.push().key                          // rastgele key oluşturuldu.

        if (key != null) {                                      // Key var ise...
            val data = HashMap<String, String>()               // Textlerden gelen veriler hashmap'e aktarıldı.
            data["Kullanici-Adi"] = kulAd
            data["Kullanici-Tel"] = kulTel

            reference.child(key).setValue(data)             // Oluşturulan rastgele key'in altına "data" hashmapindeki veriler isimleriyle birlikte eklendi.
                .addOnSuccessListener {             // Ekleme işlemi başarılıysa...
                    Toast.makeText(
                        applicationContext,
                        "Yardım İsteğiniz Kaydedildi, En Kısa Sürede Sizinle İletişime Geçilecektir.",
                        Toast.LENGTH_SHORT
                    ).show()
                }.addOnFailureListener {  // Ekleme işlemi Başarısızsa...
                    Toast.makeText(
                        applicationContext,
                        "Yardımızını Kaydedemedik :(",
                        Toast.LENGTH_SHORT
                    ).show()
                }
        }
    }

}


