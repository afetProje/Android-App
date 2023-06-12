package com.zafer.afetproje

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.firebase.firestore.FirebaseFirestore
import com.zafer.afetproje.databinding.ActivityMain3Binding
import com.zafer.afetproje.databinding.ActivityRegisterBinding
import java.lang.Exception
import java.util.UUID

private lateinit var binding: ActivityMain3Binding

class MainActivity3 : AppCompatActivity() {
    private lateinit var binding: ActivityMain3Binding
    private lateinit var spinner: Spinner
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain3Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        try {

            val ad = binding.KullAdText
            val tel = binding.KullTelText                   // Arayüzdeki bileşenler id'leri üzerinden değişkenlere atandı.
            val btn = binding.YardimButton
            val sinifNesne = Fonksiyonlar(ActivityRegisterBinding.inflate(layoutInflater))   // Sınıftan nesne türetildi.
            val myLayout : ConstraintLayout = findViewById(R.id.MyLayout5)

            sinifNesne.Renklendir(btn,myLayout,ad,tel)

            spinner = findViewById(R.id.Spinner)  // Spinner id'si alındı.
            val spinnerData = arrayOf("Ayakkabı", "Pantolon", "Şapka", "Bere", "Yorgan", "Yastık")  // spinner'de gözükecek veriler girildi.
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerData)  // Veriler spinner'e ekleniyor.
            spinner.adapter = adapter

            binding.YardimButton.setOnClickListener {

                SpinnerItemSelected(spinner)
            }
        } catch (e: Exception) {    // Program çöker ise..
            Toast.makeText(this, "Beklenmedik Bir HATA Oluştu !!" + e.toString(), Toast.LENGTH_SHORT).show()
        }

    }

    fun EditTextKontrol(Kullanici_Ad: String, Kullanici_Tel: String, Urun_Adet: String): Boolean { // Textleri Kontrol Eden fonksiyon

        return Kullanici_Ad.isNotEmpty() && Kullanici_Tel.isNotEmpty() && Urun_Adet.isNotEmpty()
    }

    fun FireStoreKaydet(name: String, phone: String, need: String, piece: String) {  // Firestore Kayıt İşlemleri..

        val helpID = UUID.randomUUID().toString()
        val data = hashMapOf(
            "name" to name,
            "phone" to phone,
            "need" to need,
            "piece" to piece,
            "helpID" to helpID
        )


        firestore.collection("helps").document("ongoing_need").collection("helps").document(helpID).set(data)


            .addOnSuccessListener {
                Toast.makeText(applicationContext, "Yardımınız Kaydedildi, Teşekkürler :)", Toast.LENGTH_SHORT).show()
            }

            .addOnFailureListener {
                Toast.makeText(applicationContext, "Yardımızı Kaydedemedik :(", Toast.LENGTH_SHORT).show()
            }
    }

    fun SpinnerItemSelected(parent: AdapterView<*>) {   //Spinner'deki veriler seçildiyse...

        val secilenUrun = spinner.selectedItem.toString()
        val kullaniciAd = binding.KullAdText.text.take(30).toString().trim()  // veriler sınırlandırılarak değişkenlere aktarıldı.
        val kullaniciTel = binding.KullTelText.text.take(11).toString().trim()
        val kullaniciAdet = binding.AdetText.text.take(5).toString().trim()

        if (EditTextKontrol(kullaniciAd, kullaniciTel, kullaniciAdet)) {

            FireStoreKaydet(kullaniciAd,kullaniciTel,secilenUrun,kullaniciAdet) // Firestore'a kaydetme fonksiyonuna gönderiliyor.

        }else{
            binding.KullAdText.error = "Lütfen Boş Bırakmayınız !"
            binding.KullTelText.error = "Lütfen Boş Bırakmayınız !"
            binding.AdetText.error = "Lütfen Boş Bırakmayınız !"
            Toast.makeText(this, "Lütfen Boş Bırakmayınız!", Toast.LENGTH_LONG).show() // Text alanları boşsa...

        }

    }
}





