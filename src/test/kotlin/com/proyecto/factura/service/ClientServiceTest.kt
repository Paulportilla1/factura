package com.proyecto.factura.service



import com.proyecto.factura.model.Client
import com.proyecto.factura.repository.ClientRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.springframework.boot.test.context.SpringBootTest


@SpringBootTest
class ClientServiceTest {

    @InjectMocks
    lateinit var clientService: ClientService

    @Mock
    lateinit var clientRepository: ClientRepository

    val clientMock = Client().apply {
        id = 2
        nui = "0301707030"
        fullname = "Juan"
        address = "Cuenca" // Corrected typo in address
    }

    @Test
    fun saveClientCorrect() {
        Mockito.`when`(clientRepository.save(Mockito.any(Client::class.java))).thenReturn(clientMock)
        val response = clientService.save(clientMock)
        Assertions.assertEquals(clientMock.id, response.id)
    }

    @Test
    fun saveClientWhenFullnameIsBlank() {
        Assertions.assertThrows(Exception::class.java) {
            clientMock.apply { fullname = " " }
            Mockito.`when`(clientRepository.save(Mockito.any(Client::class.java))).thenReturn(clientMock)
            clientService.save(clientMock)
        }
    }

    @Test
    fun saveClientWhenAddressIsBlank() {
        Assertions.assertThrows(Exception::class.java) {
            clientMock.apply { address = " " }
            Mockito.`when`(clientRepository.save(Mockito.any(Client::class.java))).thenReturn(clientMock)
            clientService.save(clientMock)
        }
    }
}
