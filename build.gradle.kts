import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.spotless)
}

kotlin {
    applyDefaultHierarchyTemplate()

    jvm()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.uuid)
                api(libs.kotlinx.coroutines.core)
            }
        }
    }

    targets.withType<KotlinNativeTarget>().configureEach {
        binaries.framework {
            isStatic = true
            baseName = "RepositoryKt"
        }
    }
}

spotless {
    kotlin {
        target("**/*.kt")
        targetExclude("${layout.buildDirectory}/**/*.kt")
        ktlint(libs.versions.ktlint.get())
            .editorConfigOverride(
                mapOf("ktlint_standard_filename" to "disabled"),
            )
    }
    kotlinGradle {
        target("**/*.kts")
        targetExclude("${layout.buildDirectory}/**/*.kts")
        ktlint(libs.versions.ktlint.get())
    }
}
