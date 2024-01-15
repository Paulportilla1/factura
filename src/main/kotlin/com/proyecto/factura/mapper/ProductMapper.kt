package com.proyecto.factura.mapper

import com.proyecto.factura.dto.ProductDto
import com.proyecto.factura.model.Product


object ProductMapper {
    fun mapToDto(product: Product): ProductDto {

        return ProductDto(
            product.id,
            "${product.description} ${product.brand}"
        )
    }
}