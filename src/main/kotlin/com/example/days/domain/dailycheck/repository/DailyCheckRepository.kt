package com.example.days.domain.dailycheck.repository

import com.example.days.domain.dailycheck.model.DailyCheck
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DailyCheckRepository: JpaRepository<DailyCheck, Long> {
}