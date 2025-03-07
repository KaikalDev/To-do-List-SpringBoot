FROM eclipse-temurin:11-jdk-alpine

WORKDIR /app

# Altere 'app.jar' para o nome correto do arquivo gerado (no seu caso, 'Modulo_40-0.0.1-SNAPSHOT.jar')
COPY target/modulo-40-0.0.1-SNAPSHOT.jar app.jar

# Defina as opções do Java
ENV JAVA_OPTS="-Xmx200m -Xms128m -XX:MaxMetaspaceSize=64m -XX:CompressedClassSpaceSize=16m"

# Comando para rodar o app com o perfil de produção
CMD ["sh", "-c", "java $JAVA_OPTS -Dspring.profiles.active=prod -jar app.jar"]
