package com.sicredi.onboarding.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.sicredi.onboarding.viewmodel.AssociateViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.ResolverStyle
import java.util.Locale


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AssociateFormScreen(
    viewModel: AssociateViewModel,
    associateId: Long?,
    onDone: () -> Unit
) {
    val scope = rememberCoroutineScope()

    // Estados dos campos
    var nome by remember { mutableStateOf("") }
    var cpf by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var telefone by remember { mutableStateOf("") }
    var endereco by remember { mutableStateOf("") }
    var rendaTxt by remember { mutableStateOf("") }
    var nascimentoTxt by remember { mutableStateOf("") } // texto livre; vamos converter com segurança

    // Carrega dados se for edição
    LaunchedEffect(associateId) {
        if (associateId != null) {
            viewModel.load(associateId) { a ->
                a?.let {
                    nome = it.nome
                    cpf = it.cpf
                    email = it.email.orEmpty()
                    telefone = it.telefone.orEmpty()
                    endereco = it.endereco.orEmpty()
                    rendaTxt = it.rendaMensal?.let { v -> formatDoubleForEdit(v) }.orEmpty()
                    nascimentoTxt = it.dataNascimento?.let { d -> d.format(DateTimeFormatter.ofPattern("dd/MM/uuuu")) }.orEmpty()
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Cadastro de Associado")

        OutlinedTextField(
            value = nome, onValueChange = { nome = it },
            label = { Text("Nome") }, modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = cpf, onValueChange = { cpf = it },
            label = { Text("CPF") }, modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        OutlinedTextField(
            value = email, onValueChange = { email = it },
            label = { Text("Email") }, modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )

        OutlinedTextField(
            value = telefone, onValueChange = { telefone = it },
            label = { Text("Telefone") }, modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = endereco, onValueChange = { endereco = it },
            label = { Text("Endereço") }, modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = rendaTxt, onValueChange = { rendaTxt = it },
            label = { Text("Renda mensal (ex: 2500,00)") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
        )

        OutlinedTextField(
            value = nascimentoTxt, onValueChange = { nascimentoTxt = it },
            label = { Text("Nascimento (dd/MM/aaaa ou ddMMyyyy)") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Spacer(Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = {
                    scope.launch {
                        // ✅ CONVERSÕES SEGURAS (sem crash)
                        val renda = parseDoubleOrNull(rendaTxt)
                        val nascimento = parseDateOrNull(nascimentoTxt)

                        viewModel.save(
                            id = associateId,
                            nome = nome,
                            cpf = cpf,
                            email = email.ifBlank { null },
                            telefone = telefone.ifBlank { null },
                            endereco = endereco.ifBlank { null },
                            renda = renda,                 // null se inválido
                            nascimento = nascimento        // null se inválido
                        )
                        onDone()
                    }
                }
            ) {
                Text(if (associateId == null) "Salvar" else "Atualizar")
            }

            Button(onClick = { onDone() }) {
                Text("Cancelar")
            }
        }
    }
}

/* ========================= helpers ========================= */

private fun parseDoubleOrNull(text: String): Double? {
    val t = text.trim()
    if (t.isEmpty()) return null
    // aceita "1.234,56" ou "1234.56" — vamos normalizar para ponto como separador decimal
    val normalized = t
        .replace(".", "")      // remove separador de milhar
        .replace(",", ".")     // usa ponto como decimal
    return normalized.toDoubleOrNull()
}

private fun parseDateOrNull(text: String): LocalDate? {
    val raw = text.trim()
    if (raw.isEmpty()) return null

    val locale = Locale("pt", "BR")
    val formats = listOf(
        DateTimeFormatter.ofPattern("dd/MM/uuuu", locale)
            .withResolverStyle(ResolverStyle.STRICT),
        DateTimeFormatter.ofPattern("ddMMyyyy", locale)
            .withResolverStyle(ResolverStyle.STRICT)
    )

    for (f in formats) {
        runCatching { return LocalDate.parse(raw, f) }
    }
    return null // inválida -> trata como ausente no MVP
}

private fun formatDoubleForEdit(v: Double): String {
    // simples: mostra com vírgula brasileira
    val s = "%,.2f".format(Locale("pt", "BR"), v)
    // Em muitas Locales a vírgula já vem certa, mas garantimos:
    return s.replace(",", "X").replace(".", ",").replace("X", ".")
}
