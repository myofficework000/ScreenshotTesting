# Screenshot Testing with Jetpack Compose

This repository demonstrates how to implement screenshot testing for Jetpack Compose UI components using [Paparazzi](https://cashapp.github.io/paparazzi/). Screenshot testing ensures visual consistency by capturing and comparing UI screenshots.


## Getting Started

Follow these steps to set up screenshot testing in your Jetpack Compose project.

### Prerequisites

- **Android Studio**
- **Kotlin**
- **Jetpack Compose**
- **JUnit**

### Dependencies

Add the following dependencies to your `build.gradle` file:

```kotlin
dependencies {
    // Paparazzi library
    testImplementation("app.cash.paparazzi:paparazzi:1.2.0")

    // Robolectric for resource resolution
    testImplementation("org.robolectric:robolectric:4.11.1")

    // Compose UI dependencies for testing
    implementation("androidx.compose.ui:ui:1.5.1")
    implementation("androidx.compose.material3:material3:1.2.1")
}
```

## Setting Up Paparazzi

Paparazzi is a tool for rendering Compose components in unit tests to produce screenshots. It simulates an Android environment, allowing for fast, consistent results without requiring an emulator.

### Add Test Class

Create a test class in the `src/test` folder:

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

### Configuring Paparazzi

Paparazzi can emulate different devices and configurations. Customize it as needed:

```kotlin
@get:Rule
val paparazzi = Paparazzi(
    deviceConfig = Paparazzi.DeviceConfig.PIXEL_5,
    renderingMode = Paparazzi.RenderingMode.NORMAL
)
```

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

1. Fork this repository.
2. Create a new branch for your feature.
3. Submit a pull request with a clear description of the changes.


## References

- [Screenshot Testing with Compose (Medium Article)](https://medium.com/@domen.lanisnik/screenshot-testing-with-compose-9a84bd28b6fb)
- [Paparazzi Documentation](https://cashapp.github.io/paparazzi/)


## License

This project is licensed under the MIT License. See the LICENSE file for details.

Happy Testing! 🎉
