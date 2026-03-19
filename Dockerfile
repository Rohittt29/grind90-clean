FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY . .

RUN chmod +x mvnw && ./mvnw clean install -DskipTests

RUN chmod +x mvnw && ./mvnw clean install -DskipTests -Dspring.profiles.active=prod

EXPOSE 8080

CMD ["java", "-jar", "target/*.jar"]