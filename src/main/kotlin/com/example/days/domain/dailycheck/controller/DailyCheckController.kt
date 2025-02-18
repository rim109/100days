package com.example.days.domain.dailycheck.controller

import com.example.days.domain.dailycheck.dto.request.DailyCheckRequest
import com.example.days.domain.dailycheck.dto.response.DailyCheckResponse
import com.example.days.domain.dailycheck.service.DailyCheckService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/daily_check/{resolutionId}")
class DailyCheckController(
    private val dailyCheckService: DailyCheckService
) {
    @PostMapping
    fun createDailyCheck(
        @PathVariable resolutionId: Long,
        @RequestBody dailyCheckRequest: DailyCheckRequest
    ): ResponseEntity<DailyCheckResponse>{
        val createdDailyCheck = dailyCheckService.createDailyCheck(resolutionId, dailyCheckRequest)
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDailyCheck)
    }

    @GetMapping
    fun getDailyCheck(
        @PathVariable resolutionId: Long
    ): ResponseEntity<List<DailyCheckResponse>>{
        val dailyCheck = dailyCheckService.getDailyCheckByList(resolutionId)
        return ResponseEntity.ok(dailyCheck)
    }

    @PatchMapping("/{dailyCheckId}")
    fun updateDailyCheckMemo(
        @PathVariable resolutionId: Long,
        @PathVariable dailyCheckId: Long,
        @RequestBody dailyCheckRequest: DailyCheckRequest
    ): ResponseEntity<DailyCheckResponse>{
        val updatedDailyCheck = dailyCheckService.updateDailyCheck(resolutionId, dailyCheckId, dailyCheckRequest)
        return ResponseEntity.ok(updatedDailyCheck)
    }

    @DeleteMapping("/{dailyCheckId}")
    fun deleteDailyCheck(
        @PathVariable resolutionId: Long,
        @PathVariable dailyCheckId: Long
    ): ResponseEntity<Unit>{
        dailyCheckService.deleteDailyCheck(dailyCheckId)
        return ResponseEntity.noContent().build()
    }
}