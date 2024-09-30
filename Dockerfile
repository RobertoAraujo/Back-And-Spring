# Usa uma imagem oficial do Maven para compilar o projeto
FROM maven:3.8.6-openjdk-17-slim AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Usa uma imagem leve do OpenJDK para rodar a aplicação
FROM openjdk:17-jdk-slim
WORKDIR /app

# Copia o jar gerado no estágio anterior
COPY --from=build /app/target/*.jar app.jar

# Expõe a porta que o Spring Boot usa por padrão
EXPOSE 8080

# Variáveis de ambiente para configurar o banco de dados
ENV SPRING_PROFILES_ACTIVE=prod
# Para H2
ENV SPRING_DATASOURCE_URL=jdbc:h2:mem:testdb
ENV SPRING_DATASOURCE_DRIVER-CLASS-NAME=org.h2.Driver

# Para PostgreSQL (altere se necessário)
# ENV SPRING_DATASOURCE_URL=jdbc:postgresql://postgres:5432/dbname
# ENV SPRING_DATASOURCE_USERNAME=postgres
# ENV SPRING_DATASOURCE_PASSWORD=postgres
# ENV SPRING_DATASOURCE_DRIVER-CLASS-NAME=org.postgresql.Driver

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
