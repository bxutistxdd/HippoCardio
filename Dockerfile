# ===============================
# Solo necesitamos Java 24 para correr la app
# ===============================
FROM eclipse-temurin:24-jdk-alpine

WORKDIR /app

# Copiamos el jar ya compilado
COPY target/hippocardioo-0.0.1-SNAPSHOT.jar app.jar

# Puerto de referencia (Render asigna PORT dinámico)
EXPOSE 8081

# Arranque de la app
ENTRYPOINT ["java","-jar","app.jar"]
