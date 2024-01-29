package com.proyecto.factura.service

import com.google.gson.Gson
import com.proyecto.factura.model.Client
import com.proyecto.factura.model.Invoice
import com.proyecto.factura.repository.ClientRepository
import com.proyecto.factura.repository.InvoiceRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.springframework.boot.test.context.SpringBootTest
import java.io.File



@SpringBootTest
class InvoiceServiceTest {

    @InjectMocks
    lateinit var invoiceService: InvoiceService

    @Mock
    lateinit var invoiceRepository: InvoiceRepository

    @Mock
    lateinit var clientRepository: ClientRepository

    val jsonString = File("./src/test/resources/invoice.json").readText(Charsets.UTF_8)
    val invoiceMock = Gson().fromJson(jsonString, Invoice::class.java)

    val clientMock = Client().apply {
        id=1
        nui="0301707030"
        fullname="Juan"
        address= "Ceunca"
    }

    @Test
    fun saveInvoiceWhenIsCorrect(){
        Mockito.`when`(clientRepository.findById(invoiceMock.clientId)).thenReturn(clientMock)
        Mockito.`when`(invoiceRepository.save(Mockito.any(Invoice::class.java))).thenReturn(invoiceMock)
        val actualResponse = invoiceService.save(invoiceMock)
        Assertions.assertEquals(actualResponse.id, invoiceMock.id)
        //Lee el archivo desde l ruta
        val jsonString = File("./src/test/resources/invoice.json").readText(Charsets.UTF_8)
// convierte en objeto de tipo Invoice
        val invoiceMock = Gson().fromJson(jsonString, Invoice::class.java)
    }



}

