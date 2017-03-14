package com.edi.learn.axon.command.aggregates;

import com.edi.learn.axon.command.commands.CreateAccountCommand;
import com.edi.learn.axon.command.commands.WithdrawMoneyCommand;
import com.edi.learn.axon.common.domain.AccountId;
import com.edi.learn.axon.common.events.AccountCreatedEvent;
import com.edi.learn.axon.common.events.MoneyWithdrawnEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.spring.stereotype.Aggregate;
import org.slf4j.Logger;

import java.math.BigDecimal;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by Edison Xu on 2017/3/7.
 */
@Aggregate
public class BankAccount {

    private static final Logger LOGGER = getLogger(BankAccount.class);

    @AggregateIdentifier
    private AccountId accountId;
    private String accountName;
    private BigDecimal balance;

    public BankAccount() {
    }

    @CommandHandler
    public BankAccount(CreateAccountCommand command){
        LOGGER.debug("Construct a new BankAccount");
        apply(new AccountCreatedEvent(command.getAccountId(), command.getAccountName(), command.getAmount()));
    }

    @CommandHandler
    public void handle(WithdrawMoneyCommand command){
        apply(new MoneyWithdrawnEvent(command.getAccountId(), command.getAmount()));
    }

    @EventHandler
    public void on(AccountCreatedEvent event){
        this.accountId = event.getAccountId();
        this.accountName = event.getAccountName();
        this.balance = new BigDecimal(event.getAmount());
        LOGGER.info("Account {} is created with balance {}", accountId, this.balance);
    }

    @EventHandler
    public void on(MoneyWithdrawnEvent event){
        BigDecimal result = this.balance.subtract(new BigDecimal(event.getAmount()));
        if(result.compareTo(BigDecimal.ZERO)<0)
            LOGGER.error("Cannot withdraw more money than the balance!");
        else {
            this.balance = result;
            LOGGER.info("Withdraw {} from account {}, balance result: {}", event.getAmount(), accountId, balance);
        }
    }
}
