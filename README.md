# Screenshot Testing with Jetpack Compose

![Language](https://img.shields.io/github/languages/top/cortinico/kotlin-android-template?color=blue&logo=kotlin)
![GitHub repo size](https://img.shields.io/github/repo-size/myofficework000/ScreenshotTesting)  
![GitHub stars](https://img.shields.io/github/stars/myofficework000/ScreenshotTesting?style=social)  
![Contributions welcome](https://img.shields.io/badge/contributions-welcome-brightgreen.svg?style=flat)

This repository demonstrates how to implement **screenshot testing** for Jetpack Compose UI using [**Paparazzi**](https://cashapp.github.io/paparazzi/).  
Screenshot testing helps you detect **visual regressions** by comparing UI screenshots captured during tests. It is particularly useful for catching unintended design changes when working in teams or maintaining design consistency across releases.

---

## Why Screenshot Testing?

Unlike traditional unit tests, screenshot tests focus on the **UI appearance**, ensuring that the layout, spacing, colors, and typography remain visually consistent. This is essential for:

- Catching UI bugs before code reaches production
- Verifying pixel-perfect implementations
- Running fast, emulator-free UI checks in CI

---

## 🧰 Prerequisites

Before you start, make sure your project uses:

- ✅ Android Studio
- ✅ Kotlin
- ✅ Jetpack Compose
- ✅ JUnit (for running tests)

---

## 📦 Adding Dependencies

Update your `build.gradle` (Module: app) with the necessary test dependencies:

```kotlin
dependencies {
    // Paparazzi core library
    testImplementation("app.cash.paparazzi:paparazzi:1.2.0")

    // Robolectric required for internal Android resource resolution
    testImplementation("org.robolectric:robolectric:4.11.1")

    // Jetpack Compose dependencies
    implementation("androidx.compose.ui:ui:1.5.1")
    implementation("androidx.compose.material3:material3:1.2.1")
}
```

## Setting Up Paparazzi
Paparazzi renders Compose UI components in unit tests without requiring a device or emulator.
It works by simulating an Android environment using Robolectric and capturing screenshots directly.

### 🧪 Creating a Screenshot Test
Create a test class in the src/test/java/... directory:

```kotlin
import app.cash.paparazzi.Paparazzi
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import org.junit.Rule
import org.junit.Test

class WelcomeScreenScreenshotTest {

    @get:Rule
    val paparazzi = Paparazzi()

    @Test
    fun launchWelcomeScreen_lightTheme() {
        paparazzi.snapshot {
            WelcomeScreen()
        }
    }
}

@Composable
fun WelcomeScreen() {
    Text(text = "Welcome to Screenshot Testing!")
}
```

This will render the WelcomeScreen() composable and save a screenshot for comparison.

### 📲 Customizing the Device Configuration
You can simulate different devices and screen sizes using deviceConfig:

Paparazzi can emulate different devices and configurations. Customize it as needed:

```kotlin
@get:Rule
val paparazzi = Paparazzi(
    deviceConfig = Paparazzi.DeviceConfig.PIXEL_5,
    renderingMode = Paparazzi.RenderingMode.NORMAL
)
```

Available rendering modes:
- NORMAL: Default rendering
- V_SCROLL: For vertically scrollable components
- FULL: Renders full screen height

### Running Tests

Run your tests using:

```bash
./gradlew test
```

Test results and screenshots will be generated in the `build/reports/tests/testDebugUnitTest` folder.

## Debugging Common Issues

### `Resources$NotFoundException`

- **Cause**: Missing or inaccessible resources.
- **Solution**:
  - Move tests to the `androidTest` folder to access Android resources.
  - Mock any required resources in your test.

### Theme Issues

- Use default colors or mock themes for Paparazzi to resolve dynamic resources:

```kotlin
MaterialTheme(
    colorScheme = lightColorScheme(
        primary = Color(0xFF6200EE),
        secondary = Color(0xFF03DAC5)
    )
) {
    WelcomeScreen()
}
```

## Contribution Guidelines

1. Fork repository.
2. Create a new branch for your feature.
3. Submit a pull request with a clear description of the changes.


## References

- [Screenshot Testing with Compose (Medium Article)](https://medium.com/@domen.lanisnik/screenshot-testing-with-compose-9a84bd28b6fb)
- [Paparazzi Documentation](https://cashapp.github.io/paparazzi/)


## License

This project is licensed under the MIT License. See the LICENSE file for details.

Happy Testing! 🎉
