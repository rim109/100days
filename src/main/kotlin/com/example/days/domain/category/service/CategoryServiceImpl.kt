package com.example.days.domain.category.service

import com.example.days.domain.category.dto.request.CategoryRequest
import com.example.days.domain.category.dto.request.CategoryUpdateRequest
import com.example.days.domain.category.dto.response.CategoryResponse
import com.example.days.domain.category.dto.response.CategoryResponse.Companion.from
import com.example.days.domain.category.model.Category
import com.example.days.domain.category.repository.CategoryRepository
import com.example.days.global.common.exception.ModelNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CategoryServiceImpl(
    private val categoryRepository: CategoryRepository
) : CategoryService {
    override fun getCategoryList(): List<CategoryResponse> {
        return categoryRepository.findAll().map { from(it) }
    }

    @Transactional
    override fun createCategory(req: CategoryRequest): CategoryResponse {
        val category = categoryRepository.save(
            Category(
                name = req.name,
                info = req.info
            )
        )
        return CategoryResponse.from(category)
    }

    @Transactional
    override fun updateCategory(categoryId: Long, req: CategoryUpdateRequest): CategoryResponse {
        val category =
            categoryRepository.findByIdOrNull(categoryId) ?: throw ModelNotFoundException("category", categoryId)
        category.info = req.info
        return CategoryResponse.from(category)
    }

    @Transactional
    override fun deleteCategory(categoryId: Long) {
        val category =
            categoryRepository.findByIdOrNull(categoryId) ?: throw ModelNotFoundException("category", categoryId)
        categoryRepository.delete(category)
    }
}