package com.jordan.caloriemvvm.domain.use_case.tracker

import com.jordan.caloriemvvm.domain.model.tracker.TrackedFood
import com.jordan.caloriemvvm.domain.repository.TrackerRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

class GetFoodsForDate(
    private val repository: TrackerRepository
) {
    operator fun invoke(date: LocalDate): Flow<List<TrackedFood>> {
        return repository.getFoodForDate(date)
    }
}