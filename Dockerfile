# Usamos Java 24
FROM eclipse-temurin:24-jdk-alpine

# Carpeta de trabajo dentro del contenedor
WORKDIR /app

# Copiamos el jar generado por Maven desde target/
COPY target/hippocardioo-0.0.1-SNAPSHOT.jar app.jar

# Exponemos un puerto de referencia (local 8081)
EXPOSE 8081

# Comando para arrancar la app
ENTRYPOINT ["java", "-jar", "app.jar"]
