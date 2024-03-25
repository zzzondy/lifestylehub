package com.auth.presentation.screens.login

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import com.auth.presentation.R
import com.auth.presentation.screens.login.state_hoisting.LoginScreenAction
import com.auth.presentation.screens.login.state_hoisting.LoginScreenState
import com.common.ui.theme.LifestyleHubTheme

@Composable
fun LoginScreenContent(
    state: LoginScreenState,
    onAction: (LoginScreenAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    val focusManager = LocalFocusManager.current
    val passwordFocusRequester = FocusRequester()

    var isPasswordHidden by remember {
        mutableStateOf(true)
    }

    LazyColumn(
        modifier = modifier.pointerInput(Unit) {
            detectTapGestures {
                focusManager.clearFocus()
            }
        },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        item {
            Text(
                text = stringResource(R.string.login_description),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = LifestyleHubTheme.paddings.medium),
                textAlign = TextAlign.Center,
            )

            Spacer(modifier = Modifier.height(LifestyleHubTheme.paddings.medium))

            AnimatedVisibility(visible = state.errorMessage != null) {
                if (state.errorMessage != null) {
                    Text(
                        text = state.errorMessage.asString(),
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = LifestyleHubTheme.paddings.medium),
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.error
                    )

                    Spacer(modifier = Modifier.height(LifestyleHubTheme.paddings.medium))
                }
            }
        }

        item {
            OutlinedTextField(
                value = state.login,
                onValueChange = { onAction(LoginScreenAction.OnLoginUpdate(it)) },
                label = {
                    Text(text = stringResource(R.string.login_title))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = LifestyleHubTheme.paddings.medium),
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(onNext = { passwordFocusRequester.requestFocus() }),
                trailingIcon = {
                    if (state.login.isNotBlank()) {
                        IconButton(onClick = { onAction(LoginScreenAction.OnLoginUpdate("")) }) {
                            Icon(
                                imageVector = Icons.Rounded.Clear,
                                contentDescription = stringResource(
                                    R.string.clear
                                )
                            )
                        }
                    }
                },
                leadingIcon = {
                    Icon(
                        painter = painterResource(R.drawable.person),
                        contentDescription = stringResource(R.string.lock)
                    )
                }
            )

            Spacer(modifier = Modifier.height(LifestyleHubTheme.paddings.small))
        }

        item {
            OutlinedTextField(
                value = state.password,
                label = {
                    Text(text = stringResource(R.string.password))
                },
                onValueChange = { onAction(LoginScreenAction.OnPasswordUpdate(it)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = LifestyleHubTheme.paddings.medium)
                    .focusRequester(passwordFocusRequester),
                singleLine = true,
                visualTransformation = if (isPasswordHidden) PasswordVisualTransformation() else VisualTransformation.None,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
                trailingIcon = {
                    IconButton(onClick = { isPasswordHidden = !isPasswordHidden }) {
                        val visibilityIcon =
                            if (isPasswordHidden) R.drawable.visibility else R.drawable.visibility_off

                        Icon(
                            painter = painterResource(visibilityIcon),
                            contentDescription = stringResource(
                                R.string.clear
                            )
                        )
                    }
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Rounded.Lock,
                        contentDescription = stringResource(R.string.lock)
                    )
                }
            )

            Spacer(modifier = Modifier.height(LifestyleHubTheme.paddings.small))
        }

        item {
            Button(
                onClick = {
                    onAction(LoginScreenAction.OnLoginButtonClicked)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = LifestyleHubTheme.paddings.medium)
            ) {
                Text(text = stringResource(R.string.login))
            }
        }
    }
}