package io.github.robertoaraujo.configuration;

import io.github.robertoaraujo.configuration.factory.DbConfig;
import io.github.robertoaraujo.configuration.factory.H2DbConfig;
import io.github.robertoaraujo.configuration.factory.PostgresDbConfig;
import io.github.robertoaraujo.constants.DbType;
import org.springframework.context.annotation.Configuration;

import static io.github.robertoaraujo.constants.DbType.POSTGRES;

@Configuration
public class DbConfiguration {

    protected DbType dbType = POSTGRES;

    public static DbConfig createDbConfig(String dbType) {
        if (dbType.equals(POSTGRES.name())) {
            return new PostgresDbConfig();
        } else {
            return new H2DbConfig();
        }
    }
}
