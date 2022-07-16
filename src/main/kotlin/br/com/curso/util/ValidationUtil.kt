package br.com.curso.util

import br.com.curso.ProductServiceRequest
import br.com.curso.ProductServiceUpdateRequest
import br.com.curso.exceptions.InvalidArgumentException

class ValidationUtil {
    companion object {
        fun validatePayload(payload: ProductServiceRequest?): ProductServiceRequest {
            payload?.let {
                if (it.name.isNullOrBlank())
                    throw InvalidArgumentException("nome")

                if (it.price.isNaN() || it.price < 0)
                    throw InvalidArgumentException("preço")
                return it
            }
            throw InvalidArgumentException("payload")
        }

        fun validateUpdatePayload(payload: ProductServiceUpdateRequest?): ProductServiceUpdateRequest {
            payload?.let {
                if (it.id <= 0L)
                    throw InvalidArgumentException("ID")

                if (it.name.isNullOrBlank())
                    throw InvalidArgumentException("nome")

                if (it.price.isNaN() || it.price < 0)
                    throw InvalidArgumentException("preço")
                return it
            }
            throw InvalidArgumentException("payload")
        }
    }
}