package com.zafer.afetproje

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import com.zafer.afetproje.databinding.ActivityMain3Binding
import java.lang.Exception

private lateinit var binding: ActivityMain3Binding

class MainActivity3 : AppCompatActivity() {
    private lateinit var binding: ActivityMain3Binding
    private lateinit var spinner: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain3Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        try {
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

    fun FireBaseKaydet(urun: String, kulAd: String, kulTel: String, urunAdet: String) {  // Realtime Database Kayıt İşlemleri..

        val database = FirebaseDatabase.getInstance()             // Database örneği oluşturuldu.
        val reference = database.getReference("Yardimlar")  // Düğüm ismi oluşturuldu, Yani yardımlar adı altında veriler toplanacak.
        val key = reference.push().key                          // rastgele key oluşturuldu.

        if (key != null) {                                      // Key var ise...
            val data = HashMap<String, String>()               // Textlerden gelen veriler hashmap'e aktarıldı.
            data["Urun"] = urun
            data["Kullanici-Adi"] = kulAd
            data["Kullanici-Tel"] = kulTel
            data["Urun-Adet"] = urunAdet

            reference.child(key).setValue(data)             // Oluşturulan rastgele key'in altına "data" hashmapindeki veriler isimleriyle birlikte eklendi.
                .addOnSuccessListener {             // Ekleme işlemi başarılıysa...
                    Toast.makeText(
                        applicationContext,
                        "Yardımınız Kaydedildi, Teşekkürler :)",
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

    fun SpinnerItemSelected(parent: AdapterView<*>) {   //Spinner'deki veriler seçildiyse...

        val secilenUrun = spinner.selectedItem.toString()
        val kullaniciAd = binding.KullAdText.text.take(30).toString().trim()  // veriler sınırlandırılarak değişkenlere aktarıldı.
        val kullaniciTel = binding.KullTelText.text.take(11).toString().trim()
        val kullaniciAdet = binding.AdetText.text.take(5).toString().trim()

        if (EditTextKontrol(kullaniciAd,kullaniciTel,kullaniciAdet)) {             // Textler boş mu dolu mu kontrol edildi.
            FireBaseKaydet(secilenUrun, kullaniciAd, kullaniciTel, kullaniciAdet)  // Veri tabanına kaydetme fonksiyonuna gönderildi.
        } else {
            Toast.makeText(this, "Lütfen Boş Bırakmayınız!", Toast.LENGTH_LONG).show()  // Textler Boş ise...
        }
    }
}
