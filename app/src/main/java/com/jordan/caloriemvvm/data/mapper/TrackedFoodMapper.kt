package com.jordan.caloriemvvm.data.mapper

import android.os.Build
import androidx.annotation.RequiresApi
import com.jordan.caloriemvvm.data.local.entity.TrackedFoodEntity
import com.jordan.caloriemvvm.domain.model.tracker.MealType
import com.jordan.caloriemvvm.domain.model.tracker.TrackedFood
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
fun TrackedFoodEntity.toTrackedFood(): TrackedFood {
    return TrackedFood(
        name = name,
        carbs = carbs,
        protein = protein,
        fat = fat,
        calories = calories,
        imageUrl = imageUrl,
        mealType = MealType.fromString(type),
        amount = amount,
        date = LocalDate.of(year, month, dayOfMonth),
        id = id
    )
}

@RequiresApi(Build.VERSION_CODES.O)
fun TrackedFood.toTrackedFoodEntity(): TrackedFoodEntity {
    return TrackedFoodEntity(
        name = name,
        carbs = carbs,
        protein = protein,
        fat = fat,
        calories = calories,
        imageUrl = imageUrl,
        type = mealType.name,
        amount = amount,
        year = date.year,
        month = date.monthValue,
        dayOfMonth = date.dayOfMonth,
        id = id
    )
}