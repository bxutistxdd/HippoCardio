# ===============================
# Etapa 1: Build del jar con Maven
# ===============================
FROM maven:3.9.2-jdk-24 AS build

WORKDIR /app

# Copiamos los archivos de Maven
COPY pom.xml .
COPY src ./src

# Compilamos el jar (sin tests)
RUN mvn clean package -DskipTests

# ===============================
# Etapa 2: Imagen ligera con Java
# ===============================
FROM eclipse-temurin:24-jdk-alpine

WORKDIR /app

# Copiamos el jar compilado desde la etapa de build
COPY --from=build /app/target/hippocardioo-0.0.1-SNAPSHOT.jar app.jar

# Puerto de referencia (Render usará el PORT dinámico)
EXPOSE 8081

# Arranque de la app
ENTRYPOINT ["java","-jar","app.jar"]
