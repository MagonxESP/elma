rootProject.name = "elma"

include(":elma")
project(":elma").projectDir = file("src/elma")

include(":elma:backend")
project(":elma:backend").projectDir = file("apps/elma/backend")