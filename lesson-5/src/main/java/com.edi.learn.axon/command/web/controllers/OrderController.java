package com.edi.learn.axon.command.web.controllers;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.edi.learn.axon.command.commands.CreateOrderCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by Edison Xu on 2017/3/7.
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    private static final Logger LOGGER = getLogger(OrderController.class);

    @Autowired
    private CommandGateway commandGateway;

    @Autowired
    private HttpServletResponse response;

    @RequestMapping(method = RequestMethod.POST)
    public void create(@RequestBody(required = true) JSONObject input){
        LOGGER.info(input.toJSONString());

        int responseCode = HttpServletResponse.SC_BAD_REQUEST;

        if(input.containsKey("username") && input.containsKey("products")){
            String username = input.getString("username");
            JSONArray products = input.getJSONArray("products");
            if(!StringUtils.isEmpty(username) && products.size()>0){
                Map<String, Integer> map = new HashMap<>();
                CreateOrderCommand command = new CreateOrderCommand(username, map);
                for(Object each:products){
                    JSONObject o = (JSONObject)each;
                    if(!o.containsKey("id") || !o.containsKey("number"))
                        return;
                    map.put(o.getString("id"), o.getInteger("number"));
                }
                commandGateway.sendAndWait(command);
                responseCode = HttpServletResponse.SC_CREATED;
            }
        }

        response.setStatus(responseCode);
    }


}
