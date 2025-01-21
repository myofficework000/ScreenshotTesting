package com.code4galaxy.screenshottesting.ui

import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import com.android.ide.common.rendering.api.SessionParams
import com.code4galaxy.screenshottesting.WelcomeScreen
import com.code4galaxy.screenshottesting.ui.theme.ScreenshotTestingTheme
import org.junit.Rule
import org.junit.Test

class WelcomeScreenScreenshotTest {
    @get:Rule
    val paparazzi = Paparazzi(
        deviceConfig = DeviceConfig.PIXEL_5,
        renderingMode = SessionParams.RenderingMode.NORMAL,
        showSystemUi = false,
        maxPercentDifference = 1.0,
    )

    @Test
    fun launchWelcomeScreen_lightTheme() {
        paparazzi.snapshot {
            ScreenshotTestingTheme(darkTheme = false) {
                WelcomeScreen(
                    onSignInSignUp = {},
                    onSignInAsGuest = {}
                )
            }
        }
    }
}