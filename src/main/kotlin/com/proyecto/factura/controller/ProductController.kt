package com.proyecto.factura.controller

import com.proyecto.factura.dto.ProductDto
import com.proyecto.factura.model.Product
import com.proyecto.factura.service.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/product")   //endpoint
@CrossOrigin(methods = [RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.PUT, RequestMethod.DELETE])
class ProductController {
    @Autowired
    lateinit var productService: ProductService

    @GetMapping
    fun list (product: Product, pageable: Pageable):ResponseEntity<*>{
        val response= productService.list(pageable,product)
        return ResponseEntity(response, HttpStatus.OK)
    }
//@RequestParam searchValue:String

    @PostMapping
    fun save (@RequestBody product: Product): ResponseEntity<Product> {
        return ResponseEntity(productService.save(product), HttpStatus.OK )
    }

    @PutMapping
    fun update (@RequestBody product: Product): ResponseEntity<Product> {
        return ResponseEntity(productService.update(product), HttpStatus.OK)
    }

    @PatchMapping
    fun updateName (@RequestBody product: Product): ResponseEntity<Product> {
        return ResponseEntity(productService.updateName(product), HttpStatus.OK)
    }

    @DeleteMapping("/delete/{id}")
    fun delete (@PathVariable("id") id: Long):Boolean? {
        return productService.delete(id)
    }

    @GetMapping("/{id}")
    fun listById (@PathVariable("id") id: Long): ResponseEntity<*> {
        return ResponseEntity(productService.listById (id), HttpStatus.OK)

    }
    @GetMapping("/product-projection")
    fun listDto(): List<ProductDto> {
        return productService.listDto()

    }
}