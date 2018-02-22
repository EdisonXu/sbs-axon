package com.edi.learn.axon.commands;

/**
 * Created by Edison Xu on 2017/4/10.
 */
public class IncreaseStockCommand extends ChangeStockCommand {

    public IncreaseStockCommand() {
    }

    public IncreaseStockCommand(String id, int number) {
        super(id, number);
    }
}
