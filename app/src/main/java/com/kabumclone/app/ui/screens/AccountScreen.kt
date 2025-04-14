package com.kabumclone.app.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.google.firebase.auth.FirebaseUser
import com.kabumclone.app.viewmodel.AuthViewModel

@Composable
fun AccountScreen(
    onGoogleLoginClicked: () -> Unit,
    viewModel: AuthViewModel = viewModel()
) {
    val user by viewModel.user.collectAsState()

    if (user == null) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Button(onClick = onGoogleLoginClicked) {
                Text("Entrar com Google")
            }
        }
    } else {
        PerfilUsuario(user = user!!, onLogout = viewModel::logout)
    }
}


@Composable
fun PerfilUsuario(user: FirebaseUser, onLogout: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        user.photoUrl?.let {
            Image(
                painter = rememberAsyncImagePainter(it),
                contentDescription = "Foto de perfil",
                modifier = Modifier
                    .size(100.dp)
                    .align(Alignment.CenterHorizontally)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("Nome: ${user.displayName ?: "Visitante"}", style = MaterialTheme.typography.titleMedium)
        Text("Email: ${user.email ?: "Não disponível"}")
        Spacer(modifier = Modifier.height(16.dp))
        Text("Saldo na carteira: R$ 200,00")
        Spacer(modifier = Modifier.height(32.dp))
        Button(onClick = onLogout) {
            Text("Sair")
        }
    }
}
