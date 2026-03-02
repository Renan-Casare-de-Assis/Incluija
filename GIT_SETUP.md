# 🚀 Guia Completo: Git + GitHub para Projeto INCLUIJÁ

## 📋 Pré-requisitos

1. **Git** instalado: https://git-scm.com/
2. **GitHub** conta criada: https://github.com/signup
3. **SSH ou HTTPS** configurado no GitHub

---

## 1️⃣ INICIALIZAR GIT LOCALMENTE

### Abra o PowerShell no diretório do projeto e execute:

```powershell
# Navegar até o diretório do projeto
cd C:\Users\renan\AndroidStudioProjects\Incluija

# Inicializar repositório Git
git init

# Configurar nome e email (se não estiver configurado globalmente)
git config user.name "Seu Nome"
git config user.email "seu.email@example.com"

# Verificar status dos arquivos
git status
```

---

## 2️⃣ ADICIONAR ARQUIVOS AO STAGING

```powershell
# Adicionar todos os arquivos (exceto os do .gitignore)
git add .

# OU adicionar arquivos específicos
git add app/src/main/java/br/com/fiap/incluija/
git add .gitignore
git add .gitattributes
git add README.md

# Verificar quais arquivos foram adicionados
git status
```

---

## 3️⃣ FAZER O PRIMEIRO COMMIT

```powershell
# Criar commit com mensagem descritiva
git commit -m "init: projeto inicial INCLUIJÁ com telas principais

- Tela de login com autenticação
- Tela de cadastro com validação
- Dashboard home com vagas em destaque
- Perfil do usuário com habilidades
- Gerenciamento de candidaturas com timeline
- Implementado com Jetpack Compose e Material3"

# Verificar log do commit
git log
```

---

## 4️⃣ CRIAR REPOSITÓRIO NO GITHUB

### Passo a passo:

1. Acesse: https://github.com/new
2. **Repository name**: `Incluija`
3. **Description**: "Aplicativo Android para inclusão no mercado de trabalho"
4. **Visibility**: Public (ou Private se preferir)
5. **NÃO** inicialize com README, .gitignore ou LICENSE (já temos localmente)
6. Clique em **"Create repository"**

---

## 5️⃣ CONECTAR REPOSITÓRIO LOCAL AO GITHUB

### Opção A: Usando HTTPS (mais fácil)

```powershell
# Adicionar origem remota
git remote add origin https://github.com/SEU_USUARIO/Incluija.git

# Renomear branch padrão para main (se necessário)
git branch -M main

# Fazer push da branch main
git push -u origin main
```

### Opção B: Usando SSH (mais seguro)

```powershell
# Gerar chave SSH (se não tiver)
ssh-keygen -t ed25519 -C "seu.email@example.com"

# Adicionar chave ao ssh-agent
ssh-add $env:USERPROFILE\.ssh\id_ed25519

# Adicionar a chave pública ao GitHub:
# 1. Copiar conteúdo: type $env:USERPROFILE\.ssh\id_ed25519.pub
# 2. GitHub → Settings → SSH and GPG keys → New SSH key
# 3. Cole a chave e salve

# Adicionar origem remota via SSH
git remote add origin git@github.com:SEU_USUARIO/Incluija.git

# Renomear branch padrão para main
git branch -M main

# Fazer push da branch main
git push -u origin main
```

---

## 6️⃣ VERIFICAR CONEXÃO

```powershell
# Verificar remote configurado
git remote -v

# Deve mostrar:
# origin  https://github.com/SEU_USUARIO/Incluija.git (fetch)
# origin  https://github.com/SEU_USUARIO/Incluija.git (push)
```

---

## 7️⃣ CRIAR BRANCHES PARA FEATURES (Opcional)

```powershell
# Criar e ir para branch de feature
git checkout -b feature/melhorias-ui

# Após fazer mudanças
git add .
git commit -m "feat: melhorar layout da tela de candidaturas"
git push -u origin feature/melhorias-ui

# Então criar Pull Request no GitHub
```

---

## 8️⃣ COMANDOS GIT ÚTEIS DIÁRIOS

```powershell
# Ver status
git status

# Ver histórico de commits
git log
git log --oneline
git log --graph --oneline --all

# Ver mudanças não adicionadas
git diff

# Ver mudanças adicionadas
git diff --cached

# Fazer commit de mudanças
git commit -am "mensagem do commit"

# Fazer push para GitHub
git push

# Fazer pull do GitHub
git pull

# Reverter mudanças (desfazer alterações não commitadas)
git checkout -- .

# Desfazer último commit (mantendo mudanças)
git reset --soft HEAD~1

# Desfazer último commit (descartando mudanças)
git reset --hard HEAD~1
```

---

## ⚙️ CONFIGURAÇÃO GLOBAL GIT (Uma única vez)

```powershell
# Configurar nome globalmente
git config --global user.name "Seu Nome Completo"

# Configurar email globalmente
git config --global user.email "seu.email@example.com"

# Verificar configurações
git config --global user.name
git config --global user.email

# Editar config global
git config --global --edit
```

---

## 📋 CHECKLIST COMPLETO

- [ ] Git instalado (`git --version`)
- [ ] Repositório local inicializado (`git init`)
- [ ] Usuário Git configurado
- [ ] Arquivos adicionados (`git add .`)
- [ ] Primeiro commit realizado (`git commit -m "..."`)
- [ ] Repositório criado no GitHub
- [ ] Remote origin configurado (`git remote add origin ...`)
- [ ] Push realizado para GitHub (`git push -u origin main`)
- [ ] Repositório visível no GitHub

---

## 🆘 SOLUÇÃO DE PROBLEMAS

### Erro: "fatal: not a git repository"
```powershell
cd C:\Users\renan\AndroidStudioProjects\Incluija
git init
```

### Erro: "permission denied" ao fazer push
```powershell
# Verificar chave SSH
ssh -T git@github.com

# Ou usar HTTPS em vez de SSH
git remote set-url origin https://github.com/SEU_USUARIO/Incluija.git
```

### Erro: "Master branch not allowed"
```powershell
# Renomear para main
git branch -M main
git push -u origin main
```

---

## 📚 REFERÊNCIAS

- Git Docs: https://git-scm.com/doc
- GitHub Guides: https://guides.github.com/
- Git Cheat Sheet: https://education.github.com/git-cheat-sheet-education.pdf
- Conventional Commits: https://www.conventionalcommits.org/

---

**✅ Parabéns! Seu projeto está versionado no Git e sincronizado com o GitHub!** 🎉

