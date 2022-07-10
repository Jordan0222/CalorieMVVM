package com.jordan.caloriemvvm.domain.use_case

import com.google.common.truth.Truth.assertThat
import com.jordan.caloriemvvm.domain.model.onboarding.ActivityLevel
import com.jordan.caloriemvvm.domain.model.onboarding.Gender
import com.jordan.caloriemvvm.domain.model.onboarding.GoalType
import com.jordan.caloriemvvm.domain.model.onboarding.UserInfo
import com.jordan.caloriemvvm.domain.model.tracker.MealType
import com.jordan.caloriemvvm.domain.model.tracker.TrackedFood
import com.jordan.caloriemvvm.domain.preferences.Preferences
import com.jordan.caloriemvvm.domain.use_case.tracker.CalculateMealNutrients
import io.mockk.every
import io.mockk.mockk
import org.junit.Before
import org.junit.Test
import java.time.LocalDate
import kotlin.random.Random

class CalculateMealNutrientsTest {

    private lateinit var calculateMealNutrients: CalculateMealNutrients

    @Before
    fun setUp() {
        val preference = mockk<Preferences>(relaxed = true)
        every { preference.loadUserInfo() } returns UserInfo(
            gender = Gender.Male,
            age = 25,
            weight = 60.5f,
            height = 173,
            activityLevel = ActivityLevel.Medium,
            goalType = GoalType.KeepWeight,
            carbRatio = 0.4f,
            proteinRatio = 0.3f,
            fatRatio = 0.3f
        )
        calculateMealNutrients = CalculateMealNutrients(preference)
    }

    @Test
    fun `Calories for breakfast properly calculated`() {
        val trackedFoods = (1..30).map {
            TrackedFood(
                name = "name",
                carbs = Random.nextInt(100),
                protein = Random.nextInt(100),
                fat = Random.nextInt(100),
                mealType = MealType.fromString(
                    listOf("breakfast", "lunch", "dinner", "snack").random()
                ),
                imageUrl = null,
                amount = 100,
                date = LocalDate.now(),
                calories = Random.nextInt(2000)
            )
        }

        val result = calculateMealNutrients(trackedFoods)

        val breakfastCalories = result.mealNutrients.values
            .filter { it.mealType == MealType.Breakfast }
            .sumOf { it.calories }

        val expectedCalories = trackedFoods
            .filter { it.mealType == MealType.Breakfast }
            .sumOf { it.calories }

        assertThat(breakfastCalories).isEqualTo(expectedCalories)
    }

    @Test
    fun `Carbs for dinner properly calculated`() {
        val trackedFoods = (1..30).map {
            TrackedFood(
                name = "name",
                carbs = Random.nextInt(100),
                protein = Random.nextInt(100),
                fat = Random.nextInt(100),
                mealType = MealType.fromString(
                    listOf("breakfast", "lunch", "dinner", "snack").random()
                ),
                imageUrl = null,
                amount = 100,
                date = LocalDate.now(),
                calories = Random.nextInt(2000)
            )
        }

        val result = calculateMealNutrients(trackedFoods)

        val dinnerCarbs = result.mealNutrients.values
            .filter { it.mealType == MealType.Dinner }
            .sumOf { it.carbs }

        val expectedCarbs = trackedFoods
            .filter { it.mealType == MealType.Dinner }
            .sumOf { it.carbs }

        assertThat(dinnerCarbs).isEqualTo(expectedCarbs)
    }
}