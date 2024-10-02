package io.github.robertoaraujo.configuration.factory;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

public class PostgresDbConfig implements DbConfig {

    @Override
    public DataSource dataSource() {
        System.out.println("Configurando o banco PostgreSQL PRODUÇÃO...");
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/apis");
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres");

        return dataSource;
    }

}
