package com.auth.presentation.screens.profile.components.content

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import com.auth.domain.models.User
import com.auth.presentation.R
import com.auth.presentation.screens.profile.state_hoisting.ProfileScreenAction
import com.common.ui.theme.LifestyleHubTheme

@Composable
fun ProfileScreenContentState(
    user: User,
    onAction: (ProfileScreenAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        item {
            AsyncImage(
                model = user.photo,
                contentDescription = stringResource(R.string.user_photo),
                modifier = Modifier
                    .size(LifestyleHubTheme.sizes.extraExtraLarge)
                    .clip(MaterialTheme.shapes.extraLarge),
            )

            Spacer(modifier = Modifier.height(LifestyleHubTheme.paddings.medium))
        }

        item {
            Text(
                text = user.login,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(horizontal = LifestyleHubTheme.paddings.medium)
            )

            Spacer(modifier = Modifier.height(LifestyleHubTheme.paddings.extraSmall))

            Text(
                text = user.email,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(horizontal = LifestyleHubTheme.paddings.medium)
            )

            Spacer(modifier = Modifier.height(LifestyleHubTheme.paddings.small))

            Text(
                text = user.name + " " + user.surname,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(horizontal = LifestyleHubTheme.paddings.medium)
            )

            Spacer(modifier = Modifier.height(LifestyleHubTheme.paddings.small))

            Text(
                text = user.phone,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(horizontal = LifestyleHubTheme.paddings.medium)
            )

            Spacer(modifier = Modifier.height(LifestyleHubTheme.paddings.extraLarge))

            OutlinedButton(
                onClick = { onAction(ProfileScreenAction.OnSignOutButtonClicked) },
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = MaterialTheme.colorScheme.errorContainer,
                    contentColor = MaterialTheme.colorScheme.error
                )
            ) {
                Text(text = stringResource(R.string.sign_out))
            }
        }
    }
}