package com.kotlinegitim.rehberkayit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.kotlinegitim.rehberkayit.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.button.setOnClickListener{
            val adSoyad = binding.editTextText.text.toString()
            val number = binding.editTextPhone.text.toString()

            if (adSoyad.isEmpty()|| number.isEmpty()) {
                Toast.makeText(this, "Ad Soyad ve numara boş bırakılamaz", Toast.LENGTH_SHORT).show()
            }
            else{
                var Intent = Intent(this@MainActivity, Sonuc::class.java)
                startActivity(Intent)
            }
        }
    }
}