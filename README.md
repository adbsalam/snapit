
# SnapIt

[SnapIt ](https://central.sonatype.com/namespace/uk.adbsalam.snapit) auto test generation for [Paparazzi Compose](https://github.com/cashapp/paparazzi)

![Maven Central](https://img.shields.io/badge/maven_central-1.0.2-blue)

## Features

Snapit is a powerful tool designed to automate the generation of Paparazzi tests by simple adding ```@Snapit``` Annotation on ```@Preview``` Functions, significantly reducing the time and resources required for creating snapshot tests. It leverages the ```@Preview``` Composables feature from the codebase, making it easy to implement and utilize. With Snapit, you can streamline your testing process and ensure the quality and reliability of your code.

## Includes
- ```@SnapIt``` Annotation
- ```Snapit Lint``` Lint checks to help implementation
- ```ksp``` to allow test file generation
- ```Paparazzi Testing Utility``` to implement Paparazzi 


## Implementation

Apply [Snapit-Gradle Plugin](https://plugins.gradle.org/plugin/uk.adbsalam.snapit) plugin to your module. [GithubRepo](https://github.com/MuhammadAbdulSalam/snapit-plugin)

In your ```build.gradle.kts``` apply plugin

```kotlin
plugins {
   id("uk.adbsalam.snapit") version "<LATEST-VERSION>"
}
```

OR Use Legacy 
```
buildscript {
  repositories {
    maven {
      url = uri("https://plugins.gradle.org/m2/")
    }
  }
  dependencies {
    classpath("uk.adbsalam.snapit:snapit-plugin:1.0.2")
  }
}

apply(plugin = "uk.adbsalam.snapit")

```


In your ```build.gradle.kts``` set snap test package location to generate tests at.
```kotlin
snapIt {
   testDir("uk/adbsalam/snapit")
}
```

This will allow usage of ```@SnapIt``` in code and create following gradle tasks

Now In Compose files you can make use of annotation ```@SnapIt``` as following

- ```./gradlew :module:SnapItGenerate``` to generate test files
- ```./gradlew :module:SnapItRecord``` to record Paparazzi Snapshot tests
- ```./gradlew :module:SnapItVerify``` to run/verify generated tests




## Usage/Examples

In ```ExampleScreen.kt``` I have following code

```kotlin
@Composable
fun ExampleCompose() {
    Text(text = "Hello World")
}

@Composable
@Preview
@SnapIt
fun ExamplePreview() {
    ExampleCompose()
}

@Composable
@Preview
@SnapIt(
    name = "when in preview, should render correctly",
    preview = true,
    isScreen = true
)
fun ExamplePreview2() {
    ExampleCompose()
}
```
Run Task to genenrate test file

```./gradlew :module:snapitgenerate```

This will generate 2 Files.
- ```ExampleScreenComponentTest.kt``` to generate test for a component with no
  background of device system UI.
```kotlin
 import app.cash.paparazzi.Paparazzi
 import org.junit.Rule
 import org.junit.Test
 import org.junit.runner.RunWith
 import org.junit.runners.JUnit4
 import uk.adbsalam.snapit.testing.captureScreenshot
 import uk.adbsalam.snapit.testing.forComponent

 @RunWith(JUnit4::class)
 class ExampleScreenComponentTest {
   @get:Rule
   val paparazzi: Paparazzi = Paparazzi.forComponent()

   @Test
   fun examplePreviewSnapTest() {
     paparazzi.captureScreenshot {
         ExamplePreview()
     }
   }
 }
```

- ```ExampleScreenScreentTest.kt``` to generate test for a Screen with system UI enabled.
```kotlin 
 import androidx.compose.runtime.CompositionLocalProvider
 import androidx.compose.ui.platform.LocalInspectionMode
 import app.cash.paparazzi.Paparazzi
 import org.junit.Rule
 import org.junit.Test
 import org.junit.runner.RunWith
 import org.junit.runners.JUnit4
 import uk.adbsalam.snapit.testing.captureScreenshot
 import uk.adbsalam.snapit.testing.forScreen

 @RunWith(JUnit4::class)
 class ExampleScreenScreenTest {
   @get:Rule
   val paparazzi: Paparazzi = Paparazzi.forScreen()

   @Test
   fun `when in preview, should render correctly`() {
     paparazzi.captureScreenshot {
         CompositionLocalProvider(LocalInspectionMode provides true) {
             ExamplePreview2()
         }
     }
   }
 }
```

## Record Tests 

Run task to generate snapshots
```
./gradlew :module:snapitrecord
```

This will generate following SnapShots for this example code

### Component
![Component](https://github.com/MuhammadAbdulSalam/snapit/blob/develop/sample/src/test/snapshots/images/uk.adbsalam.snapit.sample_ExampleScreenComponentTest_examplePreviewSnapTest.png)


### Screen
![Screen](https://github.com/MuhammadAbdulSalam/snapit/blob/develop/sample/src/test/snapshots/images/uk.adbsalam.snapit.sample_ExampleScreenScreenTest_when%20in%20preview%2C%20should%20render%20correctly.png)


## Verification
Run task to verify snapshot tests
```
./gradlew :module:snapitverify
```


## Acknowledgements

- Special thanks to [Ben Ezard](https://github.com/Ezard) for help and mentoring.

