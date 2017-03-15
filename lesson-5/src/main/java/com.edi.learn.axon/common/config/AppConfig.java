package com.edi.learn.axon.common.config;

import com.edi.learn.axon.command.config.OrderConfig;
import com.edi.learn.axon.command.config.ProductConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by Edison Xu on 2017/3/14.
 */
@Configuration
@Import({
        AxonConfiguration.class,
        OrderConfig.class,
        ProductConfig.class
})
public class AppConfig {
}
