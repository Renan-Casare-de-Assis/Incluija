package br.com.fiap.incluija

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Cores da aplicação
private val OrangePrimary = Color(0xFFD35429)
private val BeigeBackground = Color(0xFFF5EFE9)
private val GrayLight = Color(0xFFE0E0E0)
private val GrayText = Color(0xFF757575)
private val GrayDark = Color(0xFF424242)

@Composable
fun TelaCadastro() {
    var nomeCompleto by remember { mutableStateOf("") }
    var cpf by remember { mutableStateOf("") }
    var nascimento by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var telefone by remember { mutableStateOf("") }
    var cidade by remember { mutableStateOf("") }

    var selectedChips by remember { mutableStateOf(setOf<String>()) }

    val scrollState = rememberScrollState()

    Scaffold(
        containerColor = BeigeBackground
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Barra superior laranja
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
                    fontWeight = FontWeight.Normal
                )
            }

            // Indicadores de progresso
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(BeigeBackground)
                    .padding(horizontal = 24.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                ProgressIndicatorBar(isActive = true, modifier = Modifier.weight(1f))
                ProgressIndicatorBar(isActive = true, modifier = Modifier.weight(1f))
                ProgressIndicatorBar(isActive = false, modifier = Modifier.weight(1f))
            }

            // Conteúdo com scroll
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
                    .padding(horizontal = 24.dp)
                    .padding(bottom = 24.dp)
            ) {
                Spacer(modifier = Modifier.height(24.dp))

                // Seção DADOS PESSOAIS
                Text(
                    text = "DADOS PESSOAIS",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = OrangePrimary,
                    letterSpacing = 0.5.sp
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Nome completo
                CustomTextField(
                    value = nomeCompleto,
                    onValueChange = { nomeCompleto = it },
                    placeholder = "Nome completo"
                )

                Spacer(modifier = Modifier.height(12.dp))

                // CPF e Nascimento na mesma linha
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    CustomTextField(
                        value = cpf,
                        onValueChange = { cpf = it },
                        placeholder = "CPF",
                        modifier = Modifier.weight(1f)
                    )

                    CustomTextField(
                        value = nascimento,
                        onValueChange = { nascimento = it },
                        placeholder = "Nascimento",
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                // E-mail
                CustomTextField(
                    value = email,
                    onValueChange = { email = it },
                    placeholder = "E-mail"
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Telefone / WhatsApp
                CustomTextField(
                    value = telefone,
                    onValueChange = { telefone = it },
                    placeholder = "Telefone / WhatsApp"
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Cidade - Estado
                CustomTextField(
                    value = cidade,
                    onValueChange = { cidade = it },
                    placeholder = "Cidade - Estado"
                )

                Spacer(modifier = Modifier.height(32.dp))

                // Seção PERFIL DE VULNERABILIDADE
                Text(
                    text = "PERFIL DE VULNERABILIDADE",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = OrangePrimary,
                    letterSpacing = 0.5.sp
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "Selecione o que se aplica a você (opcional):",
                    fontSize = 14.sp,
                    color = GrayDark
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Chips selecionáveis
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // Primeira linha
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        SelectableChip(
                            text = "Refugiado/Migrante",
                            isSelected = selectedChips.contains("Refugiado/Migrante"),
                            onSelected = {
                                selectedChips = if (selectedChips.contains("Refugiado/Migrante")) {
                                    selectedChips - "Refugiado/Migrante"
                                } else {
                                    selectedChips + "Refugiado/Migrante"
                                }
                            }
                        )
                        SelectableChip(
                            text = "PcD",
                            isSelected = selectedChips.contains("PcD"),
                            onSelected = {
                                selectedChips = if (selectedChips.contains("PcD")) {
                                    selectedChips - "PcD"
                                } else {
                                    selectedChips + "PcD"
                                }
                            }
                        )
                    }

                    // Segunda linha
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        SelectableChip(
                            text = "Egresso prisional",
                            isSelected = selectedChips.contains("Egresso prisional"),
                            onSelected = {
                                selectedChips = if (selectedChips.contains("Egresso prisional")) {
                                    selectedChips - "Egresso prisional"
                                } else {
                                    selectedChips + "Egresso prisional"
                                }
                            }
                        )
                        SelectableChip(
                            text = "LGBTQIA+",
                            isSelected = selectedChips.contains("LGBTQIA+"),
                            onSelected = {
                                selectedChips = if (selectedChips.contains("LGBTQIA+")) {
                                    selectedChips - "LGBTQIA+"
                                } else {
                                    selectedChips + "LGBTQIA+"
                                }
                            }
                        )
                    }

                    // Terceira linha
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        SelectableChip(
                            text = "Quilombola",
                            isSelected = selectedChips.contains("Quilombola"),
                            onSelected = {
                                selectedChips = if (selectedChips.contains("Quilombola")) {
                                    selectedChips - "Quilombola"
                                } else {
                                    selectedChips + "Quilombola"
                                }
                            }
                        )
                        SelectableChip(
                            text = "Indígena",
                            isSelected = selectedChips.contains("Indígena"),
                            onSelected = {
                                selectedChips = if (selectedChips.contains("Indígena")) {
                                    selectedChips - "Indígena"
                                } else {
                                    selectedChips + "Indígena"
                                }
                            }
                        )
                        SelectableChip(
                            text = "Outro",
                            isSelected = selectedChips.contains("Outro"),
                            onSelected = {
                                selectedChips = if (selectedChips.contains("Outro")) {
                                    selectedChips - "Outro"
                                } else {
                                    selectedChips + "Outro"
                                }
                            }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(72.dp))

                // Botão Continuar
                Button(
                    onClick = { /* TODO: Ação de continuar */ },
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

@Composable
fun ProgressIndicatorBar(
    isActive: Boolean,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .height(4.dp)
            .clip(RoundedCornerShape(2.dp))
            .background(if (isActive) OrangePrimary else GrayLight)
    )
}

@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier
) {
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
        modifier = modifier
            .fillMaxWidth(),
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedContainerColor = Color.White,
            focusedContainerColor = Color.White,
            unfocusedBorderColor = GrayLight,
            focusedBorderColor = OrangePrimary,
            unfocusedTextColor = GrayDark,
            focusedTextColor = GrayDark
        ),
        shape = RoundedCornerShape(16.dp),
        singleLine = true
    )
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
