package com.zafer.afetproje

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.firebase.firestore.FirebaseFirestore
import com.zafer.afetproje.databinding.ActivityMain4Binding
import com.zafer.afetproje.databinding.ActivityRegisterBinding
import java.lang.Exception
import java.util.UUID

private lateinit var binding: ActivityMain4Binding

class MainActivity4 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain4Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        try {

            val sinifNesne = Fonksiyonlar(ActivityRegisterBinding.inflate(layoutInflater))
            val myLayout: ConstraintLayout = findViewById(R.id.MyLayout4)
            val kad = binding.AdText
            val ktc = binding.TcText
            val telf = binding.TelText
            val shr = binding.SehirText
            val malze = binding.IhtiyacText
            val adt = binding.AdtText
            val btn = binding.YardimAlBtn

            sinifNesne.Renklendir2(btn,myLayout,kad,ktc,telf,shr,malze,adt)

            // Yardim al butonuna basıldıysa...
            binding.YardimAlBtn.setOnClickListener {

                val ad = binding.AdText.text.take(30).toString()   // Arayüzdeki veriler sınırlandırıldı ve değişkenlere atandı.
                val tc = binding.TcText.text.take(11).toString()
                val tel = binding.TelText.text.take(11).toString()
                val sehir = binding.SehirText.text.take(20).toString()
                val malzeme = binding.IhtiyacText.text.take(30).toString()
                val adet = binding.AdtText.text.take(5).toString()


                val kontrol = EditTextKontrol(ad,tc,tel,sehir,malzeme,adet)   // Textler boş mu dolu mu kontrol edildi.

                if (kontrol) {                                               // Textler dolu ise...

                    FireStoreKaydet(ad,tc,tel,sehir,malzeme,adet)

                } else {                                                    // Textler boş ise

                    kad.error = "Lütfen Boş Bırakmayınız!"
                    ktc.error = "Lütfen Boş Bırakmayınız!"
                    telf.error = "Lütfen Boş Bırakmayınız!"
                    shr.error  = "Lütfen Boş Bırakmayınız!"
                    malze.error = "Lütfen Boş Bırakmayınız!"
                    adt.error = "Lütfen Boş Bırakmayınız!"
                    Toast.makeText(this, "Tüm alanları doldurun!", Toast.LENGTH_SHORT).show()
                }
            }
        } catch (e: Exception) {
            Toast.makeText(this, "Beklenmedik bir hata oluştu: $e", Toast.LENGTH_SHORT).show()
        }
    }

    // Textleri kontrol eden fonksiyon
    private fun EditTextKontrol(kulAd: String, kulTc: String, kulTel: String, kulSehir: String, kulIhtiyac: String, adet: String ): Boolean {

        return kulAd.isNotEmpty() && kulTc.isNotEmpty() && kulTel.isNotEmpty() && kulSehir.isNotEmpty() && kulIhtiyac.isNotEmpty() && adet.isNotEmpty()
    }

    // Veri tabanına kayıt yapan fonksiyon
    fun FireStoreKaydet(kulAd: String, kulTc: String, kulTel: String, kulSehir: String, kulIhtiyac: String, adet: String ) {  // Firestore Kayıt İşlemleri..

        val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

       /* val data = hashMapOf(
            "Kullanici-Adi" to kulAd,
            "Kullanici-Tel" to kulTel
        )

        firestore.collection("Yardim-Alacaklar").add(data)*/


        val helpID = UUID.randomUUID().toString()
        val data = hashMapOf(
            "name" to kulAd,
            "tc" to kulTc,
            "phone" to kulTel,
            "location" to kulSehir,
            "need" to kulIhtiyac,
            "piece" to adet,
            "helpID" to helpID
        )

         firestore.collection("helps").document("ongoing_help_need").collection("helps").document(helpID).set(data)

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
