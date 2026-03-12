package br.com.fiap.incluija.data

/**
 * Classe de dados que representa um usuário mockado.
 * Usada para armazenar informações de usuários em memória.
 *
 * @param email E-mail único do usuário
 * @param senha Senha do usuário
 * @param nomeCompleto Nome completo do usuário
 * @param cpf CPF do usuário
 * @param nascimento Data de nascimento do usuário
 * @param telefone Telefone/WhatsApp do usuário
 * @param cidade Cidade e estado do usuário
 * @param vulnerabilidades Set de perfis de vulnerabilidade selecionados
 */
data class UsuarioMock(
    val email: String,
    val senha: String,
    val nomeCompleto: String,
    val cpf: String,
    val nascimento: String,
    val telefone: String,
    val cidade: String,
    val vulnerabilidades: Set<String>
)

