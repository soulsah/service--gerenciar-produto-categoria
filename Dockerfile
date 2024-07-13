# Etapa de construção com Maven
FROM maven:3.8.5-openjdk-17 AS MAVEN_BUILD
WORKDIR /app
COPY ./ ./
RUN mvn clean package -DskipTests

# Etapa de construção do jar com Layertools
FROM openjdk:17-oracle AS builder
WORKDIR /app
COPY --from=MAVEN_BUILD /app/target/*.jar application.jar
RUN java -Djarmode=layertools -jar application.jar extract

# Etapa final para rodar a aplicação
FROM openjdk:17-oracle
WORKDIR /app
EXPOSE 8082
COPY --from=builder /app/dependencies/ ./
COPY --from=builder /app/snapshot-dependencies/ ./
COPY --from=builder /app/spring-boot-loader/ ./
COPY --from=builder /app/application/ ./
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]
