// build.gradle.kts (raíz del proyecto)
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        // Gradle plugin de Android y Kotlin, si necesitas usarlo aquí
        classpath("com.android.tools.build:gradle:8.3.0")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.0")
    }
}

// Si no necesitas configuraciones adicionales, puedes dejarlo vacío
