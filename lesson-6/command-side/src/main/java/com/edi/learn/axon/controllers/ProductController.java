package com.edi.learn.axon.controllers;

import com.alibaba.fastjson.JSONObject;
import com.edi.learn.axon.commands.ChangeStockCommand;
import com.edi.learn.axon.commands.CreateProductCommand;
import com.edi.learn.axon.commands.DecreaseStockCommand;
import com.edi.learn.axon.commands.IncreaseStockCommand;
import org.axonframework.commandhandling.CommandExecutionException;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.commandhandling.model.ConcurrencyException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by Edison Xu on 2017/3/7.
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    private static final Logger LOGGER = getLogger(ProductController.class);

    @Autowired
    private CommandGateway commandGateway;

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public void create(@PathVariable(value = "id") String id,
                       @RequestParam(value = "name", required = true) String name,
                       @RequestParam(value = "price", required = true) long price,
                       @RequestParam(value = "stock",required = true) int stock,
                       HttpServletResponse response) {

        LOGGER.debug("Adding Product [{}] '{}' {}x{}", id, name, price, stock);

        try {
            // multiply 100 on the price to avoid float number
            CreateProductCommand command = new CreateProductCommand(id,name,price*100,stock);
            commandGateway.sendAndWait(command);
            response.setStatus(HttpServletResponse.SC_CREATED);// Set up the 201 CREATED response
            return;
        } catch (CommandExecutionException cex) {
            LOGGER.warn("Add Command FAILED with Message: {}", cex.getMessage());
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            if (null != cex.getCause()) {
                LOGGER.warn("Caused by: {} {}", cex.getCause().getClass().getName(), cex.getCause().getMessage());
                if (cex.getCause() instanceof ConcurrencyException) {
                    LOGGER.warn("A duplicate product with the same ID [{}] already exists.", id);
                    response.setStatus(HttpServletResponse.SC_CONFLICT);
                }
            }
        }
    }

    @PutMapping("/{id}")
    public void change(@PathVariable(value = "id") String id,
                       @RequestBody(required = true) JSONObject input,
                       HttpServletResponse response){
        boolean isIncrement = input.getBooleanValue("incremental");
        int number = input.getIntValue("number");
        ChangeStockCommand command = isIncrement? new IncreaseStockCommand(id, number) : new DecreaseStockCommand(id, number);

        try {
            // multiply 100 on the price to avoid float number
            //commandGateway.send(command, LoggingCallback.INSTANCE);
            commandGateway.sendAndWait(command);
            response.setStatus(HttpServletResponse.SC_OK);// Set up the 201 CREATED response
            return;
        } catch (CommandExecutionException cex) {
            LOGGER.warn("Add Command FAILED with Message: {}", cex.getMessage());
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            if (null != cex.getCause()) {
                LOGGER.warn("Caused by: {} {}", cex.getCause().getClass().getName(), cex.getCause().getMessage());
                if (cex.getCause() instanceof ConcurrencyException) {
                    LOGGER.warn("Concurrent issue happens for product {}", id);
                    response.setStatus(HttpServletResponse.SC_CONFLICT);
                }
            }
        } catch (Exception e) {
            // should not happen
            LOGGER.error("Unexpected exception is thrown", e);
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
