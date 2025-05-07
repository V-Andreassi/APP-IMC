package com.example.imc

import androidx.room.*

@Dao
interface ImcDao {
    @Insert
    suspend fun inserir(registro: ImcRegistro)

    @Query("SELECT * FROM imc_registro ORDER BY id DESC")
    suspend fun listarTodos(): List<ImcRegistro>

    @Delete
    suspend fun deletar(registro: ImcRegistro)

    @Query("DELETE FROM imc_registro")
    suspend fun deletarTodos()

}
