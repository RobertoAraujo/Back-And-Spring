package io.github.robertoaraujo.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application-h2.properties")
public class DBonfiguration {

    @Value("${spring.datasource.url}")
    private String datasourceUrl;

    @Value("${spring.datasource.username}")
    private String datasourceUsername;

    @Value("${spring.datasource.password}")
    private String datasourcePassword;

    @Bean
    public String customPropertyBean() {
        if (datasourcePassword == null) {
            return "Datasource URL: " + datasourceUrl + ", Username: " + datasourceUsername;
        } else {
            return "Datasource URL: " + datasourceUrl + ", Username: " + datasourceUsername + ", Password: " + datasourcePassword;
        }
    }

}
