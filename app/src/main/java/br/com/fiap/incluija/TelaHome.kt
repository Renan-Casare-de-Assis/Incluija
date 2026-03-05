package br.com.fiap.incluija

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Cores da aplicação
private val OrangePrimary = Color(0xFFD35429)
private val BeigeBackground = Color(0xFFF5EFE9)
private val GrayLight = Color(0xFFE8E8E8)
private val GrayText = Color(0xFF757575)
private val GrayDark = Color(0xFF424242)

// Gradiente colorido (igual à TelaLogin, TelaCadastro e TelaCandidaturas)
private val gradientColors = listOf(
    Color(0xFFFFBD59), // Laranja/Amarelo
    Color(0xFFE94057), // Rosa/Vermelho
    Color(0xFF8A2387)  // Roxo
)
private val horizontalGradient = Brush.horizontalGradient(colors = gradientColors)

// Data classes para o estado
data class Job(
    val title: String,
    val company: String,
    val salary: String,
    val tags: List<String>,
    val icon: String,
    val category: String
)

/**
 * Filtra a lista de jobs baseado na categoria selecionada
 */
fun filterJobs(jobs: List<Job>, selectedFilter: String): List<Job> {
    return if (selectedFilter == "Todas") {
        jobs
    } else {
        jobs.filter { it.category == selectedFilter }
    }
}

data class BottomNavItem(
    val label: String,
    val icon: ImageVector,
    val selected: Boolean
)

@Composable
fun TelaHome(
    onNavigation: (String) -> Unit = {},
    onNavigateToCandidaturas: () -> Unit = {}
) {
    var selectedFilter by remember { mutableStateOf("Todas") }
    var selectedNavItem by remember { mutableStateOf("Início") }

    val filters = listOf("Todas", "Remoto", "CLT", "Sem experiência")

    val jobs = listOf(
        Job(
            title = "Atendente de Loja",
            company = "Mercadão Brasil",
            salary = "R\$1.600",
            tags = listOf("CLT", "Presencial", "PcD"),
            icon = "🏪",
            category = "CLT"
        ),
        Job(
            title = "Auxiliar Administrativo",
            company = "TechImpulso",
            salary = "R\$1.900",
            tags = listOf("Remoto", "PJ"),
            icon = "💻",
            category = "Remoto"
        ),
        Job(
            title = "Auxiliar de Cozinha",
            company = "Restaurante Sabor",
            salary = "R\$1.412",
            tags = listOf("CLT", "Migrante"),
            icon = "👨‍🍳",
            category = "CLT"
        )
    )

    val navItems = listOf(
        BottomNavItem("Início", Icons.Default.Home, selectedNavItem == "Início"),
        BottomNavItem("Buscar", Icons.Default.Search, selectedNavItem == "Buscar"),
        BottomNavItem("Vagas", Icons.Default.Favorite, selectedNavItem == "Minhas vagas"),
        BottomNavItem("Cursos", Icons.Default.Create, selectedNavItem == "Cursos"),
        BottomNavItem("Perfil", Icons.Default.Person, selectedNavItem == "Perfil")
    )

    Scaffold(
        containerColor = BeigeBackground,
        bottomBar = {
            BottomNavigationBar(
                items = navItems,
                onItemClick = { selectedNavItem = it },
                onNavigation = onNavigation
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Header
            item {
                HeaderSection()
            }

            // Cards de Resumo
            item {
                SummaryCardsSection(onNavigateToCandidaturas = onNavigateToCandidaturas)
            }

            // Título "Vagas em destaque"
            item {
                Text(
                    text = "Vagas em destaque",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = GrayDark,
                    modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp)
                )
            }

            // Filtros
            item {
                FiltersSection(
                    filters = filters,
                    selectedFilter = selectedFilter,
                    onFilterSelected = { selectedFilter = it }
                )
            }

            // Lista de Vagas
            items(filterJobs(jobs, selectedFilter)) { job ->
                JobCard(job = job)
            }

            // Espaço final
            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
fun HeaderSection() {
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
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "",
                fontSize = 24.sp,
                modifier = Modifier.padding(end = 8.dp)
            )
            Text(
                text = "INCLUIJÁ",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                letterSpacing = 1.sp
            )
        }
    }
}

@Composable
fun SummaryCardsSection(onNavigateToCandidaturas: () -> Unit = {}) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        SummaryCard(
            icon = "📊",
            number = "1.240",
            label = "Vagas abertas hoje",
            isGradient = true,
            backgroundColor = OrangePrimary,
            textColor = Color.White,
            modifier = Modifier.weight(1f)
        )

        SummaryCard(
            icon = "🎯",
            number = " 3",
            label = "Minhas candidaturas",
            isGradient = false,
            backgroundColor = GrayLight,
            textColor = GrayDark,
            modifier = Modifier.weight(1f),
            onClick = onNavigateToCandidaturas
        )
    }
}

