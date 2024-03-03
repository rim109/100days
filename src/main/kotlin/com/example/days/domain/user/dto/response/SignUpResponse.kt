package com.example.days.domain.user.dto.response

import com.example.days.domain.user.model.User

data class SignUpResponse(
    val nickName: String,
    val message: String
) {
    companion object {
        fun from(user: User): SignUpResponse {
            return SignUpResponse(
                nickName = user.nickName,
                message = "회원가입이 완료되었습니다."
            )
        }
    }
}
