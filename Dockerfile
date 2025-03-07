# Usa uma versão oficial do JDK 17
FROM openjdk:17-jdk-slim

# Define um diretório de trabalho
WORKDIR /app

# Copia o JAR gerado pelo build do Maven/Gradle para dentro do container
COPY target/modulo-40-0.0.1-SNAPSHOT.jar app.jar

# Expõe a porta que a aplicação usa
EXPOSE 8080

# Define as opções de memória através da variável de ambiente
ENV JAVA_OPTS="-Xms256m -Xmx512m"

# Usa exec para iniciar o processo de forma mais segura
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
