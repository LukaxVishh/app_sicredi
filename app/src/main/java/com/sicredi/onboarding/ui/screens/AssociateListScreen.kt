package com.sicredi.onboarding.ui.screens


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sicredi.onboarding.data.model.Associate
import com.sicredi.onboarding.viewmodel.AssociateViewModel
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.Arrangement


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AssociateListScreen(
    viewModel: AssociateViewModel,
    onAdd: () -> Unit,
    onEdit: (Long) -> Unit
) {
    val state by viewModel.uiState.collectAsState()


    Scaffold(
        topBar = { TopAppBar(title = { Text("Possíveis Associados") }) },
        floatingActionButton = {
            FloatingActionButton(onClick = onAdd) { Text("+") }
        }
    ) { padding ->
        if (state.items.isEmpty()) {
            Box(Modifier.fillMaxSize().padding(padding), contentAlignment = Alignment.Center) {
                Text("Nenhum cadastro ainda. Toque em + para adicionar.")
            }
        } else {
            LazyColumn(Modifier.padding(padding).fillMaxSize(), contentPadding = PaddingValues(12.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(state.items) { a -> AssociateItem(a, onEdit = { onEdit(a.id) }, onDelete = { viewModel.delete(a) }) }
            }
        }
    }
}


@Composable
private fun AssociateItem(a: Associate, onEdit: () -> Unit, onDelete: () -> Unit) {
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp)) {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(a.nome, style = MaterialTheme.typography.titleMedium, modifier = Modifier.clickable { onEdit() })
                TextButton(onClick = onDelete) { Text("Excluir") }
            }
            a.cpf.takeIf { it.isNotBlank() }?.let { Text("CPF: $it") }
            a.email?.let { Text("Email: $it") }
            a.telefone?.let { Text("Telefone: $it") }
        }
    }
}