import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.net.URI

plugins {
    kotlin("jvm") version "1.6.10"
}

allprojects {
    apply {
        plugin("org.jetbrains.kotlin.jvm")
    }

    group = "es.magonxesp"
    version = "1.0-SNAPSHOT"

    repositories {
        mavenCentral()
        maven {
            url = URI("https://jitpack.io")
        }
    }

    dependencies {
        testImplementation(kotlin("test"))
        testImplementation("io.mockk:mockk:1.12.2")
        testImplementation("io.github.serpro69:kotlin-faker:1.10.0")
    }

    tasks.test {
        useJUnitPlatform()
    }

    tasks.withType<KotlinCompile>() {
        kotlinOptions.jvmTarget = "13"
    }


}
