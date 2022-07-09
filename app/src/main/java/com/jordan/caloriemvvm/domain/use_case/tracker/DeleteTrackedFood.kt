package com.jordan.caloriemvvm.domain.use_case.tracker

import com.jordan.caloriemvvm.domain.model.tracker.TrackedFood
import com.jordan.caloriemvvm.domain.repository.TrackerRepository

class DeleteTrackedFood(
    private val repository: TrackerRepository
) {

    suspend operator fun invoke(trackedFood: TrackedFood) {
        return repository.deleteTrackedFood(trackedFood)
    }
}