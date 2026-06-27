import java.util.Base64

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose")
}

android {
    namespace = "com.aistudio.zenmeditation.app"
    compileSdk = 35

    signingConfigs {
        create("release") {
            val storeFileProp = project.findProperty("android.injected.signing.store.file")?.toString()
            if (storeFileProp != null) {
                storeFile = file(storeFileProp)
                storePassword = project.findProperty("android.injected.signing.store.password")?.toString()
                keyAlias = project.findProperty("android.injected.signing.key.alias")?.toString()
                keyPassword = project.findProperty("android.injected.signing.key.password")?.toString()
            } else {
                val embeddedBase64 = ""
                val embeddedAlias = "releasekey"
                val embeddedStorePwd = "android"
                val embeddedKeyPwd = "android"
                if (embeddedBase64.isNotEmpty() && !embeddedBase64.startsWith("{{")) {
                    try {
                        val keystoreFile = file("${rootDir}/release-embedded.keystore")
                        if (!keystoreFile.exists()) {
                            val bytes = Base64.getDecoder().decode(embeddedBase64)
                            keystoreFile.writeBytes(bytes)
                        }
                        storeFile = keystoreFile
                        storePassword = if (embeddedStorePwd.startsWith("{{")) "android" else embeddedStorePwd
                        keyAlias = if (embeddedAlias.startsWith("{{")) "releasekey" else embeddedAlias
                        keyPassword = if (embeddedKeyPwd.startsWith("{{")) "android" else embeddedKeyPwd
                    } catch (e: Exception) {
                        System.err.println("Failed to decode embedded keystore: ${e.message}")
                    }
                }
            }
        }
    }

    defaultConfig {
        applicationId = "com.aistudio.zenmeditation.app"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            val hasSigning = project.hasProperty("android.injected.signing.store.file")
            if (hasSigning) {
                signingConfig = signingConfigs.getByName("release")
            }
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.6")
    implementation("androidx.activity:activity-compose:1.9.3")
    implementation(platform("androidx.compose:compose-bom:2024.10.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.6")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2024.10.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}