UI
==

Kotlin Multiplatform UI goodies. Right now concentrating mostly on Colors & Theming.

# Usage

From Kotlin Multiplatform:

```groovy
kotlin {
  sourceSets {
    val commonMain by getting {
      dependencies {
        implementation("com.vanniktech:ui:0.8.0")
      }
    }
  }
}
```

From Android / JVM Multiplatform:

```groovy
dependencies {
  implementation("com.vanniktech:ui:0.8.0")
}
```

From iOS:

```ruby
pod 'ui', :git => 'https://github.com/vanniktech/ui', :tag => "0.8.0"
```

# API

Mostly undocumented, best to dig through the source code. API is also subject to change.

# License

Copyright (C) 2022 - Niklas Baudy

Licensed under the Apache License, Version 2.0
