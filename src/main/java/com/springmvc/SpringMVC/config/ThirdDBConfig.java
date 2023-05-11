package com.springmvc.SpringMVC.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "thirdEntityManagerFactory",
        transactionManagerRef = "thirdTransactionManager",
        basePackages = {"com.springmvc.SpringMVC.repository.thirdDB"})
public class ThirdDBConfig {

    @Bean(name = "thirdProps")
    @ConfigurationProperties("spring.thirddatasource")
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }


    @Bean(name = "thirdDatasource")
    public DataSource datasource(@Qualifier("thirdProps") DataSourceProperties properties) {
        return properties.initializeDataSourceBuilder().build();
    }


    @Bean(name = "thirdEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean
            (EntityManagerFactoryBuilder builder,
             @Qualifier("thirdDatasource") DataSource dataSource) {
        return builder.dataSource(dataSource)
                .packages("com.springmvc.SpringMVC.model.thirdDB")
                .persistenceUnit("orclpdb33").build();
    }


    @Bean(name = "thirdTransactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("thirdEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
