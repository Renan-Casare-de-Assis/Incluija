# 🎯 RESUMO VISUAL - Sistema de Autenticação Mockada

## 📊 Diagrama de Fluxo

```
┌─────────────────────────────────────────────────────────────────┐
│                         APP INICIADO                             │
└────────────────────────┬────────────────────────────────────────┘
                         │
                         ▼
            ┌────────────────────────┐
            │    TELA LOGIN          │
            │ teste@email.com / 123  │ (usuário padrão pré-cadastrado)
            │ Botão: "Entrar"        │
            │ Link: "Criar Grátis"   │
            └────────┬───────────────┘
                     │
        ┌────────────┴────────────┐
        │                         │
        ▼                         ▼
    "Entrar" clicado     "Criar Grátis" clicado
        │                         │
        ▼                         ▼
  validarLogin()         ┌────────────────────┐
        │                │   TELA CADASTRO    │
    ┌───┴───┐            │ Campos obrigatórios:
    │       │            │ - Nome (≥5 letras)
  OK? NÃO  │            │ - CPF (11 dígitos)
    │  │    │            │ - Email (único)
    │  │    │            │ - Senha + Repetir
    │  │    │            │ - Telefone (11)
    │  │    │            │ - Cidade (≥3 letras)
    │  │    │            │ - Vulnerabilidade
    │  │    │            │ Botão: "Continuar"
    │  │    └────────────┤ Botão: "Voltar"
    │  │                 └────────┬──────────┘
    │  │                          │
    │  │                   validarFormulario()
    │  │                          │
    │  │   ┌──────────┬───────────┴─────────┬──────────┐
    │  │   │          │                     │          │
    │  │   NO         OK                    NO         OK
    │  │   │          │                     │          │
    │  │   ▼          ▼                     ▼          ▼
    │  │ Mostra   registrarNoRepositorio()  ▲     RepositorioUsuarios
    │  │ Erros         │                    │      registrarUsuario()
    │  │               │                    │          │
    │  │    ┌──────────┴─────────┐          │     ┌────┴────┐
    │  │    │                    │          │     │         │
    │  │  SUCESSO            FALHA           │   OK?       NÃO
    │  │    │                    │          │     │         │
    │  │    ▼                    ▼          │     ▼         ▼
    │  │  Diálogo:           Diálogo:       │  Sucesso   Falha
    │  │  "Cadastro           "Email já     │    │         │
    │  │   Sucesso!"          cadastrado"   │    │      Mostra
    │  │      │                   │         │    │      Diálogo
    │  │      │                   │         │    │      "Email já
    │  │      OK                  OK        │    │      cadastrado"
    │  │      │                   │         │    │         │
    │  │      ▼                   ▼         │    │      Volta TCD
    │  │   ┌──────────┐       ┌────────┐   │    │
    │  │   │ TelaLogin│       │Volta   │   │    │
    │  │   │          │       │Cadastro│   │    │
    │  │   └──────────┘       └────────┘   │    │
    │  │                                    │    │
    │  └────────────────────────────────────┤    │
    │                                       │    │
    └───────────────────────────────────┬───┘    │
                                        │        │
                                        ▼        ▼
                                    "Entrar"
                                        │
                                    validarLogin()
                                        │
                            ┌───────────┴───────────┐
                            │                       │
                            ▼                       ▼
                        SUCESSO                  FALHA
                            │                       │
                            ▼                       ▼
                    ┌─────────────────┐    ┌──────────────────┐
                    │  TELA HOME      │    │ AlertDialog Erro │
                    │ ✅ Autenticado  │    │ "Email/senha     │
                    │                 │    │  inválidos"      │
                    └─────────────────┘    │                  │
                                           │ Botão: OK        │
                                           │ (limpa campos)   │
                                           └──────────────────┘
```

---

## 🗂️ Estrutura de Arquivos

```
incluija/
├── app/
│   └── src/
│       └── main/
│           └── java/
│               └── br/
│                   └── com/
│                       └── fiap/
│                           └── incluija/
│                               ├── data/                          ← NOVO
│                               │   ├── UsuarioMock.kt             ← NOVO
│                               │   └── RepositorioUsuarios.kt     ← NOVO
│                               │
│                               ├── TelaCadastro.kt                ← MODIFICADO
│                               ├── TelaLogin.kt                   ← MODIFICADO
│                               ├── TelaHome.kt
│                               ├── TelaCandidaturas.kt
│                               ├── TelaPerfil.kt
│                               └── MainActivity.kt
│
└── docs/
    ├── AUTENTICACAO_MOCKADA_RESUMO.md          ← NOVO
    ├── GUIA_AUTENTICACAO_MOCKADA.md            ← NOVO
    └── CHECKLIST_AUTENTICACAO.md               ← NOVO
```

---

## 🔄 Fluxo de Dados

