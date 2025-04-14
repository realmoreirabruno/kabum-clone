package com.kabumclone.app.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.kabumclone.app.data.model.Produto
import kotlinx.coroutines.delay
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.kabumclone.app.ui.components.ProdutoCardHorizontal
import com.kabumclone.app.ui.components.ProdutoCardVertical

@Composable
fun HomeScreen(navController: NavController) {
    val destaques = remember {
        listOf(
            Produto("1", "Cadeira Gamer KBM! GAMING CG200, Preto", 699.90, "https://plus.unsplash.com/premium_photo-1678112179122-50605ebc5697?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"),
            Produto("2", "Monitor Gamer Curvo KBM! Gaming MG210 23.6' FHD, 180Hz", 599.90, "https://images.unsplash.com/photo-1585792180666-f7347c490ee2?q=80&w=2077&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"),
            Produto("3", "SSD KBM! Gaming, SATA III, 256GB", 179.90, "https://plus.unsplash.com/premium_photo-1721133260774-84f57d69cb82?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"),
        )
    }

    val recomendados = remember {
        List(10) {
            Produto("$it", "Produto $it", (100..500).random().toDouble(), "https://via.placeholder.com/150")
        }
    }

    val pagerState = rememberPagerState { destaques.size }

    LaunchedEffect(Unit) {
        while (true) {
            delay(3000)
            val nextPage = (pagerState.currentPage + 1) % destaques.size
            pagerState.animateScrollToPage(nextPage)
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 64.dp)
    ) {
        item {
            HorizontalPager(
                state = pagerState,
                pageSize = PageSize.Fill,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
            ) { page ->
                val produto = destaques[page]
                Image(
                    painter = rememberAsyncImagePainter(produto.imagemUrl),
                    contentDescription = produto.nome,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(destaques.size) { index ->
                    val isSelected = pagerState.currentPage == index
                    Box(
                        modifier = Modifier
                            .size(if (isSelected) 10.dp else 8.dp)
                            .padding(2.dp)
                            .background(
                                if (isSelected) Color.Black else Color.LightGray,
                                shape = CircleShape
                            )
                    )
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
            Text("Destaques", style = MaterialTheme.typography.titleLarge, modifier = Modifier.padding(horizontal = 16.dp))
        }

        item {
            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
            ) {
                items(destaques) { produto ->
                    ProdutoCardHorizontal(produto = produto, onClick = {
                        navController.navigate("produto/${produto.id}")
                    })
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
            Text("Recomendados", style = MaterialTheme.typography.titleLarge, modifier = Modifier.padding(horizontal = 16.dp))
        }

        items(recomendados) { produto ->
            ProdutoCardVertical(produto = produto, onClick = {
                navController.navigate("produto/${produto.id}")
            })
        }
    }
}

