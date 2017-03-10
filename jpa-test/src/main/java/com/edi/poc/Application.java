package com.edi.poc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by Edison Xu on 2017/3/10.
 */
@SpringBootApplication
@ComponentScan({"com.edi.poc"})
public class Application {

    public static void main(String args[]){
        SpringApplication.run(Application.class, args);
    }
}
