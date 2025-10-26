package com.sicredi.onboarding.data.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate


@Entity(tableName = "associates")
data class Associate(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val nome: String,
    val cpf: String,
    val email: String?,
    val telefone: String?,
    val endereco: String?,
    val rendaMensal: Double?,
    val dataNascimento: LocalDate?,
    val createdAt: Long = System.currentTimeMillis()
)