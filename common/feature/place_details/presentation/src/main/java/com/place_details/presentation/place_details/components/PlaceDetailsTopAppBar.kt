package com.place_details.presentation.place_details.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.place_details.presentation.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaceDetailsTopAppBar(
    onBackButtonClicked: () -> Unit = {}
) {
    TopAppBar(
        title = {
            Text(text = stringResource(R.string.place_details))
        },
        navigationIcon = {
            IconButton(onClick = onBackButtonClicked) {
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                    contentDescription = stringResource(
                        R.string.arrow_back
                    )
                )
            }
        }
    )
}