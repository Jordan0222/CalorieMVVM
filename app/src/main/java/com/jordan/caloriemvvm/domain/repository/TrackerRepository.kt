package com.jordan.caloriemvvm.domain.repository

import com.jordan.caloriemvvm.domain.model.tracker.TrackableFood
import com.jordan.caloriemvvm.domain.model.tracker.TrackedFood
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface TrackerRepository {

    suspend fun searchFood(
        query: String,
        page: Int,
        pageSize: Int
    ): Result<List<TrackableFood>>

    suspend fun insertTrackedFood(trackedFood: TrackedFood)

    suspend fun deleteTrackedFood(trackedFood: TrackedFood)

    fun getFoodForDate(localDate: LocalDate): Flow<List<TrackedFood>>
}