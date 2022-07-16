package br.com.curso.services

import br.com.curso.dto.ProductReq
import br.com.curso.dto.ProductRes
import br.com.curso.dto.ProductUpdateReq

interface ProductService {
    fun create(req: ProductReq): ProductRes
    fun findById(id: Long): ProductRes
    fun update(req: ProductUpdateReq): ProductRes
    fun delete(id: Long)
    fun findAll(): List<ProductRes>
}