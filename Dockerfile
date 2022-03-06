FROM openjdk:14-buster

COPY . .

RUN ./gradlew build -x test

ENTRYPOINT ["./gradlew"]
