plugins {
    id("com.android.application") version "8.0.2" apply false
    kotlin("android") version "1.9.10" apply false
}

tasks.register<Delete>("clean") {
    delete(rootProject.layout.buildDirectory)
}
