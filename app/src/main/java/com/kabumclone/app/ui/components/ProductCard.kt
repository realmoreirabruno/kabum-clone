package com.kabumclone.app.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
fun ProdutoCardVertical(
    produto: Product,
    onClick: () -> Unit,
    onFavoriteClick: (Product) -> Unit,
    isFavorite: Boolean
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() }
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(produto.image),
                contentDescription = produto.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(produto.name, style = MaterialTheme.typography.titleMedium)
            Text(produto.description, style = MaterialTheme.typography.bodySmall)
            Text("R$ ${produto.price}", style = MaterialTheme.typography.bodyMedium)
            IconButton(onClick = { onFavoriteClick(produto) }, modifier = Modifier.align(Alignment.End)) {
                Icon(
                    imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                    contentDescription = if (isFavorite) "Remover dos favoritos" else "Adicionar aos favoritos",
                    tint = if (isFavorite) Color.Red else Color.Gray
                )
            }
        }
    }
}
