package com.JavaTech.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import jakarta.activation.DataSource;

@Configuration
public class DataSourceConfig {

	/*@Bean
    public DataSource dataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl("jdbc:mysql://localhost:3306/radisapp");
        hikariConfig.setUsername("root");
        hikariConfig.setPassword("root12345");
        hikariConfig.setDriverClassName("com.mysql.cj.jdbc.Driver");

        // HikariCP settings
        hikariConfig.setMaximumPoolSize(10);
        hikariConfig.setMinimumIdle(5);
        hikariConfig.setIdleTimeout(30000);
        hikariConfig.setMaxLifetime(1800000);
        hikariConfig.setConnectionTimeout(30000);

        return (DataSource) new HikariDataSource(hikariConfig);
    }*/
}
