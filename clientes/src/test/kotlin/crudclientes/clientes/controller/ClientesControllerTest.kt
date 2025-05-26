package crudclientes.clientes.controller

import crudclientes.clientes.entity.Clientes
import crudclientes.clientes.repository.ClientesRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import java.time.LocalDateTime
import java.util.Optional
import kotlin.collections.get

class ClientesControllerTest {

    val repository = mock(ClientesRepository::class.java)
    val controller = ClientesController(repository)
    lateinit var cliente: Clientes



    // criando um mock global para facilitar os testes
    @BeforeEach
    fun setup() {
        cliente = Clientes(
            id = 1,
            nome = "Maria",
            email = "maria@email.com",
            rg = "12345678",
            cpf = "123.456.789-00",
            cnpj = null,
            dataNascimento = LocalDateTime.now(),
            dataInicio = LocalDateTime.now(),
            endereco = "Rua das Flores",
            cep = "12345-678",
            descricao = "Cliente fiel",
            inscricaoEstadual = null,
            isProBono = false,
            isAtivo = true,
            isJuridico = false
        )
    }


    @Test
    fun `buscar todos os clientes e retornar status 200 com a lista`(){

        //uso do mockito para simular o comportamento do repositório. quando o findAll for chamado, vai retornar a lista do before each
        `when`(repository.findAll()).thenReturn(listOf(cliente))

        //o null significa que va buscar todos
        val retorno = controller.buscar(null)


        //verifica se o status é 200
        assertEquals(200, retorno.statusCode.value())

        //verifica se o corpo da resposta não é nulo, ou seja maior que 1
        assertEquals(1, retorno.body?.size)
    }


    @Test
    fun `buscar todos os clientes e retornar status 204 com lista vazia`(){
        `when`(repository.findAll()).thenReturn(emptyList())

        //mockito vai buscar por uma lista vazia e retorna-la
        val retorno = controller.buscar(null)

        //verifica se o status é 204, ou seja, sem conteúdo
        assertEquals(204, retorno.statusCode.value())

        //verifica se o corpo da resposta é vazio, pq nao da pra retornar
        assertNull(retorno.body)
    }


    @Test
    fun `buscar cliente por id retorna status 200 com o cliente`() {

        // o optional vai simular a existencia do id 1 no banco de dados
        `when`(repository.findById(1)).thenReturn(Optional.of(cliente))
        val retorno = controller.buscarPorId(1)

        //verifica o status
        assertEquals(200, retorno.statusCode.value())

        //verifica se o corpo NÃO é nulo, ou seja, foi retornado
        assertNotNull(retorno.body)

        //ve se o objeto retornado é igual o cliente
        assertEquals(cliente, retorno.body)
    }


    @Test
    fun `buscar cliente por id inexistente retorna status 404`() {
        `when`(repository.findById(999)).thenReturn(Optional.empty())

        val retorno = controller.buscarPorId(999)

        assertEquals(404, retorno.statusCode.value())
    }


    @Test
    fun `cadastrar cliente e retorna status 201 com o cliente criado`() {
        `when`(repository.save(cliente)).thenReturn(cliente)
        val resposta = controller.cadastrar(cliente)


        assertEquals(201, resposta.statusCode.value())
        assertNotNull(resposta.body)
        assertEquals(cliente, resposta.body)

        //verifica se salvou o  novo cliente
        verify(repository, times(1)).save(cliente)
    }


    @Test
    fun `atualizar cliente e retorna status 200 com o cliente atualizado`() {

        //mockito ve se ja existe no banco
        `when`(repository.existsById(1)).thenReturn(true)

        //ao salvar o cliente, retorna o proprio cliente
        `when`(repository.save(cliente)).thenReturn(cliente)


        val resposta = controller.atualizar(1, cliente)


        assertEquals(200, resposta.statusCode.value())
        assertNotNull(resposta.body)
        assertEquals(cliente, resposta.body)

        verify(repository, times(1)).save(cliente)

    }


    @Test
    fun `atualizar parcialmente o cliente e retornar status 200 com o cliente atualizado`() {
        `when`(repository.findById(1)).thenReturn(Optional.of(cliente))
        `when`(repository.save(cliente)).thenReturn(cliente)

        // Cria um mapa com as atualizações parciais
        val atualizacoes = mapOf("nome" to "Maria Atualizada", "email" to "maria.atualizada@email.com")


        val resposta = controller.atualizarParcial(1, atualizacoes)


        assertEquals(200, resposta.statusCode.value())


        assertNotNull(resposta.body)
        assertEquals("Maria Atualizada", resposta.body?.nome)
        assertEquals("maria.atualizada@email.com", resposta.body?.email)


        verify(repository, times(1)).save(cliente)
    }



    @Test
    fun `deletar cliente existente e retornar status 204`() {
        `when`(repository.existsById(1)).thenReturn(true)
        val resposta = controller.deletar(1)


        assertEquals(204, resposta.statusCode.value())
        verify(repository, times(1)).deleteById(1)
    }

    @Test
    fun `deletar cliente existente e retornar status 404`() {
        val idInvalido = -1

        val resposta = controller.deletar(idInvalido)
        assertEquals(404, resposta.statusCode.value())
    }


}