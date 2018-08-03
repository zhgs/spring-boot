package org.zhgs.demo.springboot.datasource;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    /*
    @Bean(name = "appDataSource")
    @ConfigurationProperties(prefix = "app.datasource")
    @Primary // 主加载顺序优先，默认
    @Qualifier("appDataSource") // bean别名
    public HikariDataSource dataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }
    */

    @Bean(name = "appJdbcTemplate")
    @Primary
    public JdbcTemplate jdbcTemplate(@Qualifier("appDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);

    }

    @Bean(name = "appDataSource")
    @ConfigurationProperties(prefix = "app.datasource")
    @Primary // 主加载顺序优先，默认
    @Qualifier("appDataSource") // bean别名
    public HikariDataSource dataSource(DataSourceProperties properties) {
        return properties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    @Bean
    @Primary
    @ConfigurationProperties("app.datasource")
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }


}
