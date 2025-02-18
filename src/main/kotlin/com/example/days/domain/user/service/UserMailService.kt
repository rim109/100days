package com.example.days.domain.user.service

import com.example.days.domain.user.dto.request.EmailRequest

interface UserMailService {
    fun sendEmail(request: EmailRequest)
    fun verifyCode(code: String)
}