package com.example.imc

import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

class activity_historico : AppCompatActivity() {
    private lateinit var dao: ImcDao
    private lateinit var recycler: RecyclerView
    private lateinit var adapter: HistoricoAdapter
    private val lista: MutableList<ImcRegistro> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historico)

        recycler = findViewById(R.id.recyclerViewHistorico)
        val btnVoltar = findViewById<Button>(R.id.btnVoltarHistorico)
        val btnLimpar = findViewById<Button>(R.id.btnLimparHistorico)

        val controller = AnimationUtils.loadLayoutAnimation(this, R.anim.layout_animation_fall_down)
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.layoutAnimation = controller

        val db = ImcDatabase.getDatabase(this)
        dao = db.imcDao()

        adapter = HistoricoAdapter(lista)
        recycler.adapter = adapter

        carregarHistorico()

        btnVoltar.setOnClickListener { finish() }

        btnLimpar.setOnClickListener {
            val alert = AlertDialog.Builder(this)
            alert.setTitle("Confirmar exclusão")
            alert.setMessage("Tem certeza que deseja apagar todo o histórico?")
            alert.setIcon(android.R.drawable.ic_dialog_alert)
            alert.setPositiveButton("Sim") { _, _ ->
                lifecycleScope.launch {
                    dao.deletarTodos()
                    lista.clear()
                    adapter.notifyDataSetChanged()
                    Toast.makeText(this@activity_historico, "Histórico apagado com sucesso!", Toast.LENGTH_SHORT).show()
                }
            }
            alert.setNegativeButton("Cancelar", null)
            alert.show()
        }
    }

    private fun carregarHistorico() {
        lifecycleScope.launch {
            lista.clear()
            lista.addAll(dao.listarTodos())
            adapter.notifyDataSetChanged()
            recycler.scheduleLayoutAnimation()
        }
    }
}
