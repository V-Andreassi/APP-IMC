package com.example.imc

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnCalcular = findViewById<Button>(R.id.btnCalcular)
        val btnHistorico = findViewById<Button>(R.id.btnHistorico)

        btnCalcular.setOnClickListener {
            startActivity(Intent(this, CalculoActivity::class.java))
        }

        btnHistorico.setOnClickListener {
            startActivity(Intent(this, activity_historico::class.java))
        }
    }
}
