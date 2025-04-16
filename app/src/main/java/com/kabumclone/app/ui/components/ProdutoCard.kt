package com.kabumclone.app.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.kabumclone.app.data.model.Product

@Composable
fun ProdutoCardHorizontal(produto: Product, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .width(160.dp)
            .padding(end = 8.dp)
            .clickable { onClick() }
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Image(
                painter = rememberAsyncImagePainter(produto.image),
                contentDescription = produto.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            )
            Text(produto.name, style = MaterialTheme.typography.bodyMedium)
            Text("R$ ${produto.price}", style = MaterialTheme.typography.bodyLarge)
        }
    }
}

@Composable
fun ProdutoCardVertical(produto: Product, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() }
    ) {
        Row(modifier = Modifier.padding(8.dp)) {
            Image(
                painter = rememberAsyncImagePainter(produto.image),
                contentDescription = produto.name,
                modifier = Modifier
                    .size(100.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(produto.name, style = MaterialTheme.typography.bodyMedium)
                Text("R$ ${produto.price}", style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}