### 1️⃣ Registrar Novo Usuário
```
TelaCadastro
    │
    ├─ Coleta dados do formulário
    │  └─ email, senha, nomeCompleto, cpf, ...
    │
    ├─ Valida localmente
    │  └─ Nome ≥ 5 letras ✓
    │  └─ CPF = 11 dígitos ✓
    │  └─ Email válido ✓
    │  └─ Telefone = 11 dígitos ✓
    │  └─ Cidade ≥ 3 letras ✓
    │  └─ Vulnerabilidade selecionada ✓
    │
    ├─ Cria UsuarioMock
    │  └─ new UsuarioMock(email, senha, ...)
    │
    └─> RepositorioUsuarios.registrarUsuario()
         │
         ├─ Verifica se email já existe
         │  ├─ SIM → return false → Mostra erro
         │  └─ NÃO → Adiciona à lista → return true
         │
         └─> usuarios.add(novoUsuario)
             └─ Dados armazenados em memória
```

### 2️⃣ Login de Usuário
```
TelaLogin
    │
    ├─ Coleta email e senha
    │
    ├─ Valida se não estão vazios
    │  ├─ VAZIO → Mostra erro
    │  └─ PREENCHIDO → Continua
    │
    └─> RepositorioUsuarios.validarLogin(email, senha)
         │
         ├─ Busca usuário com email (case-insensitive)
         │  ├─ Encontrado → Compara senha (case-sensitive)
         │  │  ├─ IGUAL → return true ✅
         │  │  └─ DIFERENTE → return false ❌
         │  └─ Não encontrado → return false ❌
         │
         └─> Se true → onNavigateToHome()
             └─> Se false → Mostra erro + limpa campos
```

---

## 💾 Dados em Memória

### Estrutura
```kotlin
object RepositorioUsuarios {
    private val usuarios: MutableList<UsuarioMock> = [
        UsuarioMock(
            email = "teste@email.com",
            senha = "123456",
            nomeCompleto = "Usuário Teste",
            cpf = "12345678901",
            nascimento = "01/01/2000",
            telefone = "11999999999",
            cidade = "São Paulo, SP",
            vulnerabilidades = setOf("PcD")
        ),
        // ... mais usuários cadastrados
    ]
}
```

### Ciclo de Vida
```
APP INICIADO
    │
    ▼
RepositorioUsuarios init {}
    │
    ├─ usuarios = mutableListOf()
    │
    └─ Adiciona usuário padrão
         └─ teste@email.com / 123456
    
    ▼
USUÁRIO USA APP
    │
    ├─ Cadastra novos usuários → Adicionados à lista
    ├─ Faz login → Valida na lista
    └─ Navega entre telas → Dados persistem
    
    ▼
APP FECHADO / REINICIADO
    │
    └─ Todos os dados perdidos ❌
       (Apenas usuário padrão volta)
```

---

## 🎯 Estados e Callbacks

### TelaCadastro
```kotlin
@Composable
fun TelaCadastro(
    onNavigateBack: () -> Unit = {}  // Voltar para Login
)

Estados:
- nomeCompleto: String
- cpf: String
- email: String
- senha: String
- vulnerabilidades: Set<String>
- showSuccessDialog: Boolean
- showEmailDuplicadoDialog: Boolean
- nomeError, cpfError, emailError... : String?

Métodos:
- validarFormulario(): Boolean
- registrarNoRepositorio(): Unit
```

### TelaLogin
```kotlin
@Composable
fun TelaLogin(
    onNavigateToCadastro: () -> Unit = {},  // Ir para Cadastro
    onNavigateToHome: () -> Unit = {}       // Ir para Home
)

Estados:
- email: String
- senha: String
- showErroLoginDialog: Boolean

Métodos:
- validarLogin(): Unit
```

---

## ✨ Destaques da Implementação

### ✅ Pontos Fortes
- Arquitetura limpa e separada (camadas)
- Singleton thread-safe para RepositorioUsuarios
- Data class imutável para UsuarioMock
- Validações robustas locais
- Diálogos informativos
- Usuário padrão pré-cadastrado
- Email case-insensitive (melhor UX)
- Código bem documentado com KDoc
- Preparado para migração para SQLite

### 🔧 Funcionalidades
- Registrar novo usuário ✓
- Validar email único ✓
- Login com validação ✓
- Limpar campos após erro ✓
- Mensagens de erro específicas ✓
- Navegação entre telas ✓
- Dados perdidos ao reiniciar (esperado) ✓

### 📱 Experiência do Usuário
- Flow intuitivo (Login → Cadastro → Home)
- Feedback visual em cada ação
- Campos obrigatórios marcados
- Erros específicos e claros
- Botões bem definidos
- Sem navegação confusa

---

## 🚀 Pronto para

✅ Testes no emulador
✅ Validação de fluxos
✅ Commit no Git/GitHub
✅ Próximo sprint (SQLite)

---

**Visualizado em:** 06/03/2026
**Status:** 🟢 Pronto para Teste

