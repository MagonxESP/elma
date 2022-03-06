plugins {
    application
}

dependencies {
    implementation(project(":elma"))
}

application {
    mainClass.set("es.magonxesp.elma.api.MainKt")
}
