package br.com.fiap.incluija

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Cores da aplicacao
private val OrangePrimary = Color(0xFFD35429)
private val BeigeBackground = Color(0xFFF5EFE9)
private val GrayLight = Color(0xFFE0E0E0)
private val GrayText = Color(0xFF757575)
private val GrayDark = Color(0xFF424242)
private val ErrorRed = Color(0xFFB00020)



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

    val scrollState = rememberScrollState()

    fun validarFormulario(): Boolean {
        nomeError = validateNome(nomeCompleto)
        cpfError = validateCpf(cpf)
        nascimentoError = if (nascimento.isBlank()) "Nascimento e obrigatorio." else null
        emailError = validateEmail(email)
        senhaError = if (senha.isBlank()) "Senha e obrigatoria." else null
        repetirSenhaError = if (repetirSenha.isBlank()) "Repetir senha e obrigatorio." else null
        telefoneError = validateTelefone(telefone)
        cidadeError = validateCidade(cidade)
        perfilError = if (selectedChips.isEmpty()) {
            "Selecione pelo menos uma opcao de vulnerabilidade."
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

    if (showSuccessDialog) {
        AlertDialog(
            onDismissRequest = { /* nao permite fechar fora do OK */ },
            title = { Text(text = "Sucesso") },
            text = { Text(text = "Cadastramento efetuado com sucesso") },
            confirmButton = {
                TextButton(
                    onClick = {
                        showSuccessDialog = false
                        onNavigateBack()
                    }
                ) {
                    Text(text = "OK", color = OrangePrimary)
                }
            },
            dismissButton = null
        )
    }

    Scaffold(
        containerColor = BeigeBackground
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(OrangePrimary)
                    .padding(horizontal = 24.dp, vertical = 16.dp)
            ) {
                Text(
                    text = "Voltar",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.clickable { onNavigateBack() }
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
                    .padding(horizontal = 24.dp)
                    .padding(bottom = 24.dp)
            ) {
                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "DADOS PESSOAIS",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = OrangePrimary,
                    letterSpacing = 0.5.sp
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

                Spacer(modifier = Modifier.height(32.dp))

                Text(
                    text = "PERFIL DE VULNERABILIDADE",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = OrangePrimary,
                    letterSpacing = 0.5.sp
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "Selecione o que se aplica a voce:",
                    fontSize = 14.sp,
                    color = GrayDark
                )

                Spacer(modifier = Modifier.height(16.dp))

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        SelectableChip(
                            text = "Refugiado/Migrante",
                            isSelected = selectedChips.contains("Refugiado/Migrante"),
                            onSelected = {
                                selectedChips = toggleChip(selectedChips, "Refugiado/Migrante")
                                perfilError = null
                            }
                        )
                        SelectableChip(
                            text = "PcD",
                            isSelected = selectedChips.contains("PcD"),
                            onSelected = {
                                selectedChips = toggleChip(selectedChips, "PcD")
                                perfilError = null
                            }
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        SelectableChip(
                            text = "Egresso prisional",
                            isSelected = selectedChips.contains("Egresso prisional"),
                            onSelected = {
                                selectedChips = toggleChip(selectedChips, "Egresso prisional")
                                perfilError = null
                            }
                        )
                        SelectableChip(
                            text = "LGBTQIA+",
                            isSelected = selectedChips.contains("LGBTQIA+"),
                            onSelected = {
                                selectedChips = toggleChip(selectedChips, "LGBTQIA+")
                                perfilError = null
                            }
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        SelectableChip(
                            text = "Quilombola",
                            isSelected = selectedChips.contains("Quilombola"),
                            onSelected = {
                                selectedChips = toggleChip(selectedChips, "Quilombola")
                                perfilError = null
                            }
                        )
                        SelectableChip(
                            text = "Indigena",
                            isSelected = selectedChips.contains("Indigena"),
                            onSelected = {
                                selectedChips = toggleChip(selectedChips, "Indigena")
                                perfilError = null
                            }
                        )
                        SelectableChip(
                            text = "Outro",
                            isSelected = selectedChips.contains("Outro"),
                            onSelected = {
                                selectedChips = toggleChip(selectedChips, "Outro")
                                perfilError = null
                            }
                        )
                    }
                }

                if (perfilError != null) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = perfilError!!,
                        color = ErrorRed,
                        fontSize = 12.sp
                    )
                }

                Spacer(modifier = Modifier.height(72.dp))

                Button(
                    onClick = {
                        if (validarFormulario()) {
                            showSuccessDialog = true
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = OrangePrimary
                    ),
                    shape = RoundedCornerShape(28.dp)
                ) {
                    Text(
                        text = "Continuar",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }

                Spacer(modifier = Modifier.height(0.dp))
            }
        }
    }
}

