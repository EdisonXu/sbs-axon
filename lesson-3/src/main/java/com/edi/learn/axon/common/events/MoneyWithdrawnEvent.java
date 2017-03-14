package com.edi.learn.axon.common.events;


import com.edi.learn.axon.common.domain.AccountId;

/**
 * Created by Edison Xu on 2017/3/7.
 */
public class MoneyWithdrawnEvent {
    private AccountId accountId;
    private long amount;

    public MoneyWithdrawnEvent(AccountId accountId, long amount) {
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
