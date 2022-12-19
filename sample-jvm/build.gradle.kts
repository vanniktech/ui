plugins {
  application
  id("org.jetbrains.kotlin.jvm")
}

dependencies {
  implementation(project(":ui"))
}

dependencies {
  testImplementation(libs.kotlin.test.junit)
}

application {
  mainClass.set("com.vanniktech.ui.sample.jvm.UiJvmKt")
}
