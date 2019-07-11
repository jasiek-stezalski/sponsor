package com.task.sponsor.config;


import com.task.sponsor.domain.Sponsor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

@Configuration
@EnableTransactionManagement
@Profile("test")
@EnableJpaRepositories(
        entityManagerFactoryRef = "sponsorEntityManager",
        transactionManagerRef = "sponsorTransactionManager",
        basePackages = "com.task.sponsor.repository"
)
public class SponsorH2TestConfig {

    @Primary
    @Bean
    public DataSource dataSource() {
        return DataSourceBuilder
                .create()
                .url("jdbc:h2:mem:sponsor;DB_CLOSE_DELAY=-1")
                .password("sa")
                .username("sa")
                .driverClassName("org.h2.Driver")
                .build();
    }

    @Primary
    @Bean(name = "sponsorEntityManager")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(dataSource())
                .properties(hibernateProperties())
                .packages(Sponsor.class)
                .persistenceUnit("sponsorPU")
                .build();
    }

    @Primary
    @Bean(name = "sponsorTransactionManager")
    public PlatformTransactionManager transactionManager(@Qualifier("sponsorEntityManager") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    private Map<String, Object> hibernateProperties() {

        Resource resource = new ClassPathResource("hibernate.properties");

        try {
            Properties properties = PropertiesLoaderUtils.loadProperties(resource);

            return properties.entrySet().stream()
                    .collect(Collectors.toMap(
                            e -> e.getKey().toString(),
                            Map.Entry::getValue)
                    );
        } catch (IOException e) {
            return new HashMap<>();
        }
    }

}