# Etapa 1: Construcción (Build)
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
# Copiamos los archivos de configuración y el código fuente
COPY pom.xml .
COPY src ./src
# Compilamos el proyecto omitiendo las pruebas para que sea más rápido
RUN mvn clean package -DskipTests

# Etapa 2: Ejecución (Run)
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
# Copiamos el .jar generado en la etapa anterior
COPY --from=build /app/target/*.jar app.jar
# Exponemos el puerto que usaste en tu application.properties
EXPOSE 8081
# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
LABEL authors="lisan"

