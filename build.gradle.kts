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
        implementation("org.jetbrains.kotlin:kotlin-reflect:1.6.10")
        implementation("org.hibernate:hibernate-core:5.6.5.Final")
        implementation("javax.xml.bind:jaxb-api:2.3.1")
        implementation("org.glassfish.jaxb:jaxb-runtime:2.3.1")
        implementation("mysql:mysql-connector-java:8.0.28")

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

subprojects {
    tasks.withType<Jar> {

    }
}
