package crudclientes.clientes.repository

import crudclientes.clientes.entity.Clientes
import org.springframework.data.jpa.repository.JpaRepository


interface ClientesRepository: JpaRepository<Clientes, Int> {
}