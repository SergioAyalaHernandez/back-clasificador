# Usar una imagen base con Java 21
FROM eclipse-temurin:21-jre

# Establecer el directorio de trabajo
WORKDIR /app

# Copiar el archivo JAR construido por Maven
COPY target/demo-0.0.1-SNAPSHOT.jar app.jar

# Exponer el puerto
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]