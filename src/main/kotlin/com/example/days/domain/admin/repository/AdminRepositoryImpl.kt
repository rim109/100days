package com.example.days.domain.admin.repository

import com.example.days.domain.user.model.QUser
import com.example.days.domain.user.model.User
import com.example.days.global.infra.queryDSL.QueryDslSupport
import com.querydsl.core.BooleanBuilder
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository

//ㅇㅅㅇ 페이지네이션 추가로 status 할 예정
@Repository
class AdminRepositoryImpl : AdminCustomRepository, QueryDslSupport() {
    private val user = QUser.user
    override fun findByPageableUser(pageable: Pageable): Page<User> {
        val whereClause = BooleanBuilder()
        val totalCount = queryFactory.select(user.count()).from(user).where(whereClause).fetchOne() ?: 0L

        val query = queryFactory.selectFrom(user)
            .where(whereClause)
            .offset(pageable.offset)
            .limit(pageable.pageSize.toLong())
        if (pageable.sort.isSorted) {
            when (pageable.sort.first()?.property) {
                "id" -> query.orderBy(user.id.asc())
                "email" -> query.orderBy(user.email.asc())
                else -> query.orderBy(user.id.asc())
            }
        } else {
            query.orderBy(user.id.asc())
        }
        val contents = query.fetch()
        return PageImpl(contents, pageable, totalCount)
    }
}