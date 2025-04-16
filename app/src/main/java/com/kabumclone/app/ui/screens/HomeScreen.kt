package com.kabumclone.app.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.kabumclone.app.data.model.Product
import kotlinx.coroutines.delay
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.kabumclone.app.ui.components.ProdutoCardHorizontal
import com.kabumclone.app.ui.components.ProdutoCardVertical
import com.kabumclone.app.viewmodel.ProductViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(navController: NavController, viewModel: ProductViewModel = koinViewModel<ProductViewModel>()) {
    val produtos by viewModel.produtos.collectAsState()

    LaunchedEffect(true) {
        if (!viewModel.apiLoadedOnce) {
            viewModel.fetchAndCacheProducts()
            viewModel.apiLoadedOnce = true
        }
    }

    if (produtos.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 64.dp)
        ) {
            item {
                val destaques = produtos.take(3)
                if (destaques.isNotEmpty()) {
                    val pagerState = rememberPagerState { destaques.size }
                    HorizontalPager(
                        state = pagerState,
                        pageSize = PageSize.Fill,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp)
                    ) { page ->
                        val produto = destaques[page]
                        Image(
                            painter = rememberAsyncImagePainter(produto.image),
                            contentDescription = produto.name,
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

                    LaunchedEffect(destaques) {
                        while (destaques.isNotEmpty()) {
                            delay(3000)
                            val nextPage = (pagerState.currentPage + 1) % destaques.size
                            pagerState.animateScrollToPage(nextPage)
                        }
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
                    items(produtos.take(3)) { produto ->
                        ProdutoCardHorizontal(
                            produto = produto,
                            onClick = { navController.navigate("produto/${produto.productId}") }
                        )
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
                Text("Recomendados", style = MaterialTheme.typography.titleLarge, modifier = Modifier.padding(horizontal = 16.dp))
            }

            items(produtos.drop(3)) { produto ->
                ProdutoCardVertical(
                    produto = produto,
                    onClick = { navController.navigate("produto/${produto.productId}") },
                    onFavoriteClick = { viewModel.toggleFavorite(it) },
                    isFavorite = produto.isFavorite
                )
            }
        }
    }
}