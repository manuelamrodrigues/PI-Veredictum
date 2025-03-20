package crudClientes

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/clientes")
class ClienteController {

    val clientes = mutableListOf<Cliente>()

    @PostMapping
    fun criar(@RequestBody novoCliente: Cliente): ResponseEntity<Cliente> {
        clientes.add(novoCliente)
        return ResponseEntity.status(204).body(novoCliente)
    }

    @GetMapping
    fun recuperarTodos(): ResponseEntity<List<Cliente>> {
        return if (clientes.isEmpty()) {
            ResponseEntity.status(204).build()
        } else {
            ResponseEntity.status(200).body(clientes)
        }
    }

    @GetMapping("/{id}") //colocar o Id na URL, necessário quando você quer algo especifico
    fun recuperarEspecifico(@PathVariable id: Int): ResponseEntity<Cliente> {
        if (id in 0..clientes.size - 1) {
            return ResponseEntity.status(201).body(clientes[id])
        }
        return ResponseEntity.status(404).build()
    }

    @PutMapping("/{id}")
    fun atualizarTudo(@PathVariable id: Int, @RequestBody clienteAtualizado: Cliente): ResponseEntity<Cliente> {
        return if (id in 0 until clientes.size - 1) {
            clientes[id] = clienteAtualizado
            ResponseEntity.status(201).body(clienteAtualizado)
        } else {
            return ResponseEntity.status(404).build()
        }
    }

    @PatchMapping("/{id}")
    fun editarEmail(@PathVariable id: Int, @PathVariable emailAtualizado: String): ResponseEntity<Cliente>{
        clientes[id].email = emailAtualizado
        return ResponseEntity.status(200).body(clientes[id])
    }

    @DeleteMapping("/{id}")
    fun excluir(@PathVariable id: Int): ResponseEntity<Cliente>{

        return if(id in 0 .. clientes.size - 1){
            clientes.removeAt(id)
            return ResponseEntity.status(200).build()
        }else {
            return ResponseEntity.status(404).build()
        }
    }
}