package com.jordan.caloriemvvm.presentation.tracker.tracker_overview

import com.jordan.caloriemvvm.domain.model.tracker.TrackedFood


sealed class TrackerOverViewEvent {
    object OnNextDayClick: TrackerOverViewEvent()
    object OnPreviousDayClick: TrackerOverViewEvent()
    data class OnToggleMealClick(val meal: Meal): TrackerOverViewEvent()
    data class OnDeleteTrackedFoodClick(val trackedFood: TrackedFood): TrackerOverViewEvent()
}
