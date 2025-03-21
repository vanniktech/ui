import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi

plugins {
  id("org.jetbrains.dokka")
  id("org.jetbrains.kotlin.multiplatform")
  id("org.jetbrains.kotlin.native.cocoapods")
  id("com.android.library")
  id("org.jetbrains.kotlin.plugin.parcelize")
  id("org.jetbrains.kotlin.plugin.serialization")
  id("me.tylerbwong.gradle.metalava")
  id("com.vanniktech.maven.publish")
  id("app.cash.licensee")
}

licensee {
  allow("Apache-2.0")
}

metalava {
  filename.set("api/current.txt")
}

kotlin {
  @OptIn(ExperimentalKotlinGradlePluginApi::class)
  compilerOptions {
    freeCompilerArgs.addAll(
      "-P",
      "plugin:org.jetbrains.kotlin.parcelize:additionalAnnotation=com.vanniktech.ui.UiParcelize",
    )
  }

  applyDefaultHierarchyTemplate()

  androidTarget {
    publishLibraryVariants("release")
  }
  jvm()
  jvmToolchain(11)
  iosX64()
  iosArm64()
  iosSimulatorArm64()

  targets.withType<org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget> {
    compilations["main"].kotlinOptions.freeCompilerArgs += "-Xexport-kdoc"
  }

  sourceSets {
    val commonMain by getting {
      dependencies {
        api(libs.kotlinx.serialization.json)
      }
    }

    val commonTest by getting {
      dependencies {
        implementation(libs.kotlin.test.common)
        implementation(libs.kotlin.test.annotations.common)
      }
    }

    val androidMain by getting {
      dependencies {
        api(libs.androidx.appcompat)
        api(libs.androidx.swiperefreshlayout)
        api(libs.androidx.webkit)
        api(libs.flexbox)
        api(libs.material)
      }
    }

    val androidUnitTest by getting {
      dependencies {
        implementation(libs.kotlin.test.junit)
      }
    }

    val androidInstrumentedTest by getting {
      dependencies {
        implementation(libs.androidx.test.junit)
        implementation(libs.androidx.test.rules)
        implementation(libs.androidx.test.runner)
      }
    }

    val jvmMain by getting {
      dependencies {
      }
    }

    val jvmTest by getting {
      dependencies {
        implementation(libs.kotlin.test.junit)
      }
    }
  }

  cocoapods {
    summary = "Kotlin Multiplatform UI goodies"
    homepage = "https://github.com/vanniktech/ui"
    license = "MIT"
    name = "ui"
    authors = "Niklas Baudy"
    version = project.property("VERSION_NAME").toString()

    framework {
      isStatic = true
    }
  }
}

android {
  namespace = "com.vanniktech.ui"

  compileSdk = libs.versions.compileSdk.get().toInt()

  defaultConfig {
    minSdk = libs.versions.minSdk.get().toInt()
    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }

  buildFeatures {
    viewBinding = true
  }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
  }

  resourcePrefix = "ui_"
}
