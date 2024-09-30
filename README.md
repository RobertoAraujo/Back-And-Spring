# Projeto back and com Spring Boot Api com Swagger

## Descrição
Este projeto é uma aplicação Spring Boot desenvolvida para ter um modelo de api com swagger.

## Funcionalidades
- **CRUD de Entidades**: Implementação de operações de Create, Read, Update e Delete para gerenciar entidades.
- **Autenticação e Autorização**: Utilização de Spring Security para proteger endpoints e controlar acesso baseado em roles.
- **Validação de Dados**: Validação de dados de entrada usando anotações de validação do Spring.
- **Documentação da API**: Documentação dos endpoints utilizando Swagger/OpenAPI.

## Tecnologias Utilizadas
- **Spring Boot**: Framework principal para desenvolvimento java.
- **Lombok**: Ferramenta para reduzir a verbosidade do código em Java.
- **Spring Data JPA**: Persistência e acesso a dados.
- **Spring Security**: Segurança e controle de acesso.
- **Hibernate**: Implementação.
- **MySQL**: Banco de dados relacional.
- **Swagger**: Ferramenta para documentação da API.
- **Maven**: Gerenciamento de dependências e build da aplicação.

## Pré-requisitos
- **JDK 11 ou superior**
- **Maven**
- **MySQL**

## Configuração do Banco de Dados
Certifique-se de que o MySQL está instalado e em execução. Crie um banco de dados para o projeto:

##sql
- **CREATE DATABASE db;**

## application.properties
- ** Driver e Autenticação**
- **spring.datasource.url=jdbc:mysql://localhost:3306/db?useSSL=false&serverTimezone=UTC**
- **spring.datasource.username=root**
- **spring.datasource.password=sua-senha**

- **spring.jpa.hibernate.ddl-auto=update**
- **spring.jpa.show-sql=true**
- **spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5Dialect**

A aplicação estará disponível em [http://localhost:8080](http://localhost:8080).

### Endpoints Principais
- **GET /api/entidades**: Lista todas as entidades.
- **GET /api/entidades/{id}**: Obtém uma entidade pelo ID.
- **POST /api/entidades**: Cria uma nova entidade.
- **PUT /api/entidades/{id}**: Atualiza uma entidade existente.
- **DELETE /api/entidades/{id}**: Deleta uma entidade pelo ID.

### Segurança
- **Autenticação**: Os usuários devem se autenticar para acessar endpoints protegidos.
- **Roles**: Controle de acesso baseado em roles de usuário (e.g., ADMIN, USER).

### Documentação da API
A documentação da API pode ser acessada via Swagger em [http://localhost:8080/api/swagger-ui.html](http://localhost:8080/api/swagger-ui.html).

### Contribuição
Contribuições são bem-vindas! Siga os passos abaixo para contribuir:

1. Fork o repositório.
2. Crie uma branch para sua feature (`git checkout -b feature/nova-feature`).
3. Commit suas mudanças (`git commit -am 'Adiciona nova feature'`).
4. Push para a branch (`git push origin feature/nova-feature`).
5. Abra um Pull Request.

### Contato
Para dúvidas ou sugestões, entre em contato pelo email: endriosrobert@gmail.com.
