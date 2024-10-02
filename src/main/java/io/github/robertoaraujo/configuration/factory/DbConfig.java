package io.github.robertoaraujo.configuration.factory;

import javax.sql.DataSource;

public interface DbConfig {

    DataSource dataSource();

}
