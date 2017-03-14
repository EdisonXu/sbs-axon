package com.edi.learn.axon;


import com.edi.learn.axon.command.aggregates.BankAccount;
import com.edi.learn.axon.command.commands.CreateAccountCommand;
import com.edi.learn.axon.command.commands.WithdrawMoneyCommand;
import com.edi.learn.axon.common.domain.AccountId;
import org.axonframework.config.Configuration;
import org.axonframework.config.DefaultConfigurer;
import org.axonframework.eventsourcing.eventstore.inmemory.InMemoryEventStorageEngine;
import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by Edison Xu on 2017/3/7.
 */
public class Application {

    private static final Logger LOGGER = getLogger(Application.class);

    public static void main(String args[]){
        Configuration config = DefaultConfigurer.defaultConfiguration()
                .configureAggregate(BankAccount.class)
                .configureEmbeddedEventStore(c -> new InMemoryEventStorageEngine())
                .buildConfiguration();
        config.start();
        AccountId id = new AccountId();
        config.commandGateway().send(new CreateAccountCommand(id, "MyAccount",1000));
        config.commandGateway().send(new WithdrawMoneyCommand(id, 500));
        config.commandGateway().send(new WithdrawMoneyCommand(id, 500));
        /*config.commandBus().dispatch(asCommandMessage(new CreateAccountCommand(id, "MyAccount", 1000)));
        config.commandBus().dispatch(asCommandMessage(new WithdrawMoneyCommand(id, 500)));*/
    }

}
