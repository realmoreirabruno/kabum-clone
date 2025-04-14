package com.kabumclone.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun NotificationsScreen() {
    val notificacoes = listOf(
        "Promoção relâmpago em GPUs!",
        "Seu pedido foi enviado!",
        "Nova oferta em SSDs",
        "Notebooks com até 30% OFF!"
    )

    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        items(notificacoes) { noti ->
            Card(modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)) {
                Text(
                    text = noti,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}
