package com.feature_main.presentation.screens.main.components.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.common.ui.theme.LifestyleHubTheme
import com.feature_main.presentation.R

@Composable
fun ErrorWhenAppendWidget(
    title: String,
    modifier: Modifier = Modifier,
    onRefresh: () -> Unit = {},
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleSmall,
            textAlign = TextAlign.Center,
            maxLines = 2
        )

        Spacer(modifier = Modifier.height(LifestyleHubTheme.paddings.medium))

        Button(onClick = onRefresh) {
            Text(text = stringResource(R.string.try_again))
        }
    }
}