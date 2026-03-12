package br.com.fiap.incluija

import br.com.fiap.incluija.data.RepositorioUsuarios
import br.com.fiap.incluija.data.UsuarioMock
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TelaCadastro(
    onNavigateBack: () -> Unit = {}
) {
    var nomeCompleto by remember { mutableStateOf("") }
    var cpf by remember { mutableStateOf("") }
    var nascimento by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    var repetirSenha by remember { mutableStateOf("") }
    var telefone by remember { mutableStateOf("") }
    var cidade by remember { mutableStateOf("") }

    var senhaVisivel by remember { mutableStateOf(false) }
    var repetirSenhaVisivel by remember { mutableStateOf(false) }

    var selectedChips by remember { mutableStateOf(setOf<String>()) }

    // Estados de erro
    var nomeError by remember { mutableStateOf<String?>(null) }
    var cpfError by remember { mutableStateOf<String?>(null) }
    var nascimentoError by remember { mutableStateOf<String?>(null) }
    var emailError by remember { mutableStateOf<String?>(null) }
    var senhaError by remember { mutableStateOf<String?>(null) }
    var repetirSenhaError by remember { mutableStateOf<String?>(null) }
    var telefoneError by remember { mutableStateOf<String?>(null) }
    var cidadeError by remember { mutableStateOf<String?>(null) }
    var perfilError by remember { mutableStateOf<String?>(null) }

    var showSuccessDialog by remember { mutableStateOf(false) }
    var showEmailDuplicadoDialog by remember { mutableStateOf(false) }

    val scrollState = rememberScrollState()

    // Gradiente laranja→rosa→roxo
    val gradientColors = listOf(
        Color(0xFFFFBD59), // Laranja/Amarelo
        Color(0xFFE94057), // Rosa/Vermelho
        Color(0xFF8A2387)  // Roxo
    )
    val horizontalGradient = Brush.horizontalGradient(colors = gradientColors)
    val highlightColor = Color(0xFFFFBD59)

    fun validarFormulario(): Boolean {
        nomeError = validateNome(nomeCompleto)
        cpfError = validateCpf(cpf)
        nascimentoError = if (nascimento.isBlank()) "Nascimento é obrigatório." else null
        emailError = validateEmail(email)
        senhaError = if (senha.isBlank()) "Senha é obrigatória." else null
        repetirSenhaError = if (repetirSenha.isBlank()) "Repetir senha é obrigatório." else null
        telefoneError = validateTelefone(telefone)
        cidadeError = validateCidade(cidade)
        perfilError = if (selectedChips.isEmpty()) {
            "Selecione pelo menos uma opção de vulnerabilidade."
        } else {
            null
        }

        return listOf(
            nomeError,
            cpfError,
            nascimentoError,
            emailError,
            senhaError,
            repetirSenhaError,
            telefoneError,
            cidadeError,
            perfilError
        ).all { it == null }
    }

    fun registrarNoRepositorio() {
        // Criar objeto UsuarioMock com os dados do formulário
        val novoUsuario = UsuarioMock(
            email = email,
            senha = senha,
            nomeCompleto = nomeCompleto,
            cpf = cpf,
            nascimento = nascimento,
            telefone = telefone,
            cidade = cidade,
            vulnerabilidades = selectedChips
        )

        // Tentar registrar no repositório
        val registroSucesso = RepositorioUsuarios.registrarUsuario(novoUsuario)

        if (registroSucesso) {
            // Sucesso: mostrar diálogo de sucesso
            showSuccessDialog = true
        } else {
            // Falha: email já existe
            showEmailDuplicadoDialog = true
        }
    }

    if (showEmailDuplicadoDialog) {
        AlertDialog(
            onDismissRequest = { showEmailDuplicadoDialog = false },
            title = { Text(text = "Erro") },
            text = { Text(text = "E-mail ja cadastrado. Tente outro.") },
            confirmButton = {
                TextButton(
                    onClick = {
                        showEmailDuplicadoDialog = false
                    }
                ) {
                    Text(text = "OK", color = highlightColor)
                }
            },
            dismissButton = null
        )
    }

    if (showSuccessDialog) {
        AlertDialog(
            onDismissRequest = { showSuccessDialog = false },
            title = { Text(text = "Sucesso") },
            text = { Text(text = "Cadastramento efetuado com sucesso") },
            confirmButton = {
                TextButton(
                    onClick = {
                        showSuccessDialog = false
                        onNavigateBack()
                    }
                ) {
                    Text(text = "OK", color = highlightColor)
                }
            },
            dismissButton = null
        )
    }

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
                .verticalScroll(scrollState)
                .padding(horizontal = 24.dp)
                .padding(bottom = 20.dp)
        ) {
            // Apenas a setinha de voltar para economizar espaço
            Text(
                text = "←",
                color = Color.White,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(vertical = 12.dp)
                    .clickable { onNavigateBack() }
            )

            Text(
                text = "DADOS PESSOAIS",
                style = TextStyle(
                    brush = horizontalGradient,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.ExtraBold,
                    letterSpacing = 1.sp
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            CustomTextField(
                value = nomeCompleto,
                onValueChange = {
                    nomeCompleto = it
                    nomeError = null
                },
                placeholder = "Nome completo",
                errorMessage = nomeError
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                CustomTextField(
                    value = cpf,
                    onValueChange = {
                        cpf = it
                        cpfError = null
                    },
                    placeholder = "CPF",
                    errorMessage = cpfError,
                    modifier = Modifier.weight(1f)
                )

                CustomTextField(
                    value = nascimento,
                    onValueChange = {
                        nascimento = it
                        nascimentoError = null
                    },
                    placeholder = "Nascimento",
                    errorMessage = nascimentoError,
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            CustomTextField(
                value = email,
                onValueChange = {
                    email = it
                    emailError = null
                },
                placeholder = "E-mail",
                errorMessage = emailError
            )

            Spacer(modifier = Modifier.height(12.dp))

            CustomPasswordField(
                value = senha,
                onValueChange = {
                    senha = it
                    senhaError = null
                },
                placeholder = "Senha",
                isPasswordVisible = senhaVisivel,
                onVisibilityToggle = { senhaVisivel = !senhaVisivel },
                errorMessage = senhaError
            )

            Spacer(modifier = Modifier.height(12.dp))

            CustomPasswordField(
                value = repetirSenha,
                onValueChange = {
                    repetirSenha = it
                    repetirSenhaError = null
                },
                placeholder = "Repetir Senha",
                isPasswordVisible = repetirSenhaVisivel,
                onVisibilityToggle = { repetirSenhaVisivel = !repetirSenhaVisivel },
                errorMessage = repetirSenhaError
            )

            Spacer(modifier = Modifier.height(12.dp))

            CustomTextField(
                value = telefone,
                onValueChange = {
                    telefone = it
                    telefoneError = null
                },
                placeholder = "Telefone / WhatsApp",
                errorMessage = telefoneError
            )

            Spacer(modifier = Modifier.height(12.dp))

            CustomTextField(
                value = cidade,
                onValueChange = {
                    cidade = it
                    cidadeError = null
                },
                placeholder = "Cidade - Estado",
                errorMessage = cidadeError
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "PERFIL DE VULNERABILIDADE",
                style = TextStyle(
                    brush = horizontalGradient,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.ExtraBold,
                    letterSpacing = 1.sp
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Selecione o que se aplica a você:",
                fontSize = 14.sp,
                color = Color.White.copy(alpha = 0.7f),
                fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Estrutura de Chips usando apenas Column/Row estáveis
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    SelectableChip(
                        text = "Refugiado/Migrante",
                        isSelected = selectedChips.contains("Refugiado/Migrante"),
                        onSelected = { selectedChips = toggleChip(selectedChips, "Refugiado/Migrante"); perfilError = null },
                        gradient = horizontalGradient,
                        modifier = Modifier.weight(1f)
                    )
                    SelectableChip(
                        text = "PcD",
                        isSelected = selectedChips.contains("PcD"),
                        onSelected = { selectedChips = toggleChip(selectedChips, "PcD"); perfilError = null },
                        gradient = horizontalGradient,
                        modifier = Modifier.weight(0.5f)
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    SelectableChip(
                        text = "Indígena",
                        isSelected = selectedChips.contains("Indígena"),
                        onSelected = { selectedChips = toggleChip(selectedChips, "Indígena"); perfilError = null },
                        gradient = horizontalGradient,
                        modifier = Modifier.weight(1f)
                    )
                    SelectableChip(
                        text = "LGBTQIA+",
                        isSelected = selectedChips.contains("LGBTQIA+"),
                        onSelected = { selectedChips = toggleChip(selectedChips, "LGBTQIA+"); perfilError = null },
                        gradient = horizontalGradient,
                        modifier = Modifier.weight(1f)
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    SelectableChip(
                        text = "Egresso prisional",
                        isSelected = selectedChips.contains("Egresso prisional"),
                        onSelected = { selectedChips = toggleChip(selectedChips, "Egresso prisional"); perfilError = null },
                        gradient = horizontalGradient,
                        modifier = Modifier.weight(1f)
                    )
                    SelectableChip(
                        text = "Outro",
                        isSelected = selectedChips.contains("Outro"),
                        onSelected = { selectedChips = toggleChip(selectedChips, "Outro"); perfilError = null },
                        gradient = horizontalGradient,
                        modifier = Modifier.weight(0.5f)
                    )
                }
            }

            if (perfilError != null) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = perfilError!!,
                    color = Color(0xFFF44336),
                    fontSize = 13.sp
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Botão Continuar com Gradiente
            Button(
                onClick = {
                    if (validarFormulario()) {
                        registrarNoRepositorio()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .background(horizontalGradient, shape = RoundedCornerShape(28.dp)),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent
                ),
                contentPadding = PaddingValues()
            ) {
                Text(
                    text = "Continuar →",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

private fun toggleChip(current: Set<String>, label: String): Set<String> {
    return if (current.contains(label)) current - label else current + label
}

private fun validateNome(nome: String): String? {
    if (nome.isBlank()) return "Nome completo é obrigatório."
    val lettersCount = nome.count { it.isLetter() }
    if (lettersCount < 5) return "Nome deve ter no mínimo 5 letras."
    return null
}

private fun validateCpf(cpf: String): String? {
    if (cpf.isBlank()) return "CPF é obrigatório."
    val onlyDigits = cpf.filter { it.isDigit() }
    if (onlyDigits.length != 11) return "CPF deve ter exatamente 11 números."
    return null
}

private fun validateEmail(email: String): String? {
    if (email.isBlank()) return "E-mail é obrigatório."
    val regex = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")
    if (!regex.matches(email.trim())) return "Informe um e-mail válido."
    return null
}

private fun validateTelefone(telefone: String): String? {
    if (telefone.isBlank()) return "Telefone/WhatsApp é obrigatório."
    val digits = telefone.filter { it.isDigit() }
    if (digits.length != 11) return "Telefone deve ter DDD + número (11 dígitos)."
    return null
}

private fun validateCidade(cidade: String): String? {
    if (cidade.isBlank()) return "Cidade - Estado é obrigatório."
    val lettersCount = cidade.count { it.isLetter() }
    if (lettersCount < 3) return "Cidade - Estado deve ter no mínimo 3 letras."
    return null
}


@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    errorMessage: String? = null
) {
    val highlightColor = Color(0xFFFFBD59)
    Column(modifier = modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = {
                Text(
                    text = placeholder,
                    color = Color.Gray,
                    fontSize = 14.sp
                )
            },
            modifier = Modifier.fillMaxWidth(),
            isError = errorMessage != null,
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = Color(0xFF3a3a4e).copy(alpha = 0.5f),
                focusedContainerColor = Color(0xFF3a3a4e).copy(alpha = 0.5f),
                unfocusedBorderColor = Color.Gray.copy(alpha = 0.3f),
                focusedBorderColor = highlightColor,
                unfocusedTextColor = Color.White,
                focusedTextColor = Color.White,
                errorBorderColor = Color(0xFFF44336)
            ),
            shape = RoundedCornerShape(12.dp),
            singleLine = true
        )

        if (errorMessage != null) {
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = errorMessage,
                color = Color(0xFFF44336),
                fontSize = 12.sp
            )
        }
    }
}

@Composable
fun CustomPasswordField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    isPasswordVisible: Boolean,
    onVisibilityToggle: () -> Unit,
    modifier: Modifier = Modifier,
    errorMessage: String? = null
) {
    val highlightColor = Color(0xFFFFBD59)
    Column(modifier = modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = {
                Text(
                    text = placeholder,
                    color = Color.Gray,
                    fontSize = 14.sp
                )
            },
            modifier = Modifier.fillMaxWidth(),
            isError = errorMessage != null,
            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = onVisibilityToggle) {
                    Text(
                        text = if (isPasswordVisible) "👁️" else "👁️‍🗨️",
                        fontSize = 18.sp
                    )
                }
            },
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = Color(0xFF3a3a4e).copy(alpha = 0.5f),
                focusedContainerColor = Color(0xFF3a3a4e).copy(alpha = 0.5f),
                unfocusedBorderColor = Color.Gray.copy(alpha = 0.3f),
                focusedBorderColor = highlightColor,
                unfocusedTextColor = Color.White,
                focusedTextColor = Color.White,
                errorBorderColor = Color(0xFFF44336)
            ),
            shape = RoundedCornerShape(12.dp),
            singleLine = true
        )

        if (errorMessage != null) {
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = errorMessage,
                color = Color(0xFFF44336),
                fontSize = 12.sp
            )
        }
    }
}

@Composable
fun SelectableChip(
    text: String,
    isSelected: Boolean,
    onSelected: () -> Unit,
    modifier: Modifier = Modifier,
    gradient: Brush
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .then(
                if (isSelected) Modifier.background(gradient)
                else Modifier.background(Color(0xFF3a3a4e).copy(alpha = 0.5f))
            )
            .border(
                width = 1.dp,
                color = if (isSelected) Color.Transparent else Color.Gray.copy(alpha = 0.3f),
                shape = RoundedCornerShape(12.dp)
            )
            .clickable { onSelected() }
            .padding(horizontal = 12.dp, vertical = 8.dp)
    ) {
        Text(
            text = text,
            color = if (isSelected) Color.White else Color.White.copy(alpha = 0.7f),
            fontSize = 12.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
@Preview(showBackground = true)
fun TelaCadastroPreview() {
    TelaCadastro()
}
