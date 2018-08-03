package org.zhgs.demo.springboot.datasource;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages="org.zhgs.demo.springboot.mapper.slave",sqlSessionTemplateRef = "sqlSessionTemplateSlave")
public class DataSourceConfigSlave {


    // 初始化dataSource
    @ConfigurationProperties(prefix = "spring.datasource.slave")
    @Bean("dataSourceSlave")
    public DataSource dataSource(@Qualifier("dataSourcePropertiesSlave") DataSourceProperties properties){
        return properties.initializeDataSourceBuilder().type(HikariDataSource.class).build();

    }

    @ConfigurationProperties(prefix = "spring.datasource.slave")
    @Bean("dataSourcePropertiesSlave")
    public DataSourceProperties dataSourceProperties(){
        return new DataSourceProperties();

    }

    // mybatis sqlSessionFactory
    @Bean(name = "sqlSessionFactorySlave")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSourceSlave") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean  = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        return  bean.getObject();
    }

    // mybatis sqlSessionTemplate
    @Bean(name = "sqlSessionTemplateSlave")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSessionFactorySlave") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    // mybatis DataSourceTransactionManager
    @Bean(name = "dataSourceTransactionManagerSlave")
    public DataSourceTransactionManager dataSourceTransactionManager(@Qualifier("dataSourceSlave") DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }

}
