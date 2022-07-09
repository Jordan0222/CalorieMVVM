package com.jordan.caloriemvvm.presentation.onboarding.goal

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.jordan.caloriemvvm.R
import com.jordan.caloriemvvm.domain.model.GoalType
import com.jordan.caloriemvvm.presentation.onboarding.components.ActionButton
import com.jordan.caloriemvvm.presentation.onboarding.components.SelectableButton
import com.jordan.caloriemvvm.util.LocalSpacing
import com.jordan.caloriemvvm.util.UiEvent

@Composable
fun GoalScreen(
    onNextClick: () -> Unit,
    viewModel: GoalViewModel = hiltViewModel()
) {
    val spacing = LocalSpacing.current

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Success -> onNextClick()
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
            Text(
                text = stringResource(id = R.string.lose_keep_or_gain_weight),
                style = MaterialTheme.typography.h4
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            Row {
                SelectableButton(
                    text = stringResource(id = R.string.lose),
                    isSelected = viewModel.selectedGoal is GoalType.LoseWeight,
                    color = MaterialTheme.colors.primaryVariant,
                    selectedTextColor = Color.White,
                    onClick = {
                        viewModel.onGoalTypeSelect(GoalType.LoseWeight)
                    },
                    textStyle = MaterialTheme.typography.h6

                )
                Spacer(modifier = Modifier.width(spacing.spaceMedium))
                SelectableButton(
                    text = stringResource(id = R.string.keep),
                    isSelected = viewModel.selectedGoal is GoalType.KeepWeight,
                    color = MaterialTheme.colors.primaryVariant,
                    selectedTextColor = Color.White,
                    onClick = {
                        viewModel.onGoalTypeSelect(GoalType.KeepWeight)
                    },
                    textStyle = MaterialTheme.typography.h6

                )
                Spacer(modifier = Modifier.width(spacing.spaceMedium))
                SelectableButton(
                    text = stringResource(id = R.string.gain),
                    isSelected = viewModel.selectedGoal is GoalType.GainWeight,
                    color = MaterialTheme.colors.primaryVariant,
                    selectedTextColor = Color.White,
                    onClick = {
                        viewModel.onGoalTypeSelect(GoalType.GainWeight)
                    },
                    textStyle = MaterialTheme.typography.h6
                )
            }
        }
        ActionButton(
            text = stringResource(id = R.string.next),
            onClick = { viewModel.onNextClick() },
            modifier = Modifier.align(Alignment.BottomEnd)
        )
    }
}