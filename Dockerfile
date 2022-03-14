FROM gradle:7.4.1-jdk11-alpine AS build_cache

WORKDIR /build-cache

RUN mkdir -p src/elma \
    && mkdir -p apps/elma/backend

ADD gradle.properties .
ADD build.gradle.kts .
ADD apps/elma/backend/build.gradle.kts apps/elma/backend/
ADD src/elma/build.gradle.kts src/elma/

RUN gradle clean build -x test --no-daemon

FROM gradle:7.4.1-jdk11-alpine AS build

WORKDIR /build

COPY --from=build_cache /root/.gradle /root/.gradle

COPY . .

RUN gradle build -x test

FROM openjdk:14-buster

WORKDIR /app

COPY --from=build /build ./
