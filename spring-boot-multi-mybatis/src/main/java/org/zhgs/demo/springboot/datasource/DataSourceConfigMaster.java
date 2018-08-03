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
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages="org.zhgs.demo.springboot.mapper.master",sqlSessionTemplateRef = "sqlSessionTemplateMaster")
public class DataSourceConfigMaster {


    // 初始化dataSource
    @ConfigurationProperties(prefix = "spring.datasource.master")
    @Bean("dataSourceMaster")
    @Primary
    @Qualifier("dataSourceMaster")
    public HikariDataSource dataSource(@Qualifier("dataSourcePropertiesMaster") DataSourceProperties properties){
        return properties.initializeDataSourceBuilder().type(HikariDataSource.class).build();

    }

    @ConfigurationProperties(prefix = "spring.datasource.master")
    @Bean("dataSourcePropertiesMaster")
    @Primary
    @Qualifier("dataSourcePropertiesMaster")
    public DataSourceProperties dataSourceProperties(){
        return new DataSourceProperties();

    }

    // mybatis sqlSessionFactory
    @Bean(name = "sqlSessionFactoryMaster")
    @Primary
    @Qualifier("sqlSessionFactoryMaster")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSourceMaster") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean  = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        return  bean.getObject();
    }

    // mybatis sqlSessionTemplate
    @Bean(name = "sqlSessionTemplateMaster")
    @Primary
    @Qualifier("sqlSessionTemplateMaster")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSessionFactoryMaster") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    // mybatis DataSourceTransactionManager
    @Bean(name = "dataSourceTransactionManagerMaster")
    @Primary
    public DataSourceTransactionManager dataSourceTransactionManager(@Qualifier("dataSourceMaster") DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }


}
