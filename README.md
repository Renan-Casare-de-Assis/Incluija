# 📱 INCLUIJÁ - Aplicativo Android de Inclusão no Mercado de Trabalho

Um aplicativo Android desenvolvido com **Jetpack Compose** e **Material3** para conectar pessoas em situação de vulnerabilidade com oportunidades de emprego.

## 🎯 Objetivo

O aplicativo INCLUIJÁ visa incluir no mercado de trabalho pessoas que enfrentam barreiras de acesso, oferecendo:

- 📋 Visualização de vagas de emprego
- 📄 Gerenciamento de candidaturas
- ⭐ Perfil profissional completo
- 📊 Rastreamento do processo seletivo
- 🎓 Acesso a cursos de qualificação

## 🛠️ Tecnologias Utilizadas

- **Android**: API 30+
- **Kotlin**: Linguagem principal
- **Jetpack Compose**: Framework UI moderno
- **Material3**: Design system
- **Gradle**: Build system
- **Git**: Controle de versão

## 📂 Estrutura do Projeto

```
Incluija/
├── app/
│   ├── build.gradle.kts
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/br/com/fiap/incluija/
│   │   │   │   ├── MainActivity.kt
│   │   │   │   ├── TelaLogin.kt
│   │   │   │   ├── TelaCadastro.kt
│   │   │   │   ├── TelaPerfil.kt
│   │   │   │   ├── Home.kt
│   │   │   │   └── TelaCandidaturas.kt
│   │   │   ├── res/
│   │   │   └── AndroidManifest.xml
│   │   ├── androidTest/
│   │   └── test/
│   └── proguard-rules.pro
├── gradle/
├── build.gradle.kts
├── settings.gradle.kts
├── gradlew
├── gradlew.bat
├── .gitignore
├── .gitattributes
└── README.md
```

## 🎨 Telas Implementadas

1. **TelaLogin** - Autenticação de usuários
2. **TelaCadastro** - Cadastro com validação de dados
3. **Home** - Dashboard com vagas em destaque
4. **TelaPerfil** - Perfil do usuário com habilidades e experiências
5. **TelaCandidaturas** - Gerenciamento de candidaturas com timeline

## 🚀 Como Começar

### Pré-requisitos

- Android Studio Hedgehog ou superior
- JDK 11+
- Git

### Instalação

```bash
# 1. Clone o repositório
git clone https://github.com/seu-usuario/Incluija.git
cd Incluija

# 2. Abra no Android Studio
# File → Open → Selecione a pasta do projeto

# 3. Deixe o Gradle sincronizar automaticamente
# Ou execute manualmente:
./gradlew build

# 4. Execute o projeto
# Run → Run 'app' ou Shift + F10
```

## 📖 Estrutura de Commits

Este projeto segue a convenção de commits semântica:

- `feat:` Novas features
- `fix:` Correções de bugs
- `docs:` Alterações em documentação
- `style:` Alterações de formatação
- `refactor:` Refatoração de código
- `test:` Adição ou alteração de testes
- `chore:` Tarefas auxiliares (build, deps, etc)

Exemplo:
```
feat: adicionar tela de candidaturas com timeline
fix: corrigir alinhamento de ícones no header
docs: atualizar README com instruções de setup
```

## 🔧 Comandos Úteis

```bash
# Build da aplicação
./gradlew build

# Build e deploy em emulador/dispositivo
./gradlew installDebug

# Executar testes
./gradlew test

# Limpar build
./gradlew clean

# Atualizar dependências
./gradlew dependencyUpdates

# Análise estática de código
./gradlew lint
```

## 📝 Contribuindo

1. Fork o projeto
2. Crie uma branch para sua feature (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'feat: add some AmazingFeature'`)
4. Push para a branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request

## 📄 Licença

Este projeto está licenciado sob a MIT License - veja o arquivo [LICENSE](LICENSE) para detalhes.

## 👥 Autores

- **Seu Nome** - *Desenvolvimento Principal* - [GitHub](https://github.com/seu-usuario)

## 📞 Contato e Suporte

Para dúvidas, sugestões ou relatos de bugs:
- Abra uma [Issue](https://github.com/seu-usuario/Incluija/issues)
- Entre em contato via email

## 🙏 Agradecimentos

- FIAP - Faculdade de Informática e Administração Paulista
- Comunidade Android Brasil
- Todos os contribuidores

---

**Desenvolvido com ❤️ para inclusão social**

