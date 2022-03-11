FROM gradle:7.4.1-jdk11-alpine

WORKDIR /build

COPY . .

RUN gradle build -x test

FROM openjdk:14-buster

WORKDIR /app

COPY --from=0 /build ./
