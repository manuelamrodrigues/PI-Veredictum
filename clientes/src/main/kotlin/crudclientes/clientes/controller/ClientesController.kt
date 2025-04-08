package crudclientes.clientes.controller

import crudclientes.clientes.entity.Clientes
import crudclientes.clientes.repository.ClientesRepository
import jakarta.validation.Valid
import org.springframework.data.domain.Sort
import org.springframework.http.ResponseEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime


@RestController
@RequestMapping("/clientes")
class ClientesController(val repositorio: ClientesRepository) {

    @GetMapping
    fun buscar(): ResponseEntity<List<Clientes>> {
        val clientes = repositorio.findAll(Sort.by(Sort.Order.asc("nome"))) //COLOCAR FIND ALL

        return if (clientes.isEmpty()) {
            ResponseEntity.status(204).build()
        } else {
            ResponseEntity.status(200).body(clientes)
        }
    }

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Int): ResponseEntity<Clientes> {
        val clientes = repositorio.findById(id)
        return ResponseEntity.of(clientes)
    }

    @PostMapping
    fun cadastrar(@RequestBody @Valid novoCliente: Clientes): ResponseEntity<Clientes>{
        val cliente = repositorio.save(novoCliente)
        return ResponseEntity.status(201).body(novoCliente)
    }

    @PutMapping("/{id}")
    fun atualizar(@PathVariable id: Int, @RequestBody clientesAtualizados: Clientes): ResponseEntity<Clientes>{
        if(repositorio.existsById(id)){
            clientesAtualizados.id
            val clientes = repositorio.save(clientesAtualizados)
            return ResponseEntity.status(200).body(clientesAtualizados)
        }else{
            return ResponseEntity.status(404).build()
        }
    }



    @PatchMapping("/clientes/{id}")
    fun atualizarNome(@PathVariable id: Int, @RequestParam nome: String): ResponseEntity<Clientes> {
        val cliente = repositorio.findById(id)
        if (cliente.isEmpty) {
            return ResponseEntity.status(404).build()
        }
        val clienteAtualizado = cliente.get()
        clienteAtualizado.nome = nome
        repositorio.save(clienteAtualizado)
        return ResponseEntity.status(200).body(clienteAtualizado)
    }


    @PatchMapping("/clientes/{id}")
    fun atualizarEmail(@PathVariable id: Int, @RequestParam email: String): ResponseEntity<Clientes> {
        val cliente = repositorio.findById(id)
        if (cliente.isEmpty) {
            return ResponseEntity.status(404).build()
        }
        val clienteAtualizado = cliente.get()
        clienteAtualizado.email= email
        repositorio.save(clienteAtualizado)
        return ResponseEntity.status(200).body(clienteAtualizado)
    }


    @PatchMapping("/clientes/{id}")
    fun atualizarRg(@PathVariable id: Int, @RequestParam rg: String): ResponseEntity<Clientes> {
        val cliente = repositorio.findById(id)
        if (cliente.isEmpty) {
            return ResponseEntity.status(404).build()
        }
        val clienteAtualizado = cliente.get()
        clienteAtualizado.rg= rg
        repositorio.save(clienteAtualizado)
        return ResponseEntity.status(200).body(clienteAtualizado)
    }

    @PatchMapping("/clientes/{id}")
    fun atualizarCpf(@PathVariable id: Int, @RequestParam cpf: String): ResponseEntity<Clientes> {
        val cliente = repositorio.findById(id)
        if (cliente.isEmpty) {
            return ResponseEntity.status(404).build()
        }
        val clienteAtualizado = cliente.get()
        clienteAtualizado.cpf= cpf
        repositorio.save(clienteAtualizado)
        return ResponseEntity.status(200).body(clienteAtualizado)
    }

    @PatchMapping("/clientes/{id}")
    fun atualizarCnpj(@PathVariable id: Int, @RequestParam cnpj: String): ResponseEntity<Clientes> {
        val cliente = repositorio.findById(id)
        if (cliente.isEmpty) {
            return ResponseEntity.status(404).build()
        }
        val clienteAtualizado = cliente.get()
        clienteAtualizado.cnpj= cnpj
        repositorio.save(clienteAtualizado)
        return ResponseEntity.status(200).body(clienteAtualizado)
    }

    @PatchMapping("/clientes/{id}")
    fun atualizarDataNascimento(@PathVariable id: Int, @RequestParam dataNascimento: LocalDateTime): ResponseEntity<Clientes> {
        val cliente = repositorio.findById(id)
        if (cliente.isEmpty) {
            return ResponseEntity.status(404).build()
        }
        val clienteAtualizado = cliente.get()
        clienteAtualizado.dataNascimento= dataNascimento
        repositorio.save(clienteAtualizado)
        return ResponseEntity.status(200).body(clienteAtualizado)
    }

    @PatchMapping("/clientes/{id}")
    fun atualizarDataInicio(@PathVariable id: Int, @RequestParam dataInicio: LocalDateTime): ResponseEntity<Clientes> {
        val cliente = repositorio.findById(id)
        if (cliente.isEmpty) {
            return ResponseEntity.status(404).build()
        }
        val clienteAtualizado = cliente.get()
        clienteAtualizado.dataInicio= dataInicio
        repositorio.save(clienteAtualizado)
        return ResponseEntity.status(200).body(clienteAtualizado)
    }

    @PatchMapping("/clientes/{id}")
    fun atualizarEndereco(@PathVariable id: Int, @RequestParam endereco: String): ResponseEntity<Clientes> {
        val cliente = repositorio.findById(id)
        if (cliente.isEmpty) {
            return ResponseEntity.status(404).build()
        }
        val clienteAtualizado = cliente.get()
        clienteAtualizado.endereco= endereco
        repositorio.save(clienteAtualizado)
        return ResponseEntity.status(200).body(clienteAtualizado)
    }

    @PatchMapping("/clientes/{id}")
    fun atualizarCep(@PathVariable id: Int, @RequestParam cep: String): ResponseEntity<Clientes> {
        val cliente = repositorio.findById(id)
        if (cliente.isEmpty) {
            return ResponseEntity.status(404).build()
        }
        val clienteAtualizado = cliente.get()
        clienteAtualizado.cep= cep
        repositorio.save(clienteAtualizado)
        return ResponseEntity.status(200).body(clienteAtualizado)
    }

    @PatchMapping("/clientes/{id}")
    fun atualizarDescricao(@PathVariable id: Int, @RequestParam descricao: String): ResponseEntity<Clientes> {
        val cliente = repositorio.findById(id)
        if (cliente.isEmpty) {
            return ResponseEntity.status(404).build()
        }
        val clienteAtualizado = cliente.get()
        clienteAtualizado.descricao= descricao
        repositorio.save(clienteAtualizado)
        return ResponseEntity.status(200).body(clienteAtualizado)
    }

    @PatchMapping("/clientes/{id}")
    fun atualizarInscricaoEstadual(@PathVariable id: Int, @RequestParam inscricaoEstadual: String): ResponseEntity<Clientes> {
        val cliente = repositorio.findById(id)
        if (cliente.isEmpty) {
            return ResponseEntity.status(404).build()
        }
        val clienteAtualizado = cliente.get()
        clienteAtualizado.inscricaoEstadual= inscricaoEstadual
        repositorio.save(clienteAtualizado)
        return ResponseEntity.status(200).body(clienteAtualizado)
    }

    @PatchMapping("/clientes/{id}")
    fun atualizarProBono(@PathVariable id: Int, @RequestParam proBono: Boolean): ResponseEntity<Clientes> {
        val cliente = repositorio.findById(id)
        if (cliente.isEmpty) {
            return ResponseEntity.status(404).build()
        }
        val clienteAtualizado = cliente.get()
        clienteAtualizado.isProBono= proBono
        repositorio.save(clienteAtualizado)
        return ResponseEntity.status(200).body(clienteAtualizado)
    }

    @PatchMapping("/clientes/{id}")
    fun atualizarAtivo(@PathVariable id: Int, @RequestParam ativo: Boolean): ResponseEntity<Clientes> {
        val cliente = repositorio.findById(id)
        if (cliente.isEmpty) {
            return ResponseEntity.status(404).build()
        }
        val clienteAtualizado = cliente.get()
        clienteAtualizado.isAtivo= ativo
        repositorio.save(clienteAtualizado)
        return ResponseEntity.status(200).body(clienteAtualizado)
    }

    @PatchMapping("/clientes/{id}")
    fun atualizarJridico(@PathVariable id: Int, @RequestParam juridico: Boolean): ResponseEntity<Clientes> {
        val cliente = repositorio.findById(id)
        if (cliente.isEmpty) {
            return ResponseEntity.status(404).build()
        }
        val clienteAtualizado = cliente.get()
        clienteAtualizado.isJuridico= juridico
        repositorio.save(clienteAtualizado)
        return ResponseEntity.status(200).body(clienteAtualizado)
    }

    @DeleteMapping("/{id}")
    fun deletar(@PathVariable id: Int): ResponseEntity<Void> {
        if (repositorio.existsById(id)) {
            repositorio.deleteById(id)
            return ResponseEntity.status(204).build()
        }
        return ResponseEntity.status(404).build()
    }










}