package com.jordan.caloriemvvm.di

import com.jordan.caloriemvvm.domain.use_case.onboarding.ValidateNutrients
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object OnBoardingDomainModule {

    @Provides
    @ViewModelScoped
    fun provideValidateNutrientUseCase(): ValidateNutrients {
        return ValidateNutrients()
    }
}