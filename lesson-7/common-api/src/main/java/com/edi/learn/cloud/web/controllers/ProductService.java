package com.edi.learn.cloud.web.controllers;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Edison Xu on 2017/3/28.
 */
@FeignClient(value = "product-service")
public interface ProductService {

    @RequestMapping(value = "/product/{id}", method = RequestMethod.POST)
    void create(@PathVariable(value = "id") String id,
                       @RequestParam(value = "name", required = true) String name,
                       @RequestParam(value = "price", required = true) long price,
                       @RequestParam(value = "stock",required = true) int stock);

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    Resources getProducts();

}
