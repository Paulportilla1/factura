package com.proyecto.factura.service

import com.proyecto.factura.model.Product
import com.proyecto.factura.repository.ProductRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ProductServiceTest {
    @InjectMocks
    lateinit var productService: ProductService

    @Mock
    lateinit var productRepository: ProductRepository

    val productMock = Product().apply {
        id = 2
        description = "shet"
        brand = "hryt"
        price = 12.7
        stok = 6
    }

    @Test
    fun saveProductCorrect() {
        Mockito.`when`(productRepository.save(Mockito.any(Product::class.java)))
            .thenAnswer { invocation ->
                val savedProduct = invocation.arguments[0] as Product
                savedProduct.id = productMock.id // Simulate persistence and update the ID
                savedProduct
            }

        val response = productService.save(productMock)
        Assertions.assertEquals(productMock.id, response.id)
    }

    @Test
    fun saveProductWhenDescriptionIsBlank() {
        productMock.apply { description = " " }

        Assertions.assertThrows(Exception::class.java) {
            productService.save(productMock)
        }
    }

    @Test
    fun saveProductWhenStockIsNegative() {
        productMock.apply { stok = -1 }

        Assertions.assertThrows(Exception::class.java) {
            productService.save(productMock)
        }
    }
}
