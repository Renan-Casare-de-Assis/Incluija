package br.com.fiap.incluija.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import br.com.fiap.incluija.R

// ============================================================
//  IncluiJá – Typography v2
//
//  Display / Títulos → Bebas Neue  (impactante, identidade do logo)
//  Corpo / UI        → Sora        (legível, moderno, acessível)
//  Labels / Tags     → Plus Jakarta Sans (compacto, versátil)
//
//  Arquivos necessários em res/font/:
//    bebas_neue_regular.ttf
//    sora_light.ttf / sora_regular.ttf / sora_medium.ttf
//    sora_semibold.ttf / sora_bold.ttf / sora_extrabold.ttf
//    plus_jakarta_sans_regular.ttf / plus_jakarta_sans_medium.ttf
//    plus_jakarta_sans_semibold.ttf / plus_jakarta_sans_bold.ttf
// ============================================================

// --- Font Families ---

val BebasNeue = FontFamily(
    Font(R.font.bebas_neue_regular, FontWeight.Normal),
    // Bebas Neue só possui o peso Regular; use-o para todos os pesos de display
    Font(R.font.bebas_neue_regular, FontWeight.Bold),
    Font(R.font.bebas_neue_regular, FontWeight.ExtraBold),
)

val Sora = FontFamily(
    Font(R.font.sora_light,     FontWeight.Light),
    Font(R.font.sora_regular,   FontWeight.Normal),
    Font(R.font.sora_medium,    FontWeight.Medium),
    Font(R.font.sora_semibold,  FontWeight.SemiBold),
    Font(R.font.sora_bold,      FontWeight.Bold),
    Font(R.font.sora_extrabold, FontWeight.ExtraBold),
)

val PlusJakartaSans = FontFamily(
    Font(R.font.plus_jakarta_sans_regular,  FontWeight.Normal),
    Font(R.font.plus_jakarta_sans_medium,   FontWeight.Medium),
    Font(R.font.plus_jakarta_sans_semibold, FontWeight.SemiBold),
    Font(R.font.plus_jakarta_sans_bold,     FontWeight.Bold),
)

// ============================================================
//  Escala tipográfica Material3 → IncluiJá v2
//
//  Display  (3)  → Bebas Neue  | Grandes títulos de tela
//  Headline (3)  → Bebas Neue  | Títulos de seção / card
//  Title    (3)  → Sora        | Subtítulos, nomes, headers
//  Body     (2)  → Sora        | Texto corrido, descrições
//  Label    (3)  → PlusJakarta | Chips, tags, badges, botões
// ============================================================

val Typography = Typography(

    // ── Display ─────────────────────────────────────────────
    // Usado em: nome do app, splash, hero screens
    displayLarge = TextStyle(
        fontFamily   = BebasNeue,
        fontWeight   = FontWeight.Normal,   // Bebas Neue só tem Regular
        fontSize     = 57.sp,
        lineHeight   = 60.sp,
        letterSpacing = 0.06.sp,
    ),
    displayMedium = TextStyle(
        fontFamily   = BebasNeue,
        fontWeight   = FontWeight.Normal,
        fontSize     = 45.sp,
        lineHeight   = 48.sp,
        letterSpacing = 0.05.sp,
    ),
    displaySmall = TextStyle(
        fontFamily   = BebasNeue,
        fontWeight   = FontWeight.Normal,
        fontSize     = 36.sp,
        lineHeight   = 40.sp,
        letterSpacing = 0.05.sp,
    ),

    // ── Headline ─────────────────────────────────────────────
    // Usado em: títulos de tela (ex: "Criar seu perfil", "Minhas candidaturas")
    headlineLarge = TextStyle(
        fontFamily   = BebasNeue,
        fontWeight   = FontWeight.Normal,
        fontSize     = 32.sp,
        lineHeight   = 36.sp,
        letterSpacing = 0.04.sp,
    ),
    headlineMedium = TextStyle(
        fontFamily   = BebasNeue,
        fontWeight   = FontWeight.Normal,
        fontSize     = 28.sp,
        lineHeight   = 32.sp,
        letterSpacing = 0.04.sp,
    ),
    headlineSmall = TextStyle(
        fontFamily   = BebasNeue,
        fontWeight   = FontWeight.Normal,
        fontSize     = 24.sp,
        lineHeight   = 28.sp,
        letterSpacing = 0.04.sp,
    ),

    // ── Title ────────────────────────────────────────────────
    // Usado em: nome da vaga, nome do usuário, subtítulos de seção
    titleLarge = TextStyle(
        fontFamily   = Sora,
        fontWeight   = FontWeight.Bold,
        fontSize     = 22.sp,
        lineHeight   = 28.sp,
        letterSpacing = 0.sp,
    ),
    titleMedium = TextStyle(
        fontFamily   = Sora,
        fontWeight   = FontWeight.SemiBold,
        fontSize     = 17.sp,
        lineHeight   = 24.sp,
        letterSpacing = 0.sp,
    ),
    titleSmall = TextStyle(
        fontFamily   = Sora,
        fontWeight   = FontWeight.SemiBold,
        fontSize     = 15.sp,
        lineHeight   = 20.sp,
        letterSpacing = 0.sp,
    ),

    // ── Body ─────────────────────────────────────────────────
    // Usado em: descrições de vagas, textos de perfil, mensagens
    bodyLarge = TextStyle(
        fontFamily   = Sora,
        fontWeight   = FontWeight.Normal,
        fontSize     = 16.sp,
        lineHeight   = 24.sp,
        letterSpacing = 0.15.sp,
    ),
    bodyMedium = TextStyle(
        fontFamily   = Sora,
        fontWeight   = FontWeight.Normal,
        fontSize     = 14.sp,
        lineHeight   = 20.sp,
        letterSpacing = 0.15.sp,
    ),
    bodySmall = TextStyle(
        fontFamily   = Sora,
        fontWeight   = FontWeight.Light,
        fontSize     = 12.sp,
        lineHeight   = 16.sp,
        letterSpacing = 0.15.sp,
    ),

    // ── Label ────────────────────────────────────────────────
    // Usado em: chips de categoria, badges de status, botões, tabs
    labelLarge = TextStyle(
        fontFamily   = PlusJakartaSans,
        fontWeight   = FontWeight.SemiBold,
        fontSize     = 14.sp,
        lineHeight   = 20.sp,
        letterSpacing = 0.08.sp,
    ),
    labelMedium = TextStyle(
        fontFamily   = PlusJakartaSans,
        fontWeight   = FontWeight.Medium,
        fontSize     = 12.sp,
        lineHeight   = 16.sp,
        letterSpacing = 0.08.sp,
    ),
    labelSmall = TextStyle(
        fontFamily   = PlusJakartaSans,
        fontWeight   = FontWeight.Medium,
        fontSize     = 11.sp,
        lineHeight   = 16.sp,
        letterSpacing = 0.10.sp,
    ),
)