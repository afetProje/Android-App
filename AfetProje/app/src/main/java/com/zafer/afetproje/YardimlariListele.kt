package com.zafer.afetproje

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.zafer.afetproje.databinding.ActivityYardimlariListeleBinding
import java.lang.Exception

private lateinit var binding: ActivityYardimlariListeleBinding

class YardimlariListele : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityYardimlariListeleBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        try {

            val database = FirebaseDatabase.getInstance()
            val reference = database.getReference("Yardimlar")

            reference.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {

                    val productList = mutableListOf<String>()

                    for (shot in snapshot.children) {

                        val urun = shot.child("Urun").value
                        val urunAdet = shot.child("Urun-Adet").value

                        if (urunAdet != null) {

                            val productInfo = "Ürün: ${urun}\n Adet: ${urunAdet}"
                            productList.add(productInfo)
                        }

                    }

                    val productListString = productList.joinToString("\n")
                    binding.UrunText.text = productListString
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(applicationContext, "!", Toast.LENGTH_SHORT).show()
                }

            })
        } catch (e: Exception) {

            Toast.makeText(
                applicationContext,
                "Beklenmedik Bir HATA Oluştu! " + e.toString(),
                Toast.LENGTH_LONG
            ).show()
        }

    }

}