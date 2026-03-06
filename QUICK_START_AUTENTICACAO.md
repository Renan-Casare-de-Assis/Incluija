# 🚀 QUICK START - Sistema de Autenticação Mockada

## ⚡ 5 Minutos para Entender

### O que foi feito?

Implementamos um **sistema de autenticação completo** usando dados em memória (mockados):

✅ Usuários são cadastrados em uma lista durante o app  
✅ Senha é validada contra a lista  
✅ Dados são perdidos quando você fecha o app  
✅ Usuário padrão (`teste@email.com` / `123456`) sempre volta após reiniciar  

---

## 🧪 Testar em 3 Passos

### Passo 1: Abrir o App
1. Execute o projeto no emulador
2. Você verá a **TelaLogin**

### Passo 2: Login com Usuário Padrão
```
Email:    teste@email.com
Senha:    123456
Clique:   Entrar
Resultado: ✅ Vai para TelaHome
```

### Passo 3: Criar Novo Usuário
```
1. Clique em "Criar gratuitamente"
2. Preencha: Nome, CPF, Email, Senha, Telefone, Cidade, Vulnerabilidade
3. Clique em "Continuar"
4. Vê: "Cadastramento efetuado com sucesso"
5. Volta para TelaLogin
6. Tenta login com email/senha do novo usuário
7. Resultado: ✅ Vai para TelaHome
```

---

## 📂 O que foi criado/modificado?

### Novos Arquivos (2)

#### 1. `UsuarioMock.kt`
Classe que representa um usuário:
```kotlin
data class UsuarioMock(
    val email: String,
    val senha: String,
    val nomeCompleto: String,
    // ... mais campos
)
```

#### 2. `RepositorioUsuarios.kt`
Sistema que armazena e valida usuários:
```kotlin
object RepositorioUsuarios {
    fun registrarUsuario(usuario): Boolean { ... }
    fun validarLogin(email, senha): Boolean { ... }
}
```

### Arquivos Modificados (2)

#### 1. `TelaCadastro.kt`
Agora **salva** dados quando clica "Continuar"

#### 2. `TelaLogin.kt`
Agora **valida** email/senha quando clica "Entrar"

---

## 🎯 Fluxos Principais

### Fluxo 1: Novo Usuário
```
Cadastro → Validação Local → Repositório → Sucesso/Erro → Login
```

### Fluxo 2: Autenticação
```
Login → Validação Local → Repositório → Home/Erro
```

---

## ⚠️ Coisas Importantes

### 1. Dados são Perdidos
```
APP FECHA → Dados deletados
APP ABRE → Usuário padrão volta
```

### 2. Email é Único
Não pode ter dois usuários com o mesmo email.

### 3. Validações
**Localmente (antes de enviar):**
- Nome ≥ 5 letras
- CPF = 11 dígitos
- Email válido
- Telefone = 11 dígitos
- Cidade ≥ 3 letras
- Vulnerabilidade selecionada

**No Repositório (ao registrar):**
- Email não pode existir

**No Login:**
- Email e senha devem corresponder

---

## 🔐 Usuário de Teste

```
Email:    teste@email.com
Senha:    123456
```

Este usuário sempre volta após reiniciar o app!

---

## 📊 Status Atual

| Item | Status |
|------|--------|
| Código implementado | ✅ |
| Compilação | ✅ |
| Testes manuais | ⏳ (você vai fazer) |
| Commit Git | ⏳ (após validar) |
| Commit GitHub | ⏳ (após validar) |

---

## 🚀 Próximos Passos

1. **Validar no emulador** ← VOCÊ AGORA
2. **Fazer commit** ← Depois de validar
3. **Integrar com SQLite** ← Próximo sprint

---

## ❓ Perguntas Frequentes

### P: Meus dados salvaram?
**R:** Sim! Enquanto o app está aberto. Se fechar e abrir de novo, são perdidos (exceto o usuário padrão).

### P: Posso testar com vários usuários?
**R:** Sim! Cadastre quantos quiser durante a sessão. Todos estarão disponíveis para login até fechar o app.

### P: E se digitar email errado?
**R:** Durante o cadastro, ele valida se é um email válido. Se já existir, mostra erro.

### P: E se digitar senha errada no login?
**R:** Mostra diálogo "E-mail ou senha inválidos" e limpa os campos. Tente novamente.

### P: Por que o usuário padrão sempre volta?
**R:** Porque é configurado no código para sempre ser restaurado após reiniciar. É para testes.

---

## 🎮 Exercícios para Testar

### Exercício 1: Happy Path
1. Abra o app
2. Login com `teste@email.com` / `123456`
3. Resultado esperado: ✅ Vai para Home

### Exercício 2: Erro de Login
1. Tente login com `teste@email.com` / `000000`
2. Resultado esperado: ❌ "E-mail ou senha inválidos"

### Exercício 3: Cadastro Completo
1. Clique "Criar gratuitamente"
2. Preencha tudo com dados fictícios válidos
3. Selecione uma vulnerabilidade
4. Clique "Continuar"
5. Resultado esperado: ✅ "Cadastramento efetuado com sucesso"

### Exercício 4: Email Duplicado
1. Tente cadastrar com `teste@email.com` (já existe)
2. Resultado esperado: ❌ "E-mail já cadastrado"

### Exercício 5: Validação de Campos
1. Tente cadastrar com Nome = "João" (só 4 letras)
2. Resultado esperado: ❌ Erro de validação

### Exercício 6: Persistência
1. Cadastre novo usuário
2. Faça login com ele
3. Feche o app (swipe up ou back repeat)
4. Abra o app novamente
5. Tente login com o novo usuário
6. Resultado esperado: ❌ "E-mail ou senha inválidos" (dados perdidos)

---

## 📝 Checklist de Teste

Depois de testar, verifique:

- [ ] Login com usuário padrão funciona
- [ ] Login com credenciais erradas mostra erro
- [ ] Cadastro novo usuário funciona
- [ ] Email duplicado mostra erro
- [ ] Login com novo usuário funciona
- [ ] Campos são validados localmente
- [ ] Dados são perdidos após reiniciar
- [ ] Navegação entre telas é fluida
- [ ] Botões OK nos diálogos fecham corretamente

---

## 💡 Código-Chave

### Como é feito no TelaCadastro?
```kotlin
if (validarFormulario()) {
    registrarNoRepositorio()  // ← Salva dados
}
```

### Como é feito no TelaLogin?
```kotlin
if (RepositorioUsuarios.validarLogin(email, senha)) {
    onNavigateToHome()  // ← Vai para Home
} else {
    showErroLoginDialog = true  // ← Mostra erro
}
```

---

## 🔗 Documentação Disponível

Se quiser mais detalhes, leia:

1. **GUIA_AUTENTICACAO_MOCKADA.md** - Documentação completa
2. **CHECKLIST_AUTENTICACAO.md** - O que foi implementado
3. **RESUMO_VISUAL_AUTENTICACAO.md** - Diagramas visuais

---

## 🎉 Pronto!

Tudo está implementado e pronto para testar. 

**Próximo passo:** Execute no emulador e teste os fluxos acima!

---

**Última atualização:** 06/03/2026  
**Status:** 🟢 Pronto para Teste

