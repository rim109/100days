package com.example.days.domain.like.service

import com.example.days.domain.like.dto.request.LikeRequest
import com.example.days.domain.like.dto.response.LikeResponse
import com.example.days.domain.like.repository.LikeRepository
import com.example.days.domain.resolution.repository.ResolutionRepository
import com.example.days.domain.user.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class LikeServiceImpl(
    private val userRepository: UserRepository,
    private val resolutionRepository: ResolutionRepository,
    private val likeRepository: LikeRepository
): LikeService {
    @Transactional
    override fun insertLike(request: LikeRequest) {
        val user = userRepository.findByIdOrNull(request.userId) ?: TODO("예외처리")
        val resolution = resolutionRepository.findByIdOrNull(request.resolutionId) ?: TODO("예외처리")
        if (!likeRepository.existsByUserAndResolution(user, resolution)){
            resolution.updateLikeCount(true)
            likeRepository.save(LikeResponse.from(user, resolution))
        }
        else{
            TODO("이미 좋아요를 눌렀을 때")
        }
    }

    @Transactional
    override fun deleteLike(request: LikeRequest) {
        val user = userRepository.findByIdOrNull(request.userId) ?: TODO("예외처리")
        val resolution = resolutionRepository.findByIdOrNull(request.resolutionId) ?: TODO("예외처리")
        likeRepository.findByUserAndResolution(user, resolution)
            ?.let {
                resolution.updateLikeCount(false)
                likeRepository.delete(it) }
            ?: TODO("예외처리")
    }
}