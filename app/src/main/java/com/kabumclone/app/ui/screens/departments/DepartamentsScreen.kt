package com.kabumclone.app.ui.screens.departments

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kabumclone.app.data.model.Departament

@Composable
fun DepartamentsScreen(
    onSubcategoriaClick: (String) -> Unit
) {
    val departaments = listOf(
        Departament("Hardware", listOf("Placas de Vídeo", "Processadores", "Memórias RAM", "SSDs")),
        Departament("Celulares", listOf("Smartphones", "Acessórios", "Carregadores")),
        Departament("TVs", listOf("Smart TVs", "Android TV", "4K UHD")),
        Departament("Áudio", listOf("Headsets", "Caixas de Som", "Fones Bluetooth")),
        Departament("Tablets", listOf("Android", "iPad", "Windows")),
        Departament("Periféricos", listOf("Teclados", "Mouses", "Webcams"))
    )

    var expandedDepartamento by remember { mutableStateOf<String?>(null) }

    LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        items(departaments) { departament ->
            Column(modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    expandedDepartamento = if (expandedDepartamento == departament.nome) null else departament.nome
                }
                .padding(vertical = 8.dp)
            ) {
                Text(text = departament.nome, style = MaterialTheme.typography.titleMedium)

                if (expandedDepartamento == departament.nome) {
                    Spacer(modifier = Modifier.height(4.dp))
                    departament.subcategorias.forEach { sub ->
                        Text(
                            text = "• $sub",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 16.dp, top = 4.dp, bottom = 4.dp)
                                .clickable { onSubcategoriaClick(sub) },
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }
    }
}


