package com.outfittery.customer.service.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.outfittery.common.CommonSwagger;
import com.outfittery.customer.service.web.CustomerWebConfiguration;

import io.eventuate.jdbckafka.TramJdbcKafkaConfiguration;

@SpringBootApplication
@Import({CustomerWebConfiguration.class, TramJdbcKafkaConfiguration.class, CommonSwagger.class})
public class CustomerServiceMain
{

    public static void main(String[] args)
    {
        SpringApplication.run(CustomerServiceMain.class, args);
    }
}
