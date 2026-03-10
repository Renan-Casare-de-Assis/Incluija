package br.com.fiap.incluija

import androidx.compose.foundation.background
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.BorderStroke

// Paleta de Cores Premium Dark
private val DarkBackground = Color(0xFF0F0F1A)
private val CardBackground = Color(0xFF1C1C2E)
private val GrayText = Color(0xFF9E9E9E)
private val HighlightYellow = Color(0xFFFFBD59)

data class ProcessStep(
    val title: String,
    val date: String,
    val status: StepStatus
)

enum class StepStatus {
    COMPLETED, PENDING
}

data class ApplicationItem(
    val title: String,
    val company: String,
    val status: String,
    val location: String? = null,
    val steps: List<ProcessStep>? = null,
    val primaryButtonText: String? = null,
    val secondaryButtonText: String? = null
)

@Composable
fun TelaCandidaturas() {
    val applications = listOf(
        ApplicationItem(
            title = "Atendente de Loja",
            company = "Mercadão Brasil",
            status = "Entrevista",
            location = "Av. Paulista, 1000",
            primaryButtonText = "Ver detalhes",
            steps = listOf(
                ProcessStep("Candidatura enviada", "12 fev", StepStatus.COMPLETED),
                ProcessStep("Currículo aprovado", "15 fev", StepStatus.COMPLETED),
                ProcessStep("Entrevista marcada", "28 fev · 14h", StepStatus.COMPLETED),
                ProcessStep("Resultado final", "", StepStatus.PENDING)
            )
        ),
        ApplicationItem(
            title = "Auxiliar Administrativo",
            company = "TechImpulso",
            status = "Aguardando",
            primaryButtonText = "Acompanhar",
            secondaryButtonText = "Retirar"
        ),
        ApplicationItem(
            title = "Recepcionista",
            company = "Clínica Saúde+",
            status = "Aprovada",
            primaryButtonText = "Ver proposta"
        )
    )

    Scaffold(
        containerColor = DarkBackground
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            CandidaturaHeaderSection()

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp, vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(applications.size) { index ->
                    CandidaturaCard(application = applications[index])
                }
                item { Spacer(modifier = Modifier.height(20.dp)) }
            }
        }
    }
}

@Composable
fun CandidaturaHeaderSection() {
    val gradientColors = listOf(Color(0xFFFFBD59), Color(0xFFE94057), Color(0xFF8A2387))
    val horizontalGradient = Brush.horizontalGradient(colors = gradientColors)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                brush = Brush.verticalGradient(colors = listOf(Color(0xFF2C1810), Color(0xFF1a1a2e))),
                shape = RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp)
            )
            .padding(horizontal = 24.dp, vertical = 32.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Default.DateRange,
                contentDescription = null,
                tint = HighlightYellow,
                modifier = Modifier.size(30.dp).padding(end = 12.dp)
            )
            Text(
                text = "Minhas Candidaturas",
                style = TextStyle(
                    brush = horizontalGradient,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.ExtraBold
                )
            )
        }
    }
}

@Composable
fun CandidaturaCard(application: ApplicationItem) {
    val gradientColors = listOf(Color(0xFFFFBD59), Color(0xFFE94057), Color(0xFF8A2387))
    val horizontalGradient = Brush.horizontalGradient(colors = gradientColors)

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = CardBackground)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = application.title, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.White)
                    Text(text = application.company, fontSize = 14.sp, color = GrayText)
                }
                CandidaturaStatusBadge(text = application.status)
            }

            Spacer(modifier = Modifier.height(20.dp))

            if (application.steps != null) {
                CandidaturaProcessTimeline(steps = application.steps)
                Spacer(modifier = Modifier.height(20.dp))
            }

            if (application.location != null) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.LocationOn, null, tint = HighlightYellow, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(text = application.location, fontSize = 13.sp, color = GrayText)
                }
                Spacer(modifier = Modifier.height(20.dp))
            }

            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                if (application.secondaryButtonText != null) {
                    OutlinedButton(
                        onClick = {},
                        modifier = Modifier.weight(1f).height(48.dp),
                        shape = RoundedCornerShape(14.dp),
                        border = BorderStroke(1.dp, Color.Gray.copy(alpha = 0.3f))
                    ) {
                        Text(text = application.secondaryButtonText, fontSize = 14.sp, color = Color.White)
                    }
                }

                if (application.primaryButtonText != null) {
                    Button(
                        onClick = {},
                        modifier = Modifier
                            .weight(1f)
                            .height(48.dp)
                            .background(horizontalGradient, shape = RoundedCornerShape(14.dp)),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                        contentPadding = PaddingValues()
                    ) {
                        Text(text = application.primaryButtonText, fontSize = 14.sp, color = Color.White, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}

@Composable
fun CandidaturaStatusBadge(text: String) {
    Surface(
        shape = RoundedCornerShape(10.dp),
        color = HighlightYellow,
        modifier = Modifier.padding(start = 8.dp)
    ) {
        Text(
            text = text,
            fontSize = 11.sp,
            fontWeight = FontWeight.ExtraBold,
            color = Color.Black,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
        )
    }
}

@Composable
fun CandidaturaProcessTimeline(steps: List<ProcessStep>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Etapas do processo",
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            color = HighlightYellow,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        steps.forEachIndexed { index, step ->
            Row(
                modifier = Modifier.fillMaxWidth().padding(bottom = if (index < steps.size - 1) 12.dp else 0.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.width(20.dp)) {
                    Box(
                        modifier = Modifier
                            .size(14.dp)
                            .clip(CircleShape)
                            .background(if (step.status == StepStatus.COMPLETED) HighlightYellow else Color.Gray.copy(alpha = 0.3f)),
                        contentAlignment = Alignment.Center
                    ) {
                        if (step.status == StepStatus.COMPLETED) {
                            Icon(Icons.Default.Check, null, tint = Color.Black, modifier = Modifier.size(10.dp))
                        }
                    }
                    if (index < steps.size - 1) {
                        Box(modifier = Modifier.width(2.dp).height(24.dp).background(Color.Gray.copy(alpha = 0.2f)))
                    }
                }

                Column(modifier = Modifier.weight(1f)) {
                    Text(text = step.title, fontSize = 13.sp, fontWeight = FontWeight.Medium, color = Color.White)
                    if (step.date.isNotEmpty()) {
                        Text(text = step.date, fontSize = 11.sp, color = GrayText)
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
