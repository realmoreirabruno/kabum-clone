package com.kabumclone.app.ui.screens.product

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.kabumclone.app.viewmodel.CartViewModel
import com.kabumclone.app.viewmodel.ProductDetailsViewModel

@Composable
fun ProductDetailsScreen(
    navController: NavController,
    produtoId: String,
    detalhesViewModel: ProductDetailsViewModel = viewModel(),
    cartViewModel: CartViewModel = viewModel()
) {
    val produto by detalhesViewModel.produto.collectAsState()

    LaunchedEffect(Unit) {
        detalhesViewModel.carregarProduto(produtoId)
    }

    val context = LocalContext.current

    if (produto == null) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(produto!!.imagemUrl),
                contentDescription = produto!!.nome,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
            )

            Spacer(Modifier.height(16.dp))

            Text(produto!!.nome, style = MaterialTheme.typography.headlineSmall)
            Spacer(Modifier.height(8.dp))
            Text("R$ %.2f".format(produto!!.preco), style = MaterialTheme.typography.titleLarge)

            Spacer(Modifier.height(24.dp))

            Button(
                onClick = {
                    cartViewModel.addToCart(produto!!)
                    Toast.makeText(context, "Adicionado ao carrinho!", Toast.LENGTH_SHORT).show()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Adicionar ao carrinho")
            }
        }
    }
}
