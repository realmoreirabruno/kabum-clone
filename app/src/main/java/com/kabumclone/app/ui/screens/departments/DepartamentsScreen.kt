package com.kabumclone.app.ui.screens.departments

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun DepartamentsScreen(navController: NavController) {
    val departamentos = listOf(
        "Hardware", "Periféricos", "Monitores", "Componentes", "Notebooks", "Celulares"
    )

    LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        items(departamentos) { departamento ->
            Text(
                text = departamento,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp)
                    .clickable {
                        navController.navigate("subdepartamento/$departamento")
                    }
            )
        }
    }
}

