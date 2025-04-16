package com.kabumclone.app.ui.screens.departments

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ProductCategoryScreen(categoria: String) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Categoria: $categoria", style = MaterialTheme.typography.headlineSmall)

        // Retrofit pra pegar os produtos
        repeat(5) {
            Text("Produto $it da categoria $categoria", modifier = Modifier.padding(vertical = 8.dp))
        }
    }
}
