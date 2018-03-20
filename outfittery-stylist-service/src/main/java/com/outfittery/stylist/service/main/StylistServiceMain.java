package com.outfittery.stylist.service.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.outfittery.common.CommonSwagger;
import com.outfittery.stylist.service.domain.StylistServiceDomainConfiguration;
import com.outfittery.stylist.service.web.StylistWebConfiguration;

import io.eventuate.jdbckafka.TramJdbcKafkaConfiguration;
import io.eventuate.tram.commands.common.ChannelMapping;
import io.eventuate.tram.commands.common.DefaultChannelMapping;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

@Configuration
@SpringBootApplication
@EnableAutoConfiguration
@Import({StylistWebConfiguration.class, TramJdbcKafkaConfiguration.class, CommonSwagger.class})
public class StylistServiceMain
{

    public static void main(String[] args)
    {
        SpringApplication.run(StylistServiceMain.class, args);
    }
}
