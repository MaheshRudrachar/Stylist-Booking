package com.outfittery.stylist.service.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import com.outfittery.common.CommonSwagger;

import io.eventuate.jdbckafka.TramJdbcKafkaConfiguration;
import io.eventuate.tram.commands.common.ChannelMapping;
import io.eventuate.tram.commands.common.DefaultChannelMapping;

@SpringBootApplication
@EnableAutoConfiguration
@Import({
    TramJdbcKafkaConfiguration.class,
    CommonSwagger.class})
@ComponentScan
public class StylistServiceMain
{

    public static void main(String[] args)
    {
        SpringApplication.run(StylistServiceMain.class, args);
    }


    @Bean
    public ChannelMapping channelMapping()
    {
        return new DefaultChannelMapping.DefaultChannelMappingBuilder().build();
    }
}
