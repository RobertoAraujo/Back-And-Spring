package io.github.robertoaraujo.configuration;

import io.github.robertoaraujo.configuration.factory.H2DbConfig;
import io.github.robertoaraujo.configuration.factory.PostgresDbConfig;
import io.github.robertoaraujo.constants.DbType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DbConfiguration {

    private final DbType dbType;

    public DbConfiguration(@Value("${db.type}") String dbTypeStr) {
        this.dbType = DbType.valueOf(dbTypeStr.toUpperCase());
    }

    @Bean
    public DataSource dataSource() {
        switch (dbType) {
            case H2:
                return new H2DbConfig().dataSource();
            case POSTGRES:
                return new PostgresDbConfig().dataSource();
            default:
                throw new IllegalArgumentException("Tipo de banco de dados não suportado: " + dbType);
        }
    }

}

