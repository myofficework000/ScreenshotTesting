package com.code4galaxy.screenshottesting.ui

import ProfileScreen
import androidx.compose.runtime.Composable
import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class ProfileScreenTest{
    @get:Rule
    val paparazzi = Paparazzi(
        deviceConfig = DeviceConfig.PIXEL_6_PRO,
        theme = "com.code4galaxy.screenshottesting.ui.theme.ScreenshotTestingTheme"
        // ...see docs for more options
    )

    @Test
    fun `test ProfileScreen when following`() {
        paparazzi.snapshot {
            ProfileScreenTestPreview(isFollowing = true)
        }
    }

    @Test
    fun `test ProfileScreen when not following`() {
        paparazzi.snapshot {
            ProfileScreenTestPreview(isFollowing = false)
        }
    }

    @Composable
    private fun ProfileScreenTestPreview(isFollowing: Boolean) {
        ProfileScreen(
            name = "Saiteja",
            isFollowing = isFollowing,
            onFollowClick = {}
        )
    }
}