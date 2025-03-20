package crudClientes

import java.time.LocalDate

data class Cliente(
    var nome: String,
    var email: String,
    var telefone: String,
    var rg: String?= null,
    var cpf: String?= null,
    var cnpj: String?= null,
    var dataNascimento: LocalDate? = null,
    var dataInicio: LocalDate = LocalDate.now(),
    var enderecoCompleto: String? = null,
    var cep: String? = null,
    var descricao: String? = null,
    var inscricaoEstadual: String?= null,
    var isProBono: Boolean = false,
    var isAtivo: Boolean = true,
    var isJuridico: Boolean = false
)
