package br.com.fiap.incluija

import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Cores da aplicação
private val OrangePrimary = Color(0xFFD35429)
private val BeigeBackground = Color(0xFFF5EFE9)
private val DarkHeader = Color(0xFF1a1a1a)
private val GrayLight = Color(0xFFE8E8E8)
private val GrayText = Color(0xFF757575)
private val GrayDark = Color(0xFF424242)
private val OrangeSoft = Color(0xFFE8956F)

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
        containerColor = BeigeBackground
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(scrollState)
        ) {
            // Header Escuro
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
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp)
            )

            // Seção Experiências
            ExperiencesSection(
                experiences = experiences,
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp)
            )

            // Seção Compatibilidade
            CompatibilitySection(
                items = compatibility,
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp)
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
    Box(
        modifier = modifier
            .fillMaxWidth()
                .background(
                    color = OrangePrimary,
                    shape = RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp)
                )
            .padding(horizontal = 24.dp, vertical = 32.dp)
    ) {
        // Botão Editar no canto superior direito
        Button(
            onClick = onEditClick,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .height(40.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF3a3a3a)
            ),
            contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = "Editar",
                tint = Color.White,
                modifier = Modifier
                    .size(16.dp)
                    .padding(end = 4.dp)
            )
            Text(
                text = "Editar",
                fontSize = 13.sp,
                color = Color.White,
                fontWeight = FontWeight.Medium
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Avatar
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .background(OrangeSoft),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "😊",
                    fontSize = 56.sp
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Nome
            Text(
                text = "MARIA SILVA",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                letterSpacing = 0.5.sp
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Subtítulo
            Text(
                text = "Administração · São Paulo, SP",
                fontSize = 14.sp,
                color = Color(0xFFBBBBBB),
                lineHeight = 16.sp
            )
        }
    }
}

@Composable
fun StatsCard(
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Candidaturas
            StatColumn(
                number = "3",
                label = "Candidaturas",
                modifier = Modifier.weight(1f)
            )

            // Divisor Vertical
            Box(
                modifier = Modifier
                    .width(1.dp)
                    .height(60.dp)
                    .background(GrayLight)
            )

            // Perfil Completo
            StatColumn(
                number = "85%",
                label = "Perfil completo",
                modifier = Modifier.weight(1f)
            )

            // Divisor Vertical
            Box(
                modifier = Modifier
                    .width(1.dp)
                    .height(60.dp)
                    .background(GrayLight)
            )

            // Entrevistas
            StatColumn(
                number = "1",
                label = "Entrevistas",
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun StatColumn(
    number: String,
    label: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = number,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = GrayDark
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = label,
            fontSize = 12.sp,
            color = GrayText,
            lineHeight = 14.sp
        )
    }
}

@Composable
fun SkillsSection(
    skills: List<Skill>,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            // Header com título e botão adicionar
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Habilidades",
                        tint = GrayDark,
                        modifier = Modifier
                            .size(20.dp)
                            .padding(end = 8.dp)
                    )
                    Text(
                        text = "Habilidades",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = GrayDark
                    )
                }

                Text(
                    text = "+ Adicionar",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium,
                    color = OrangePrimary
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Chips de habilidades
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    skills.take(2).forEach { skill ->
                        SkillChip(text = skill.name)
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    skills.drop(2).take(2).forEach { skill ->
                        SkillChip(text = skill.name)
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    skills.drop(4).forEach { skill ->
                        SkillChip(text = skill.name)
                    }
                }
            }
        }
    }
}

@Composable
fun SkillChip(text: String) {
    Surface(
        shape = RoundedCornerShape(20.dp),
        color = BeigeBackground,
        modifier = Modifier
    ) {
        Text(
            text = text,
            fontSize = 13.sp,
            color = GrayDark,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
    }
}

@Composable
fun ExperiencesSection(
    experiences: List<Experience>,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            // Header com título e botão adicionar
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.AccountBox,
                        contentDescription = "Experiências",
                        tint = GrayDark,
                        modifier = Modifier
                            .size(20.dp)
                            .padding(end = 8.dp)
                    )
                    Text(
                        text = "Experiências",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = GrayDark
                    )
                }

                Text(
                    text = "+ Adicionar",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium,
                    color = OrangePrimary
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Itens de experiência
            experiences.forEachIndexed { index, experience ->
                ExperienceItem(experience = experience)
                if (index < experiences.size - 1) {
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }
        }
    }
}

@Composable
fun ExperienceItem(experience: Experience) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.Top
    ) {
        // Ícone circular
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(GrayLight),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = experience.icon,
                fontSize = 24.sp
            )
        }

        // Informações
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = experience.title,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = GrayDark
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = experience.company,
                fontSize = 13.sp,
                color = GrayText
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = experience.period,
                fontSize = 12.sp,
                color = OrangePrimary,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
fun CompatibilitySection(
    items: List<CompatibilityItem>,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            // Header com título
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = "Compatibilidade",
                    tint = GrayDark,
                    modifier = Modifier
                        .size(20.dp)
                        .padding(end = 8.dp)
                )
                Text(
                    text = "Compatibilidade",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = GrayDark
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Itens de compatibilidade
            items.forEach { item ->
                CompatibilityProgress(item = item)
            }
        }
    }
}

@Composable
fun CompatibilityProgress(item: CompatibilityItem) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = item.title,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = GrayDark
            )
            Text(
                text = "${item.percentage}%",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = OrangePrimary
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Barra de progresso
        LinearProgressIndicator(
            progress = { item.percentage / 100f },
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .clip(RoundedCornerShape(4.dp)),
            trackColor = GrayLight,
            color = OrangePrimary
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TelaPerfilPreview() {
    TelaPerfil()
}
