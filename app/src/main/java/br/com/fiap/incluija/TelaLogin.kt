package br.com.fiap.incluija

import androidx.compose.foundation.Image
import br.com.fiap.incluija.data.RepositorioUsuarios
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TelaLogin(
    onNavigateToCadastro: () -> Unit = {},
    onNavigateToHome: () -> Unit = {}
) {
    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    var showErroLoginDialog by remember { mutableStateOf(false) }

    fun validarLogin() {
        // Validar se os campos estão vazios
        if (email.isBlank() || senha.isBlank()) {
            showErroLoginDialog = true
            return
        }

        // Validar credenciais com o repositório
        if (RepositorioUsuarios.validarLogin(email, senha)) {
            // Sucesso: navegar para Home
            onNavigateToHome()
        } else {
            // Falha: mostrar diálogo de erro
            showErroLoginDialog = true
        }
    }

    if (showErroLoginDialog) {
        AlertDialog(
            onDismissRequest = { /* nao permite fechar fora do OK */ },
            title = { Text(text = "Erro de Login") },
            text = { Text(text = "E-mail ou senha invalidos, tente novamente!") },
            confirmButton = {
                TextButton(
                    onClick = {
                        showErroLoginDialog = false
                        // Limpar os campos
                        email = ""
                        senha = ""
                    }
                ) {
                    Text(text = "OK", color = Color(0xFFD2691E))
                }
            },
            dismissButton = null
        )
    }
    val gradientColors = listOf(
        Color(0xFFFFBD59), // Laranja/Amarelo
        Color(0xFFE94057), // Rosa/Vermelho
        Color(0xFF8A2387)  // Roxo
    )
    val horizontalGradient = Brush.horizontalGradient(colors = gradientColors)

    val gradientColors = listOf(
        Color(0xFFFFBD59), // Laranja/Amarelo
        Color(0xFFE94057), // Rosa/Vermelho
        Color(0xFF8A2387)  // Roxo
    )
    val horizontalGradient = Brush.horizontalGradient(colors = gradientColors)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF2C1810),
                        Color(0xFF1a1a2e)
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Logo e título INCLUIJA (Lado a lado - conforme imagem)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo_incluija),
                    contentDescription = "Logo IncluiJá",
                    modifier = Modifier.size(115.dp)
                )

                Spacer(modifier = Modifier.width(0.dp))
                Column {
                    Text(
                        text = "INCLUIJÁ",
                        style = TextStyle(
                            brush = horizontalGradient,
                            fontSize = 34.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                    )
                    Text(
                        text = "SEU FUTURO COMEÇA AQUI",
                        fontSize = 11.sp,
                        color = Color.White.copy(alpha = 0.7f),
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(48.dp))

            // Card com formulário
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF2a2a3e).copy(alpha = 0.7f)
                )
            ) {
                Column(
                    modifier = Modifier
                        .padding(24.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Entrar na conta",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Text(
                        text = "E-MAIL",
                        fontSize = 12.sp,
                        color = Color.Gray,
                        fontWeight = FontWeight.Medium
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        placeholder = { Text("seu@email.com", color = Color.Gray) },
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedBorderColor = Color.Gray.copy(alpha = 0.5f),
                            focusedBorderColor = Color(0xFFFFBD59),
                            unfocusedContainerColor = Color(0xFF3a3a4e),
                            focusedContainerColor = Color(0xFF3a3a4e),
                            unfocusedTextColor = Color.White,
                            focusedTextColor = Color.White
                        ),
                        shape = RoundedCornerShape(12.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "SENHA",
                        fontSize = 12.sp,
                        color = Color.Gray,
                        fontWeight = FontWeight.Medium
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = senha,
                        onValueChange = { senha = it },
                        placeholder = { Text("••••••••", color = Color.Gray) },
                        visualTransformation = PasswordVisualTransformation(),
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedBorderColor = Color.Gray.copy(alpha = 0.5f),
                            focusedBorderColor = Color(0xFFFFBD59),
                            unfocusedContainerColor = Color(0xFF3a3a4e),
                            focusedContainerColor = Color(0xFF3a3a4e),
                            unfocusedTextColor = Color.White,
                            focusedTextColor = Color.White
                        ),
                        shape = RoundedCornerShape(12.dp)
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    // Botão Entrar com Gradiente e Seta
                    Button(
                        onClick = { validarLogin() },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                            .background(horizontalGradient, shape = RoundedCornerShape(16.dp)),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent
                        ),
                        contentPadding = PaddingValues()
                    ) {
                        Text(
                            text = "Entrar →",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ) {
                        Text(
                            text = "Não tem conta? ",
                            fontSize = 14.sp,
                            color = Color.Gray
                        )
                        Text(
                            text = "Criar gratuitamente",
                            fontSize = 14.sp,
                            color = Color(0xFFFFBD59),
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.clickable { onNavigateToCadastro() }
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun TelaLoginPreview() {
    TelaLogin()
}
