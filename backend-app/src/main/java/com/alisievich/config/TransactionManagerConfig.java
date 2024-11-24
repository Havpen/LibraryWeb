package com.alisievich.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.stereotype.Component;

@Component
public class TransactionManagerConfig {

    @Bean(name = "transactionManager")
    @Primary
    public JpaTransactionManager jpaTransactionManager(EntityManagerFactory em) {
        return new JpaTransactionManager(em);
    }
}
