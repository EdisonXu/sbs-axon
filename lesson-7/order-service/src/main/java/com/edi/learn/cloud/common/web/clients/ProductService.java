package com.edi.learn.cloud.common.web.clients;

import com.edi.learn.cloud.common.web.dto.ProductDto;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Edison Xu on 2017/3/29.
 */
@FeignClient(value = "product-service")
public interface ProductService {

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    Resources<ProductDto> getProducts();

    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
    ProductDto getProduct(@PathVariable("id") String productId);
}