@Composable
fun SummaryCard(
    icon: String,
    number: String,
    label: String,
    backgroundColor: Color,
    textColor: Color,
    modifier: Modifier = Modifier,
    isGradient: Boolean = false,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = modifier
            .height(140.dp)
            .clickable(enabled = true) { onClick() },
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isGradient) Color.Transparent else backgroundColor
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .then(
                    if (isGradient) {
                        Modifier.background(
                            brush = horizontalGradient,
                            shape = RoundedCornerShape(20.dp)
                        )
                    } else {
                        Modifier
                    }
                )
                .padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = icon,
                fontSize = 32.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = number,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = textColor
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = label,
                fontSize = 13.sp,
                color = textColor.copy(alpha = 0.9f),
                lineHeight = 16.sp
            )
        }
    }
}

@Composable
fun FiltersSection(
    filters: List<String>,
    selectedFilter: String,
    onFilterSelected: (String) -> Unit
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(filters) { filter ->
            FilterChip(
                text = filter,
                isSelected = filter == selectedFilter,
                onClick = { onFilterSelected(filter) }
            )
        }
    }
}

@Composable
fun FilterChip(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Surface(
        onClick = onClick,
        shape = RoundedCornerShape(50),
        color = if (isSelected) Color.Black else GrayLight,
        modifier = Modifier
    ) {
        Text(
            text = text,
            color = if (isSelected) Color.White else GrayDark,
            fontSize = 14.sp,
            fontWeight = if (isSelected) FontWeight.Medium else FontWeight.Normal,
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
        )
    }
}

@Composable
fun JobCard(job: Job) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 8.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Ícone da vaga
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .clip(CircleShape)
                    .background(GrayLight),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = job.icon,
                    fontSize = 28.sp
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Informações da vaga
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = job.title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = GrayDark
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = job.company,
                    fontSize = 14.sp,
                    color = GrayText
                )
                Spacer(modifier = Modifier.height(8.dp))

                // Tags
                Row(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    job.tags.take(3).forEach { tag ->
                        JobTag(text = tag)
                    }
                }
            }

            Spacer(modifier = Modifier.width(12.dp))

            // Salário
            Text(
                text = job.salary,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = OrangePrimary
            )
        }
    }
}

@Composable
fun JobTag(text: String) {
    val backgroundColor = when (text) {
        "CLT" -> Color(0xFFE8F5E9)
        "Presencial" -> Color(0xFFFFF3E0)
        "PcD" -> Color(0xFFFFEBEE)
        "Remoto" -> Color(0xFFE0F7FA)
        "PJ" -> Color(0xFFF3E5F5)
        "Migrante" -> Color(0xFFFFF9C4)
        else -> GrayLight
    }

    val textColor = when (text) {
        "CLT" -> Color(0xFF2E7D32)
        "Presencial" -> Color(0xFFE65100)
        "PcD" -> Color(0xFFC62828)
        "Remoto" -> Color(0xFF00838F)
        "PJ" -> Color(0xFF6A1B9A)
        "Migrante" -> Color(0xFFF57F17)
        else -> GrayDark
    }

    Surface(
        shape = RoundedCornerShape(6.dp),
        color = backgroundColor
    ) {
        Text(
            text = text,
            fontSize = 11.sp,
            color = textColor,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
        )
    }
}

@Composable
fun BottomNavigationBar(
    items: List<BottomNavItem>,
    onItemClick: (String) -> Unit,
    onNavigation: (String) -> Unit = {}
) {
    Surface(
        shadowElevation = 8.dp,
        color = Color.White
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.forEach { item ->
                BottomNavItemView(
                    item = item,
                    onClick = { onItemClick(item.label) },
                    onNavigateToPerfil = {
                        if (item.label == "Perfil") {
                            onNavigation("perfil")
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun BottomNavItemView(
    item: BottomNavItem,
    onClick: () -> Unit,
    onNavigateToPerfil: () -> Unit = {}
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .width(60.dp)
    ) {
        IconButton(
            onClick = {
                onClick()
                if (item.label == "Perfil") {
                    onNavigateToPerfil()
                }
            },
            modifier = Modifier.size(32.dp)
        ) {
            Icon(
                imageVector = item.icon,
                contentDescription = item.label,
                tint = if (item.selected) OrangePrimary else GrayText,
                modifier = Modifier.size(24.dp)
            )
        }
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = item.label,
            fontSize = 11.sp,
            color = if (item.selected) OrangePrimary else GrayText,
            fontWeight = if (item.selected) FontWeight.Medium else FontWeight.Normal
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TelaHomePreview() {
    TelaHome()
}
