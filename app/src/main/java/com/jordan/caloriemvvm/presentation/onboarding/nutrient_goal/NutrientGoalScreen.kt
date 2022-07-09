package com.jordan.caloriemvvm.presentation.onboarding.nutrient_goal

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.jordan.caloriemvvm.R
import com.jordan.caloriemvvm.presentation.onboarding.components.ActionButton
import com.jordan.caloriemvvm.presentation.onboarding.components.UnitTextField
import com.jordan.caloriemvvm.util.LocalSpacing
import com.jordan.caloriemvvm.util.UiEvent

@Composable
fun NutrientGoalScreen(
    scaffoldState: ScaffoldState,
    onNextClick: () -> Unit,
    viewModel: NutrientGoalViewModel = hiltViewModel()
) {
    val spacing = LocalSpacing.current
    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Success -> onNextClick()
                is UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message.asString(context)
                    )
                }
                else -> Unit
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(spacing.spaceLarge)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(spacing.spaceExtraLarge))
            Text(
                text = stringResource(id = R.string.what_are_your_nutrient_goals),
                style = MaterialTheme.typography.h4
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = spacing.spaceLarge),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top
            ) {
                UnitTextField(
                    value = viewModel.state.carbsRatio,
                    onValueChange = {
                        viewModel.onEvent(NutrientGoalEvent.OnCarbRatioEnter(it))
                    },
                    unit = stringResource(id = R.string.percent_carbs)
                )
                Spacer(modifier = Modifier.height(spacing.spaceMedium))
                UnitTextField(
                    value = viewModel.state.proteinRatio,
                    onValueChange = {
                        viewModel.onEvent(NutrientGoalEvent.OnProteinRatioEnter(it))
                    },
                    unit = stringResource(id = R.string.percent_proteins)
                )
                Spacer(modifier = Modifier.height(spacing.spaceMedium))
                UnitTextField(
                    value = viewModel.state.fatRatio,
                    onValueChange = {
                        viewModel.onEvent(NutrientGoalEvent.OnFatRatioEnter(it))
                    },
                    unit = stringResource(id = R.string.percent_fats)
                )
            }
        }
        ActionButton(
            text = stringResource(id = R.string.next),
            onClick = {
                viewModel.onEvent(NutrientGoalEvent.OnNextClick)
            },
            modifier = Modifier.align(Alignment.BottomEnd)
        )
    }
}