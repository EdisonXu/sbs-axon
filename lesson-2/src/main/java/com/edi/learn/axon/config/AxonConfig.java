package com.edi.learn.axon.config;

import com.edi.learn.axon.aggregates.BankAccount;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.SimpleCommandBus;
import org.axonframework.commandhandling.model.GenericJpaRepository;
import org.axonframework.commandhandling.model.Repository;
import org.axonframework.common.jpa.ContainerManagedEntityManagerProvider;
import org.axonframework.common.jpa.EntityManagerProvider;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventhandling.SimpleEventBus;
import org.axonframework.messaging.interceptors.TransactionManagingInterceptor;
import org.axonframework.spring.config.EnableAxon;
import org.axonframework.spring.messaging.unitofwork.SpringTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * Created by Edison on 2017/3/9.
 */
@Configuration
@EnableAxon
public class AxonConfig {

    @Autowired
    PlatformTransactionManager transactionManager;

    @Bean
    public CommandBus commandBus() {
        SimpleCommandBus commandBus = new SimpleCommandBus();
        commandBus.registerHandlerInterceptor(transactionManagingInterceptor());
        return commandBus;
    }

    @Bean
    public EventBus eventBus() {
        return new SimpleEventBus();
    }

    @Bean
    public TransactionManagingInterceptor transactionManagingInterceptor(){
        return new TransactionManagingInterceptor(new SpringTransactionManager(transactionManager));
    }

    @Bean
	public EntityManagerProvider entityManagerProvider() {
		return new ContainerManagedEntityManagerProvider();
	}

	@Bean
    public Repository<BankAccount> accountRepository(){
        return new GenericJpaRepository<BankAccount>(entityManagerProvider(),BankAccount.class, eventBus());
    }
}
