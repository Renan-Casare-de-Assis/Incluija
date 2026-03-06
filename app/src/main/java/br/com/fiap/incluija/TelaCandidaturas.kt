package br.com.fiap.incluija

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.BorderStroke

// Cores da aplicação
private val OrangePrimary = Color(0xFFD35429)
private val OrangeSoft = Color(0xFFFFDCC8)
private val GreenDark = Color(0xFF2E7D32)
private val GreenSoft = Color(0xFFE8F5E9)
private val BeigeBackground = Color(0xFFF5EFE9)
private val GrayText = Color(0xFF757575)
private val GrayLight = Color(0xFFE8E8E8)
private val GrayDark = Color(0xFF424242)

data class ProcessStep(
    val title: String,
    val date: String,
    val status: StepStatus
)

enum class StepStatus {
    COMPLETED_GREEN, COMPLETED_ORANGE, PENDING
}

data class ApplicationItem(
    val title: String,
    val company: String,
    val status: String,
    val statusColor: Color,
    val statusBackgroundColor: Color,
    val borderColor: Color,
    val additionalText: String? = null,
    val steps: List<ProcessStep>? = null,
    val location: String? = null,
    val primaryButtonText: String? = null,
    val secondaryButtonText: String? = null
)

@Composable
fun TelaCandidaturas() {
    var selectedTab by remember { mutableStateOf(0) }

    val tabs = listOf("Em andamento", "Salvas", "Encerradas")

    val applications = listOf(
        ApplicationItem(
            title = "Atendente de Loja",
            company = "Mercadão Brasil",
            status = "Entrevista",
            statusColor = OrangePrimary,
            statusBackgroundColor = OrangeSoft,
            borderColor = OrangePrimary,
            location = "Av. Paulista, 1000",
            primaryButtonText = "Ver detalhes",
            steps = listOf(
                ProcessStep("Candidatura enviada", "12 fev", StepStatus.COMPLETED_GREEN),
                ProcessStep("Currículo aprovado", "15 fev", StepStatus.COMPLETED_GREEN),
                ProcessStep("Entrevista marcada", "28 fev · 14h", StepStatus.COMPLETED_ORANGE),
                ProcessStep("Resultado final", "", StepStatus.PENDING)
            )
        ),
        ApplicationItem(
            title = "Auxiliar Administrativo",
            company = "TechImpulsio",
            status = "Aguardando",
            statusColor = OrangePrimary,
            statusBackgroundColor = BeigeBackground,
            borderColor = OrangePrimary,
            additionalText = "Candidatura em 18 fev",
            primaryButtonText = "Acompanhar",
            secondaryButtonText = "Retirar"
        ),
        ApplicationItem(
            title = "Recepcionista",
            company = "Clínica Saúde+",
            status = "Aprovada",
            statusColor = GreenDark,
            statusBackgroundColor = GreenSoft,
            borderColor = GreenDark,
            additionalText = "Aprovada em 20 fev",
            primaryButtonText = "Ver proposta"
        )
    )

    Scaffold(
        containerColor = BeigeBackground
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Header
            CandidaturaHeaderSection()

            // Lista de Candidaturas
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(BeigeBackground)
                    .padding(horizontal = 24.dp, vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(applications.size) { index ->
                    CandidaturaCard(application = applications[index])
                }
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}

@Composable
fun CandidaturaHeaderSection() {
    // Gradiente laranja→rosa→roxo
    val gradientColors = listOf(
        Color(0xFFFFBD59), // Laranja/Amarelo
        Color(0xFFE94057), // Rosa/Vermelho
        Color(0xFF8A2387)  // Roxo
    )
    val horizontalGradient = Brush.horizontalGradient(colors = gradientColors)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                brush = horizontalGradient,
                shape = RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp)
            )
            .padding(horizontal = 24.dp, vertical = 20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Icon(
                imageVector = Icons.Default.DateRange,
                contentDescription = "Candidaturas",
                tint = Color.White,
                modifier = Modifier.size(28.dp)
            )
            Text(
                text = "Minhas Candidaturas",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}

@Composable
fun CandidaturaCard(application: ApplicationItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 4.dp,
                color = application.borderColor,
                shape = RoundedCornerShape(20.dp)
            ),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            // Header do card
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = application.title,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = GrayDark
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = application.company,
                        fontSize = 13.sp,
                        color = GrayText
                    )
                }

                CandidaturaStatusBadge(
                    text = application.status,
                    backgroundColor = application.statusBackgroundColor,
                    textColor = application.statusColor
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Timeline ou texto adicional
            if (application.steps != null) {
                CandidaturaProcessTimeline(steps = application.steps)
                Spacer(modifier = Modifier.height(16.dp))
            } else if (application.additionalText != null) {
                Text(
                    text = application.additionalText,
                    fontSize = 13.sp,
                    color = GrayText
                )
                Spacer(modifier = Modifier.height(16.dp))
            }

            // Localização
            if (application.location != null) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = "Localização",
                        tint = OrangePrimary,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = application.location,
                        fontSize = 13.sp,
                        color = OrangePrimary,
                        fontWeight = FontWeight.Medium
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
            }

            // Botões
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                if (application.secondaryButtonText != null) {
                    OutlinedButton(
                        onClick = {},
                        modifier = Modifier
                            .weight(1f)
                            .height(40.dp),
                        shape = RoundedCornerShape(8.dp),
                        border = BorderStroke(1.dp, GrayLight)
                    ) {
                        Text(
                            text = application.secondaryButtonText,
                            fontSize = 13.sp,
                            color = GrayDark
                        )
                    }
                }

                if (application.primaryButtonText != null) {
                    Button(
                        onClick = {},
                        modifier = Modifier
                            .weight(1f)
                            .height(40.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = GreenDark
                        )
                    ) {
                        Text(
                            text = application.primaryButtonText,
                            fontSize = 13.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CandidaturaStatusBadge(
    text: String,
    backgroundColor: Color,
    textColor: Color
) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = backgroundColor,
        modifier = Modifier.padding(start = 8.dp)
    ) {
        Text(
            text = text,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            color = textColor,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
        )
    }
}

@Composable
fun CandidaturaProcessTimeline(steps: List<ProcessStep>) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Etapas do processo",
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            color = GrayDark,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        steps.forEachIndexed { index, step ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = if (index < steps.size - 1) 12.dp else 0.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Coluna de indicadores
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.width(40.dp)
                ) {
                    // Indicador circular
                    Box(
                        modifier = Modifier
                            .size(16.dp)
                            .clip(CircleShape)
                            .background(
                                when (step.status) {
                                    StepStatus.COMPLETED_GREEN -> GreenDark
                                    StepStatus.COMPLETED_ORANGE -> OrangePrimary
                                    StepStatus.PENDING -> GrayLight
                                }
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        if (step.status != StepStatus.PENDING) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(10.dp)
                            )
                        }
                    }

                    // Linha conectora
                    if (index < steps.size - 1) {
                        Box(
                            modifier = Modifier
                                .width(2.dp)
                                .height(24.dp)
                                .background(GrayLight)
                        )
                    }
                }

                // Conteúdo do passo
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(top = 2.dp)
                ) {
                    Text(
                        text = step.title,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium,
                        color = GrayDark
                    )
                    if (step.date.isNotEmpty()) {
                        Text(
                            text = step.date,
                            fontSize = 12.sp,
                            color = GrayText
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TelaCandidaturasPreview() {
    TelaCandidaturas()
}


