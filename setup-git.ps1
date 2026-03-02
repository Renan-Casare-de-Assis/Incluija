# Script para Automatizar Setup Git + GitHub
# Salve como: setup-git.ps1
# Execute: .\setup-git.ps1

param(
    [string]$Username = "",
    [string]$Email = "",
    [string]$GithubUrl = ""
)

Write-Host "╔═══════════════════════════════════════════════════╗" -ForegroundColor Cyan
Write-Host "║  Setup Git + GitHub - Projeto INCLUIJÁ           ║" -ForegroundColor Cyan
Write-Host "╚═══════════════════════════════════════════════════╝" -ForegroundColor Cyan
Write-Host ""

# Validar Git instalado
Write-Host "✓ Verificando Git..." -ForegroundColor Yellow
$gitVersion = git --version
if ($?) {
    Write-Host "  $gitVersion" -ForegroundColor Green
} else {
    Write-Host "  ✗ Git não encontrado! Instale em https://git-scm.com/" -ForegroundColor Red
    exit 1
}

Write-Host ""

# Coletar informações do usuário
if ([string]::IsNullOrWhiteSpace($Username)) {
    $Username = Read-Host "Digite seu nome (para Git)"
}

if ([string]::IsNullOrWhiteSpace($Email)) {
    $Email = Read-Host "Digite seu email (para Git)"
}

Write-Host ""

# Inicializar Git
Write-Host "✓ Inicializando repositório Git..." -ForegroundColor Yellow
git init
if (-not $?) {
    Write-Host "✗ Erro ao inicializar Git!" -ForegroundColor Red
    exit 1
}
Write-Host "  Repositório Git inicializado!" -ForegroundColor Green

Write-Host ""

# Configurar usuário
Write-Host "✓ Configurando usuário Git..." -ForegroundColor Yellow
git config user.name "$Username"
git config user.email "$Email"
Write-Host "  Nome: $Username" -ForegroundColor Green
Write-Host "  Email: $Email" -ForegroundColor Green

Write-Host ""

# Verificar .gitignore
Write-Host "✓ Verificando .gitignore..." -ForegroundColor Yellow
if (Test-Path ".gitignore") {
    Write-Host "  .gitignore já existe" -ForegroundColor Green
} else {
    Write-Host "  ✗ .gitignore não encontrado" -ForegroundColor Red
}

Write-Host ""

# Adicionar arquivos
Write-Host "✓ Adicionando arquivos ao staging..." -ForegroundColor Yellow
git add .
$filesAdded = git status --porcelain | Measure-Object -Line
Write-Host "  $($filesAdded.Lines) itens adicionados" -ForegroundColor Green

Write-Host ""

# Fazer commit
Write-Host "✓ Criando primeiro commit..." -ForegroundColor Yellow
git commit -m "init: projeto inicial INCLUIJÁ com telas principais

- Tela de login com autenticação
- Tela de cadastro com validação
- Dashboard home com vagas em destaque
- Perfil do usuário com habilidades
- Gerenciamento de candidaturas com timeline
- Implementado com Jetpack Compose e Material3"

if ($?) {
    Write-Host "  Commit criado com sucesso!" -ForegroundColor Green
} else {
    Write-Host "  ✗ Erro ao criar commit!" -ForegroundColor Red
}

Write-Host ""

# Conexão ao GitHub (opcional)
if ([string]::IsNullOrWhiteSpace($GithubUrl)) {
    Write-Host "ℹ Próximo passo: Conectar ao GitHub" -ForegroundColor Cyan
    Write-Host ""
    Write-Host "1. Crie um novo repositório em: https://github.com/new" -ForegroundColor White
    Write-Host "   - Nome: Incluija" -ForegroundColor White
    Write-Host "   - NÃO inicialize com README, .gitignore ou license" -ForegroundColor White
    Write-Host ""
    Write-Host "2. Execute um destes comandos:" -ForegroundColor White
    Write-Host ""
    Write-Host "   HTTPS (mais fácil):" -ForegroundColor Yellow
    Write-Host "   git remote add origin https://github.com/SEU_USUARIO/Incluija.git" -ForegroundColor Gray
    Write-Host "   git branch -M main" -ForegroundColor Gray
    Write-Host "   git push -u origin main" -ForegroundColor Gray
    Write-Host ""
    Write-Host "   SSH (mais seguro):" -ForegroundColor Yellow
    Write-Host "   git remote add origin git@github.com:SEU_USUARIO/Incluija.git" -ForegroundColor Gray
    Write-Host "   git branch -M main" -ForegroundColor Gray
    Write-Host "   git push -u origin main" -ForegroundColor Gray
} else {
    Write-Host "✓ Conectando ao GitHub..." -ForegroundColor Yellow
    git remote add origin $GithubUrl
    git branch -M main
    Write-Host "  Remote adicionado!" -ForegroundColor Green

    Write-Host ""
    Write-Host "✓ Fazendo push para GitHub..." -ForegroundColor Yellow
    git push -u origin main

    if ($?) {
        Write-Host "  Push concluído com sucesso!" -ForegroundColor Green
    } else {
        Write-Host "  ✗ Erro ao fazer push!" -ForegroundColor Red
    }
}

Write-Host ""
Write-Host "╔═══════════════════════════════════════════════════╗" -ForegroundColor Green
Write-Host "║  ✓ Setup concluído com sucesso!                  ║" -ForegroundColor Green
Write-Host "╚═══════════════════════════════════════════════════╝" -ForegroundColor Green

Write-Host ""
Write-Host "Próximos passos:" -ForegroundColor Cyan
Write-Host "1. Verifique o status: git status" -ForegroundColor White
Write-Host "2. Veja o histórico: git log --oneline" -ForegroundColor White
Write-Host "3. Crie branches para features: git checkout -b feature/nome" -ForegroundColor White
Write-Host ""
Write-Host "Documentação:" -ForegroundColor Cyan
Write-Host "- Leia GIT_SETUP.md para guia completo" -ForegroundColor White
Write-Host "- Leia README.md para documentação do projeto" -ForegroundColor White

