package com.zafer.afetproje

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.google.firebase.database.*
import com.zafer.afetproje.databinding.ActivityMain4Binding
import java.lang.Exception

private lateinit var binding: ActivityMain4Binding
val database = FirebaseDatabase.getInstance()
val reference = database.getReference("Yardimlar")

class MainActivity4 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain4Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        try {
            val ad = binding.AdText.text.take(30).toString()
            val tel = binding.TelText.text.take(11).toString()
            val adet = binding.YardimAdetText.text.take(5).toString()
            val kontrol = EditTextKontrol(ad,tel,adet)

            binding.YardimAlBtn.setOnClickListener {
                if (kontrol) {
                    YardimAl()
                } else {
                    Toast.makeText(this, "Tüm alanları doldurun!", Toast.LENGTH_SHORT).show()
                }
            }

            SpinnerVeriEkle()

        } catch (e: Exception) {
            Toast.makeText(this, "Beklenmedik bir hata oluştu: $e", Toast.LENGTH_SHORT).show()
        }
    }

    private fun EditTextKontrol(Kullanici_Ad: String, Kullanici_Tel: String, Urun_Adet: String): Boolean {
        return Kullanici_Ad.isNotEmpty() && Kullanici_Tel.isNotEmpty() && Urun_Adet.isNotEmpty()
    }

    private fun YardimAl() {
        val secilenVeri = binding.Spinner2.selectedItem.toString()
        val yardimRef = reference.child(secilenVeri)

        yardimRef.runTransaction(object : Transaction.Handler {
            override fun doTransaction(currentData: MutableData): Transaction.Result {
                val value = currentData.getValue(Int::class.java) ?: 0
                if (value > 0) {
                    currentData.value = value - 1
                    return Transaction.success(currentData)
                }
                return Transaction.abort()
            }

            override fun onComplete(
                error: DatabaseError?,
                committed: Boolean,
                currentData: DataSnapshot?
            ) {
                if (committed) {
                    Toast.makeText(applicationContext, "Yardım alındı.", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(applicationContext, "Yardım alınamadı.", Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun SpinnerVeriEkle() {
        val spinner = binding.Spinner2
        val veriSeti: ArrayList<String> = ArrayList()

        reference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                veriSeti.clear() // Önceki verileri temizleyin

                for (shot in snapshot.children) {
                    val veri = shot.key.toString()
                    veriSeti.add(veri)
                }

                val adapter: ArrayAdapter<String> = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_item, veriSeti)
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinner.adapter = adapter
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(applicationContext, "Veriler alınamadı.", Toast.LENGTH_LONG).show()

            }
        })
    }
}


