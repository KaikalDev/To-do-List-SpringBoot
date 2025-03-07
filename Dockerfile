FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY target/app.jar app.jar

ENV JAVA_OPTS="-Xmx200m -Xms128m -XX:MaxMetaspaceSize=64m -XX:CompressedClassSpaceSize=16m"

CMD ["sh", "-c", "java $JAVA_OPTS -Dspring.profiles.active=prod -jar app.jar"]
