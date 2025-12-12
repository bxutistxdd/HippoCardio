# Imagen con Java y Maven
FROM maven:3.9.2-eclipse-temurin-24 AS build

WORKDIR /app

# Copiamos los archivos de Maven
COPY pom.xml .
COPY src ./src

# Compilamos el jar
RUN mvn clean package -DskipTests

# Segunda etapa: imagen ligera con Java
FROM eclipse-temurin:24-jdk-alpine

WORKDIR /app

# Copiamos el jar compilado
COPY --from=build /app/target/hippocardioo-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8081

ENTRYPOINT ["java","-jar","app.jar"]
