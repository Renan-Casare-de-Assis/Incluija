package br.com.fiap.incluija.data

/**
 * Repositório singleton para gerenciar usuários em memória.
 *
 * IMPORTANTE: Os dados são armazenados apenas em memória (RAM) e serão perdidos
 * quando o aplicativo for reiniciado. Para persistência permanente, será necessário
 * integrar com SQLite/Room no futuro.
 *
 * Usuário padrão para testes:
 * - Email: teste@email.com
 * - Senha: 123456
 */
object RepositorioUsuarios {

    // Lista de usuários em memória
    private val usuarios: MutableList<UsuarioMock> = mutableListOf()

    init {
        // Adicionar usuário padrão para testes
        usuarios.add(
            UsuarioMock(
                email = "teste@email.com",
                senha = "123456",
                nomeCompleto = "Usuário Teste",
                cpf = "12345678901",
                nascimento = "01/01/2000",
                telefone = "11999999999",
                cidade = "São Paulo, SP",
                vulnerabilidades = setOf("PcD")
            )
        )
    }

    /**
     * Registra um novo usuário no repositório.
     *
     * @param usuario Dados do usuário a ser registrado
     * @return true se o usuário foi registrado com sucesso (email não existe)
     * @return false se o email já existe no repositório
     */
    fun registrarUsuario(usuario: UsuarioMock): Boolean {
        // Verificar se o email já existe
        if (usuarios.any { it.email.equals(usuario.email, ignoreCase = true) }) {
            return false // Email já cadastrado
        }

        // Registrar o novo usuário
        usuarios.add(usuario)
        return true
    }

    /**
     * Valida as credenciais de login de um usuário.
     *
     * @param email E-mail do usuário
     * @param senha Senha do usuário
     * @return true se o email e senha correspondem a um usuário registrado
     * @return false se as credenciais são inválidas
     */
    fun validarLogin(email: String, senha: String): Boolean {
        return usuarios.any {
            it.email.equals(email, ignoreCase = true) && it.senha == senha
        }
    }

    /**
     * Obtém um usuário pelo email (para fins informativos).
     * Nota: Implementação para futura expansão.
     *
     * @param email E-mail do usuário
     * @return O usuário se encontrado, null caso contrário
     */
    fun obterUsuarioPorEmail(email: String): UsuarioMock? {
        return usuarios.find { it.email.equals(email, ignoreCase = true) }
    }

    /**
     * Limpa todos os usuários (útil para testes).
     * Mantém apenas o usuário padrão.
     */
    fun limpar() {
        usuarios.clear()
        // Reinicializar usuário padrão
        usuarios.add(
            UsuarioMock(
                email = "teste@email.com",
                senha = "123456",
                nomeCompleto = "Usuário Teste",
                cpf = "12345678901",
                nascimento = "01/01/2000",
                telefone = "11999999999",
                cidade = "São Paulo, SP",
                vulnerabilidades = setOf("PcD")
            )
        )
    }

    /**
     * Retorna a quantidade de usuários registrados (para debugging).
     */
    fun contagemUsuarios(): Int = usuarios.size
}

