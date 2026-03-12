package br.com.fiap.incluija

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Paleta de Cores Premium Dark
private val DarkBackground = Color(0xFF0F0F1A)
private val CardBackground = Color(0xFF1C1C2E)
private val GrayText = Color(0xFF9E9E9E)
private val HighlightYellow = Color(0xFFFFBD59)
private val DarkAccent = Color(0xFF252538)

data class Experience(
    val title: String,
    val company: String,
    val period: String,
    val icon: String
)

data class Skill(
    val name: String
)

data class CompatibilityItem(
    val title: String,
    val percentage: Int
)

@Composable
fun TelaPerfil() {
    var isEditing by remember { mutableStateOf(false) }

    val skills = listOf(
        Skill("Pacote Office"),
        Skill("Atendimento"),
        Skill("Organização"),
        Skill("Português"),
        Skill("Espanhol")
    )

    val experiences = listOf(
        Experience(
            title = "Vendedora",
            company = "Mercadão Bairro",
            period = "Jan 2022 – Dez 2023",
            icon = "🏪"
        ),
        Experience(
            title = "Recepcionista",
            company = "Clínica Saúde+",
            period = "Mar 2020 – Nov 2021",
            icon = "🏥"
        )
    )

    val compatibility = listOf(
        CompatibilityItem("Atendente de Loja", 92)
    )

    val scrollState = rememberScrollState()

    Scaffold(
        containerColor = DarkBackground
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(scrollState)
        ) {
            // Header Premium
            ProfileHeader(
                onEditClick = { isEditing = !isEditing }
            )

            // Card de Estatísticas
            StatsCard(
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 24.dp)
            )

            // Seção Habilidades
            SkillsSection(
                skills = skills,
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp)
            )

            // Seção Experiências
            ExperiencesSection(
                experiences = experiences,
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp)
            )

            // Seção Compatibilidade
            CompatibilitySection(
                items = compatibility,
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
fun ProfileHeader(
    onEditClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Gradiente laranja→rosa→roxo
    val gradientColors = listOf(
        Color(0xFFFFBD59), // Laranja/Amarelo
        Color(0xFFE94057), // Rosa/Vermelho
        Color(0xFF8A2387)  // Roxo
    )
    val horizontalGradient = Brush.horizontalGradient(colors = gradientColors)

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF2C1810),
                        Color(0xFF1a1a2e)
                    )
                ),
                shape = RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp)
            )
            .padding(horizontal = 24.dp, vertical = 32.dp)
    ) {
        // Row for Logo and Section Title (consistent with others)
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_incluija),
                contentDescription = "Logo IncluiJá",
                modifier = Modifier.size(60.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = "MEU PERFIL",
                    style = TextStyle(
                        brush = horizontalGradient,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                )
                Text(
                    text = "DADOS E COMPETÊNCIAS",
                    fontSize = 10.sp,
                    color = Color.White.copy(alpha = 0.7f),
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp
                )
            }
        }

        // Botão Editar
        Button(
            onClick = onEditClick,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .height(38.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = HighlightYellow
            ),
            contentPadding = PaddingValues(horizontal = 12.dp)
        ) {
            Icon(Icons.Default.Edit, null, tint = Color.Black, modifier = Modifier.size(14.dp))
            Spacer(modifier = Modifier.width(4.dp))
            Text("Editar", fontSize = 12.sp, color = Color.Black, fontWeight = FontWeight.Bold)
        }

        Column(
            modifier = Modifier.fillMaxWidth().padding(top = 80.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Avatar
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .background(CardBackground)
                    .border(2.dp, horizontalGradient, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "😊", fontSize = 56.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "MARIA SILVA",
                style = TextStyle(
                    brush = horizontalGradient,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.ExtraBold
                ),
                letterSpacing = 1.sp
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Administração · São Paulo, SP",
                fontSize = 14.sp,
                color = GrayText,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
fun StatsCard(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = CardBackground)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(24.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            StatColumn(number = "3", label = "Candidaturas", modifier = Modifier.weight(1f))
            Box(modifier = Modifier.width(1.dp).height(40.dp).background(Color.Gray.copy(alpha = 0.2f)))
            StatColumn(number = "85%", label = "Perfil", modifier = Modifier.weight(1f))
            Box(modifier = Modifier.width(1.dp).height(40.dp).background(Color.Gray.copy(alpha = 0.2f)))
            StatColumn(number = "1", label = "Entrevistas", modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun StatColumn(number: String, label: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = number, fontSize = 24.sp, fontWeight = FontWeight.Bold, color = HighlightYellow)
        Text(text = label, fontSize = 11.sp, color = GrayText)
    }
}

@Composable
fun SkillsSection(skills: List<Skill>, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = CardBackground)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Star, null, tint = HighlightYellow, modifier = Modifier.size(18.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "Habilidades", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.White)
                }
                Text(text = "+ Adicionar", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = HighlightYellow, modifier = Modifier.clickable { })
            }

            Spacer(modifier = Modifier.height(16.dp))

            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    skills.take(3).forEach { skill -> SkillChip(text = skill.name, modifier = Modifier.weight(1f)) }
                }
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    skills.drop(3).forEach { skill -> SkillChip(text = skill.name, modifier = Modifier.weight(1f)) }
                }
            }
        }
    }
}

