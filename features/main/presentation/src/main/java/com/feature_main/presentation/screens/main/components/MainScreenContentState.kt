package com.feature_main.presentation.screens.main.components

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.common.ui.theme.LifestyleHubTheme
import com.common.ui.utils.UIText
import com.feature_main.presentation.R
import com.feature_main.presentation.screens.main.state_hoisting.MainScreenAction
import com.main.domain.models.WeatherOnUserLocation

@Composable
fun MainScreenContentState(
    userWeather: WeatherOnUserLocation?,
    isRationaleShowLocationPermissionDialog: Boolean,
    modifier: Modifier = Modifier,
    errorText: UIText? = null,
    onAction: (MainScreenAction) -> Unit = {},
) {
    val context = LocalContext.current
    val permissionRequestLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            onAction(MainScreenAction.OnLocationPermissionGranted)
        } else {
            if ((ActivityCompat.shouldShowRequestPermissionRationale(
                    context as Activity,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ).not() && isRationaleShowLocationPermissionDialog.not())
            ) {
                onAction(MainScreenAction.UpdateLocationPermissionFlag(true))
            }
        }
    }

    var isLocationPermissionDialogVisible by remember { mutableStateOf(false) }
    var isLocationPermissionLastDialogVisible by remember { mutableStateOf(false) }

    if (isLocationPermissionDialogVisible) {
        LocationPermissionExplanationDialog(
            onDismiss = {
                isLocationPermissionDialogVisible = false
            },
            onConfirm = {
                isLocationPermissionDialogVisible = false
                permissionRequestLauncher.launch(Manifest.permission.ACCESS_COARSE_LOCATION)
            },
            title = stringResource(R.string.location_permission_explanation)
        )
    }

    if (isLocationPermissionLastDialogVisible) {
        LocationPermissionExplanationDialog(
            onDismiss = {
                isLocationPermissionLastDialogVisible = false
            },
            onConfirm = {
                isLocationPermissionLastDialogVisible = false
                (context as Activity).startActivity(
                    Intent(
                        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                        Uri.parse("package:" + context.packageName)
                    )
                )
            },
            title = stringResource(R.string.location_permission_explanation_last)
        )
    }

    LazyColumn(
        modifier = modifier
    ) {
        item {
            if (errorText == null && userWeather != null) {
                WeatherWidget(
                    temperature = userWeather.temperature.toInt(),
                    minTemperature = userWeather.minTemperature.toInt(),
                    maxTemperature = userWeather.maxTemperature.toInt(),
                    feelingTemperature = userWeather.temperatureFeelsLike.toInt(),
                    weatherCondition = userWeather.weatherCondition,
                    city = userWeather.currentCity,
                    icon = userWeather.icon,
                    modifier = Modifier
                        .height(LifestyleHubTheme.sizes.weatherCardHeight)
                        .fillMaxWidth()
                        .padding(LifestyleHubTheme.paddings.medium)
                )
            } else {
                ErrorWeatherWidget(
                    onClick = {
                        if (ContextCompat.checkSelfPermission(
                                context,
                                Manifest.permission.ACCESS_COARSE_LOCATION
                            ) == PackageManager.PERMISSION_GRANTED
                        ) {
                            onAction(MainScreenAction.OnLocationPermissionGranted)
                        } else if (ActivityCompat.shouldShowRequestPermissionRationale(
                                context as Activity,
                                Manifest.permission.ACCESS_COARSE_LOCATION
                            )
                        ) {
                            isLocationPermissionDialogVisible = true
                        } else if (isRationaleShowLocationPermissionDialog) {
                            isLocationPermissionLastDialogVisible = true
                        } else {
                            permissionRequestLauncher.launch(Manifest.permission.ACCESS_COARSE_LOCATION)
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(LifestyleHubTheme.sizes.weatherCardHeight)
                        .padding(LifestyleHubTheme.paddings.medium),
                    title = errorText!!.asString()
                )
            }
        }
    }
}

@Composable
private fun LocationPermissionExplanationDialog(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    title: String,
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            Button(onClick = onConfirm) {
                Text(text = stringResource(R.string.confirm))
            }
        },
        dismissButton = {
            OutlinedButton(onClick = onDismiss) {
                Text(text = stringResource(R.string.dismiss))
            }
        },
        title = {
            Text(text = title)
        },
    )
}