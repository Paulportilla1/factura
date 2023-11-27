package com.proyecto.factura.repository

import com.proyecto.factura.model.Invoice
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface InvoiceRepository: JpaRepository<Invoice, Long?> {
    fun findById (id: Long?): Invoice?

}