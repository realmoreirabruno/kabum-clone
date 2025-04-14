package com.kabumclone.app.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.kabumclone.app.data.model.Produto

@Composable
fun ProdutoCardHorizontal(produto: Produto, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .width(160.dp)
            .padding(end = 8.dp)
            .clickable { onClick() }
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Image(
                painter = rememberAsyncImagePainter(produto.imagemUrl),
                contentDescription = produto.nome,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            )
            Text(produto.nome, style = MaterialTheme.typography.bodyMedium)
            Text("R$ ${produto.preco}", style = MaterialTheme.typography.bodyLarge)
        }
    }
}

@Composable
fun ProdutoCardVertical(produto: Produto, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() }
    ) {
        Row(modifier = Modifier.padding(8.dp)) {
            Image(
                painter = rememberAsyncImagePainter(produto.imagemUrl),
                contentDescription = produto.nome,
                modifier = Modifier
                    .size(100.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(produto.nome, style = MaterialTheme.typography.bodyMedium)
                Text("R$ ${produto.preco}", style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}
