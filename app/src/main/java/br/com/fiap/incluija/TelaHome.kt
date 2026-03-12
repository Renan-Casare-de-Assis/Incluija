package br.com.fiap.incluija

import androidx.compose.foundation.Image
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

// Gradiente colorido (igual à TelaLogin)
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

@Composable
fun TelaHome(
    onNavigation: (String) -> Unit = {},
    onNavigateToCandidaturas: () -> Unit = {}
) {
    var selectedFilter by remember { mutableStateOf("Todas") }
    var selectedNavItem by remember { mutableStateOf("Início") }

    val filters = listOf("Todas", "Remoto", "CLT", "Sem experiência")

    val jobs = listOf(
        Job("Atendente de Loja", "Mercadão Brasil", "R$1.600", listOf("CLT", "Presencial", "PcD"), "🏪", "CLT"),
        Job("Auxiliar Administrativo", "TechImpulso", "R$1.900", listOf("Remoto", "PJ"), "💻", "Remoto"),
        Job("Auxiliar de Cozinha", "Restaurante Sabor", "R$1.412", listOf("CLT", "Migrante"), "👨‍🍳", "CLT")
    )

    Scaffold(
        containerColor = DarkBackground,
        bottomBar = {
            BottomNavigationBar(
                selectedItem = selectedNavItem,
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
            item { HeaderSection() }

            // Cards de Resumo
            item {
                SummaryCardsSection(onNavigateToCandidaturas = onNavigateToCandidaturas)
            }

            item {
                Text(
                    text = "Vagas em destaque",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp)
                )
            }

            item {
                FiltersSection(
                    filters = filters,
                    selectedFilter = selectedFilter,
                    onFilterSelected = { selectedFilter = it }
                )
            }

            items(if (selectedFilter == "Todas") jobs else jobs.filter { it.category == selectedFilter }) { job ->
                JobCard(job = job)
            }

            item { Spacer(modifier = Modifier.height(20.dp)) }
        }
    }
}

@Composable
fun HeaderSection() {
    Box(
        modifier = Modifier
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
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
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
                    text = "INCLUIJÁ",
                    style = TextStyle(
                        brush = horizontalGradient,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                )
                Text(
                    text = "SEU FUTURO COMEÇA AQUI",
                    fontSize = 10.sp,
                    color = Color.White.copy(alpha = 0.7f),
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp
                )
            }
        }
    }
}

@Composable
fun SummaryCardsSection(onNavigateToCandidaturas: () -> Unit = {}) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp, vertical = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        SummaryCard(
            icon = "📊",
            number = "1.240",
            label = "Vagas abertas hoje",
            isGradient = false,
            backgroundColor = CardBackground,
            textColor = HighlightYellow,
            modifier = Modifier.weight(1f)
        )

        SummaryCard(
            icon = "🎯",
            number = " 3",
            label = "Minhas candidaturas",
            isGradient = false,
            backgroundColor = CardBackground,
            textColor = HighlightYellow,
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
        Box(
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
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = icon, fontSize = 32.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = number,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (isGradient) Color.White else textColor
                )
                Text(
                    text = label,
                    fontSize = 12.sp,
                    color = (if (isGradient) Color.White else textColor).copy(alpha = 0.8f)
                )
            }
        }
    }
}

@Composable
fun FiltersSection(filters: List<String>, selectedFilter: String, onFilterSelected: (String) -> Unit) {
    LazyRow(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(filters) { filter ->
            val isSelected = filter == selectedFilter
            Surface(
                onClick = { onFilterSelected(filter) },
                shape = RoundedCornerShape(16.dp),
                color = if (isSelected) HighlightYellow else CardBackground,
                modifier = Modifier
            ) {
                Text(
                    text = filter,
                    color = if (isSelected) Color.Black else Color.White,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp)
                )
            }
        }
    }
}

@Composable
fun JobCard(job: Job) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp, vertical = 8.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = CardBackground)
    ) {
        Row(modifier = Modifier.fillMaxWidth().padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Box(modifier = Modifier.size(50.dp).clip(CircleShape).background(Color(0xFF252538)), contentAlignment = Alignment.Center) {
                Text(text = job.icon, fontSize = 24.sp)
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(text = job.title, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.White)
                Text(text = job.company, fontSize = 13.sp, color = GrayText)
                Spacer(modifier = Modifier.height(10.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    job.tags.take(2).forEach { tag ->
                        Surface(
                            shape = RoundedCornerShape(8.dp), 
                            color = HighlightYellow 
                        ) {
                            Text(
                                text = tag, 
                                fontSize = 10.sp, 
                                fontWeight = FontWeight.ExtraBold, 
                                color = Color.Black, 
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                            )
                        }
                    }
                }
            }
            Text(text = job.salary, fontSize = 15.sp, fontWeight = FontWeight.ExtraBold, color = Color.White)
        }
    }
}

@Composable
fun BottomNavigationBar(selectedItem: String, onItemClick: (String) -> Unit, onNavigation: (String) -> Unit) {
    Surface(color = CardBackground, shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)) {
        Row(modifier = Modifier.fillMaxWidth().height(70.dp), horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.CenterVertically) {
            val navItems = listOf(
                Pair("Início", Icons.Default.Home),
                Pair("Buscar", Icons.Default.Search),
                Pair("Vagas", Icons.Default.Favorite),
                Pair("Perfil", Icons.Default.Person)
            )
            navItems.forEach { (label, icon) ->
                val isSelected = selectedItem == label
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.clickable { 
                        onItemClick(label)
                        if (label == "Perfil") onNavigation("perfil")
                    }
                ) {
                    Icon(imageVector = icon, contentDescription = label, tint = if (isSelected) HighlightYellow else GrayText)
                    Text(text = label, fontSize = 11.sp, color = if (isSelected) HighlightYellow else GrayText)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TelaHomePreview() {
    TelaHome()
}
