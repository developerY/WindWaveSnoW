package com.ylabz.windwatersnow.core.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ExpandableToggleButton(
    expanded: Boolean,
    onExpandToggle: (Boolean) -> Unit,
    content: @Composable () -> Unit,
) {
    Column {
        Switch(
            checked = expanded,
            onCheckedChange = onExpandToggle,
            colors = SwitchDefaults.colors(),
        )
        if (expanded) {
            content()
        }
    }
}


@Composable
fun ExpandableComposableWithAnimation(
    expanded: Boolean,
    onExpandToggle: () -> Unit,
    content: @Composable () -> Unit,
) {
    AnimatedVisibility(
        visible = expanded,
        modifier = Modifier.clickable { onExpandToggle() },
    ) {
        content()
    }
}