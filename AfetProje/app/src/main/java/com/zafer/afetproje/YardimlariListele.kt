package com.zafer.afetproje

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
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

            val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
            val collectionRef = firestore.collection("Yardimlar")

            collectionRef.get()

                .addOnSuccessListener { snapshot: QuerySnapshot ->
                    val productList = mutableListOf<String>()

                    for (document in snapshot.documents) {

                        val urun = document.getString("Urun")
                        val urunAdet = document.getString("Urun-Adet")

                        if (urun != null && urunAdet != null) {

                            val productInfo = "Ürün: $urun\nAdet: $urunAdet"
                            productList.add(productInfo)
                        }
                    }

                    val productListString = productList.joinToString("\n")
                    binding.UrunText.text = productListString
                }
                .addOnFailureListener { exception: Exception ->

                    Toast.makeText(applicationContext, "Hata: $exception", Toast.LENGTH_SHORT).show()
                }
        } catch (e: Exception) {

            Toast.makeText(applicationContext, "Beklenmedik Bir HATA Oluştu! $e", Toast.LENGTH_LONG).show()
        }
    }
}
