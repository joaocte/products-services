package br.com.curso.services.impl

import br.com.curso.dto.ProductReq
import br.com.curso.dto.ProductRes
import br.com.curso.dto.ProductUpdateReq
import br.com.curso.exceptions.AlreadyExistsException
import br.com.curso.exceptions.ProductNotFoundException
import br.com.curso.repository.ProductRepository
import br.com.curso.services.ProductService
import br.com.curso.util.toDomain
import br.com.curso.util.toProductRes
import jakarta.inject.Singleton
@Singleton
class ProductServiceImpl(private val productRepository: ProductRepository) : ProductService {
    override fun create(req: ProductReq): ProductRes {
        verifyName(req.name)
        val productSaved = productRepository.save(req.toDomain())
        return productSaved.toProductRes()
    }

    override fun findById(id: Long): ProductRes {
        val findById = productRepository.findById(id)
        findById.orElseThrow { ProductNotFoundException(id) }
        return findById.get().toProductRes()
    }

    override fun update(req: ProductUpdateReq): ProductRes {
        verifyName(req.name)
        val product = productRepository.findById(req.id)
            .orElseThrow { ProductNotFoundException(req.id) }
        val copy = product.copy(
            name = req.name,
            price = req.price,
            quantityInStock = req.quantityInStock
        )

        return productRepository.update(copy).toProductRes()
    }

    override fun delete(id: Long) {
        val product = productRepository.findById(id)
            .orElseThrow { ProductNotFoundException(id) }
        productRepository.delete(product)
    }

    override fun findAll(): List<ProductRes> {
        val findAll = productRepository.findAll()
        return findAll.map { it.toProductRes() }
    }

    private fun verifyName(name: String) {
        productRepository.findByNameIgnoreCase(name)?.let {
            throw AlreadyExistsException(name)
        }
    }
}