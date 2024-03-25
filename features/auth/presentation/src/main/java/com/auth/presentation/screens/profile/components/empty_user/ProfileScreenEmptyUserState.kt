package com.auth.presentation.screens.profile.components.empty_user

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.auth.presentation.R
import com.auth.presentation.screens.profile.state_hoisting.ProfileScreenAction
import com.common.ui.theme.LifestyleHubTheme

@Composable
fun ProfileScreenEmptyUserState(
    onAction: (ProfileScreenAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        item {
            Button(
                onClick = {
                    onAction(ProfileScreenAction.OnLoginButtonClicked)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = LifestyleHubTheme.paddings.medium)
            ) {
                Text(text = stringResource(R.string.login))
            }

            Spacer(modifier = Modifier.height(LifestyleHubTheme.paddings.small))
        }

        item {
            OutlinedButton(
                onClick = {
                    onAction(ProfileScreenAction.OnSignUpButtonClicked)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = LifestyleHubTheme.paddings.medium)
            ) {
                Text(text = stringResource(R.string.sign_up))
            }
        }
    }
}