package com.edi.learn.axon.command.commands;

import com.edi.learn.axon.common.domain.AccountId;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

/**
 * Created by Edison Xu on 2017/3/7.
 */
public class WithdrawMoneyCommand {

    @TargetAggregateIdentifier
    private AccountId accountId;
    private long amount;


    public WithdrawMoneyCommand(AccountId accountId, long amount) {
        this.accountId = accountId;
        this.amount = amount;
    }

    public AccountId getAccountId() {
        return accountId;
    }

    public long getAmount() {
        return amount;
    }
}
