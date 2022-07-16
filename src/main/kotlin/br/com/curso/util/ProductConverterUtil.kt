package br.com.curso.util

import br.com.curso.domain.Product
import br.com.curso.dto.ProductReq
import br.com.curso.dto.ProductRes


fun Product.toProductRes(): ProductRes {
    return ProductRes(
        id = id!!,
        name = name,
        price = price,
        quantityInStock = quantityInStock
    )
}

fun ProductReq.toDomain(): Product {
    return Product(
        id = null,
        name = name,
        price = price,
        quantityInStock = quantityInStock
    )
}
