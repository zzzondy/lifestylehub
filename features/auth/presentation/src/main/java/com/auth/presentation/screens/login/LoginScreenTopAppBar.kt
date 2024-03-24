package com.auth.presentation.screens.login

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.auth.presentation.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreenTopAppBar(
    onBackButtonClicked: () -> Unit = {}
) {
    TopAppBar(
        title = {
            Text(text = stringResource(R.string.login))
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