package com.example.days.domain.admin.repository

import com.example.days.domain.admin.model.Admin
import com.example.days.domain.user.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface AdminRepository : JpaRepository<Admin, Long>, AdminCustomRepository {
    fun existsByEmail(email: String): Boolean

    fun existsByNickname(nickname: String): Boolean

    fun findAdminByEmail(email: String): Admin?
}