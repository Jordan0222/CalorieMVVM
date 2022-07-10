package com.jordan.caloriemvvm.presentation.tracker.search

import com.jordan.caloriemvvm.domain.model.tracker.TrackableFood

data class TrackableFoodUiState(
    val food: TrackableFood,
    val isExpanded: Boolean = false,
    val amount: String = ""
)