@Composable
fun SkillChip(text: String, modifier: Modifier = Modifier) {
    Surface(shape = RoundedCornerShape(12.dp), color = DarkAccent, modifier = modifier) {
        Box(contentAlignment = Alignment.Center) {
            Text(text = text, fontSize = 12.sp, color = Color.White, modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp))
        }
    }
}

@Composable
fun ExperiencesSection(experiences: List<Experience>, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = CardBackground)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.AccountBox, null, tint = HighlightYellow, modifier = Modifier.size(18.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "Experiências", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.White)
                }
                Text(text = "+ Adicionar", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = HighlightYellow, modifier = Modifier.clickable { })
            }

            Spacer(modifier = Modifier.height(16.dp))

            experiences.forEachIndexed { index, experience ->
                ExperienceItem(experience = experience)
                if (index < experiences.size - 1) Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
fun ExperienceItem(experience: Experience) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp), verticalAlignment = Alignment.CenterVertically) {
        Box(modifier = Modifier.size(48.dp).clip(CircleShape).background(DarkAccent), contentAlignment = Alignment.Center) {
            Text(text = experience.icon, fontSize = 24.sp)
        }
        Column {
            Text(text = experience.title, fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color.White)
            Text(text = experience.company, fontSize = 13.sp, color = GrayText)
            Text(text = experience.period, fontSize = 12.sp, color = HighlightYellow, fontWeight = FontWeight.Medium)
        }
    }
}

@Composable
fun CompatibilitySection(items: List<CompatibilityItem>, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = CardBackground)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.CheckCircle, null, tint = HighlightYellow, modifier = Modifier.size(18.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Compatibilidade", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.White)
            }

            Spacer(modifier = Modifier.height(16.dp))

            items.forEach { item -> CompatibilityProgress(item = item) }
        }
    }
}

@Composable
fun CompatibilityProgress(item: CompatibilityItem) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = item.title, fontSize = 14.sp, fontWeight = FontWeight.Medium, color = Color.White)
            Text(text = "${item.percentage}%", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = HighlightYellow)
        }
        Spacer(modifier = Modifier.height(8.dp))
        LinearProgressIndicator(
            progress = { item.percentage / 100f },
            modifier = Modifier.fillMaxWidth().height(10.dp).clip(RoundedCornerShape(5.dp)),
            trackColor = DarkAccent,
            color = HighlightYellow
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TelaPerfilPreview() {
    TelaPerfil()
}
