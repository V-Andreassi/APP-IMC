package com.example.imc

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class CalculoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculo)

        val edtPeso = findViewById<EditText>(R.id.edtPeso)
        val edtAltura = findViewById<EditText>(R.id.edtAltura)
        val btnResultado = findViewById<Button>(R.id.btnResultado)
        val btnHistorico = findViewById<Button>(R.id.btnHistorico) // novo botão
        val txtResultado = findViewById<TextView>(R.id.txtResultado)
        val imgResultado = findViewById<ImageView>(R.id.imgResultado)
        val btnVoltar = findViewById<Button>(R.id.btnVoltar)

        val db = ImcDatabase.getDatabase(this)
        val dao = db.imcDao()

        btnResultado.setOnClickListener {
            val peso = edtPeso.text.toString().toFloatOrNull()
            val altura = edtAltura.text.toString().toFloatOrNull()

            if (peso != null && altura != null && altura > 0) {
                val imc = peso / (altura * altura)
                val resultado = when {
                    imc < 18.5 -> {
                        imgResultado.setImageResource(R.drawable.magreza)
                        "Abaixo do peso"
                    }
                    imc < 24.9 -> {
                        imgResultado.setImageResource(R.drawable.normal)
                        "Peso ideal"
                    }
                    imc < 29.9 -> {
                        imgResultado.setImageResource(R.drawable.sobrepeso)
                        "Sobrepeso"
                    }
                    else -> {
                        imgResultado.setImageResource(R.drawable.obesidade)
                        "Obesidade"
                    }
                }

                txtResultado.text = "IMC: %.2f\n$resultado".format(imc)

                // Salvar no banco de dados
                val dataAtual = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())

                lifecycleScope.launch {
                    val registro = ImcRegistro(
                        peso = peso,
                        altura = altura,
                        resultado = resultado,
                        data = dataAtual
                    )
                    dao.inserir(registro)
                }
            } else {
                Toast.makeText(this, "Preencha os dados corretamente", Toast.LENGTH_SHORT).show()
            }
        }

        btnHistorico.setOnClickListener {
            val intent = Intent(this, activity_historico::class.java)
            startActivity(intent)
        }

        btnVoltar.setOnClickListener {
            finish()
        }
    }
}
