package com.jordan.caloriemvvm.domain.use_case.tracker

import com.jordan.caloriemvvm.domain.model.tracker.TrackableFood
import com.jordan.caloriemvvm.domain.repository.TrackerRepository

class SearchFood(
    private val repository: TrackerRepository
) {

    suspend operator fun invoke(
        query: String,
        page: Int = 1,
        pageSize: Int = 40
    ): Result<List<TrackableFood>> {
        if (query.isBlank()) {
            return Result.success(emptyList())
        }
        // trim: 把空白的部份去掉，" hello " -> "hello"
        return repository.searchFood(query.trim(), page, pageSize)
    }
}