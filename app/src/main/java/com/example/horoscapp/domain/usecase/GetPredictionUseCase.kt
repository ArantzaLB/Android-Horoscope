package com.example.horoscapp.domain.usecase

import com.example.horoscapp.domain.Repository
import javax.inject.Inject

class GetPredictionUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(sing: String) = repository.getPrediction(sing)

}