private fun toggleChip(current: Set<String>, label: String): Set<String> {
    return if (current.contains(label)) current - label else current + label
}

private fun validateNome(nome: String): String? {
    if (nome.isBlank()) return "Nome completo e obrigatorio."
    val lettersCount = nome.count { it.isLetter() }
    if (lettersCount < 5) return "Nome deve ter no minimo 5 letras."
    return null
}

private fun validateCpf(cpf: String): String? {
    if (cpf.isBlank()) return "CPF e obrigatorio."
    val onlyDigits = cpf.filter { it.isDigit() }
    if (onlyDigits.length != 11) return "CPF deve ter exatamente 11 numeros."
    if (cpf.any { !it.isDigit() }) return "CPF deve conter apenas digitos."
    return null
}

private fun validateEmail(email: String): String? {
    if (email.isBlank()) return "E-mail e obrigatorio."
    val regex = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")
    if (!regex.matches(email.trim())) return "Informe um e-mail valido."
    return null
}

private fun validateTelefone(telefone: String): String? {
    if (telefone.isBlank()) return "Telefone/WhatsApp e obrigatorio."
    val digits = telefone.filter { it.isDigit() }
    if (digits.length != 11) return "Telefone deve ter DDD + numero (11 digitos)."
    return null
}

private fun validateCidade(cidade: String): String? {
    if (cidade.isBlank()) return "Cidade - Estado e obrigatorio."
    val lettersCount = cidade.count { it.isLetter() }
    if (lettersCount < 3) return "Cidade - Estado deve ter no minimo 3 letras."
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
    Column(modifier = modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = {
                Text(
                    text = placeholder,
                    color = GrayText,
                    fontSize = 14.sp
                )
            },
            modifier = Modifier.fillMaxWidth(),
            isError = errorMessage != null,
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = Color.White,
                focusedContainerColor = Color.White,
                unfocusedBorderColor = if (errorMessage != null) ErrorRed else GrayLight,
                focusedBorderColor = if (errorMessage != null) ErrorRed else OrangePrimary,
                unfocusedTextColor = GrayDark,
                focusedTextColor = GrayDark
            ),
            shape = RoundedCornerShape(16.dp),
            singleLine = true
        )

        if (errorMessage != null) {
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = errorMessage,
                color = ErrorRed,
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
    Column(modifier = modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = {
                Text(
                    text = placeholder,
                    color = GrayText,
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
                unfocusedContainerColor = Color.White,
                focusedContainerColor = Color.White,
                unfocusedBorderColor = if (errorMessage != null) ErrorRed else GrayLight,
                focusedBorderColor = if (errorMessage != null) ErrorRed else OrangePrimary,
                unfocusedTextColor = GrayDark,
                focusedTextColor = GrayDark
            ),
            shape = RoundedCornerShape(16.dp),
            singleLine = true
        )

        if (errorMessage != null) {
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = errorMessage,
                color = ErrorRed,
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
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .background(if (isSelected) OrangePrimary else Color.White)
            .border(
                width = 1.dp,
                color = if (isSelected) OrangePrimary else GrayLight,
                shape = RoundedCornerShape(20.dp)
            )
            .clickable { onSelected() }
            .padding(horizontal = 20.dp, vertical = 10.dp)
    ) {
        Text(
            text = text,
            color = if (isSelected) Color.White else GrayDark,
            fontSize = 14.sp
        )
    }
}

@Composable
@Preview(showBackground = true)
fun TelaCadastroPreview() {
    TelaCadastro()
}
