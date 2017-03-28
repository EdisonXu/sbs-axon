package com.edi.learn.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * Created by Edison Xu on 2017/3/28.
 */
@SpringBootApplication
@EnableZuulProxy
public class ProxyServiceApplication {

    public static void main(String args[]){
        SpringApplication.run(ProxyServiceApplication.class, args);
    }
}
