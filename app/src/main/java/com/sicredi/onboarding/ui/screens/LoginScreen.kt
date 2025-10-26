package com.sicredi.onboarding.ui.screens


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp


@Composable
fun LoginScreen(onLogged: () -> Unit) {
    var user by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }


    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Card(Modifier.padding(24.dp)) {
            Column(Modifier.padding(24.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text("Sicredi — Acesso", style = MaterialTheme.typography.headlineSmall)
                OutlinedTextField(value = user, onValueChange = { user = it }, label = { Text("Usuário") })
                OutlinedTextField(
                    value = pass,
                    onValueChange = { pass = it },
                    label = { Text("Senha") },
                    visualTransformation = PasswordVisualTransformation()
                )
                Button(onClick = onLogged, modifier = Modifier.fillMaxWidth()) { Text("Entrar") }
                Text("*MVP: sem validação nesta etapa", style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}