package com.place_details.presentation.place_details.components.error

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.common.ui.theme.LifestyleHubTheme
import com.place_details.presentation.R

@Composable
fun PlaceDetailsScreenErrorState(
    text: String,
    modifier: Modifier = Modifier,
    onTryAgainClicked: () -> Unit = {},
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(horizontal = LifestyleHubTheme.paddings.medium),
            textAlign = TextAlign.Center,
        )
        
        Spacer(modifier = Modifier.height(LifestyleHubTheme.paddings.medium))

        Button(onClick = onTryAgainClicked) {
            Text(text = stringResource(R.string.try_again))
        }
    }
}