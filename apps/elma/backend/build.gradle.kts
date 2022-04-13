plugins {
    id("org.springframework.boot") version "2.6.4"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("plugin.spring") version "1.6.10"
    application
}

dependencies {
    implementation(project(":elma"))
    implementation("org.springframework.boot:spring-boot-starter-web:2.6.4")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.13.1")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.springframework.session:spring-session-core:2.6.2")
    implementation("org.springframework:spring-websocket:5.3.18")
    implementation("org.springframework:spring-messaging:5.3.18")

    testImplementation("org.springframework.boot:spring-boot-starter-test:2.6.4")
}

application {
    mainClass.set("es.magonxesp.elma.api.MainKt")
}

tasks.withType<Jar>() {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE

    manifest {
        attributes(
            "Main-Class" to "es.magonxesp.elma.api.MainKt",
            "Class-Path" to configurations.compileClasspath.get().map { it.name }.joinToString(" ")
        )
    }

    from(configurations.compileClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
}
