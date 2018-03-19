package com.outfittery.booking.service.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import com.outfittery.booking.service.BookingCommandHandlerConfig;
import com.outfittery.booking.service.messaging.BookingServiceMessagingConfiguration;
import com.outfittery.booking.service.web.BookingWebConfiguration;
import com.outfittery.common.CommonSwagger;

import io.eventuate.jdbckafka.TramJdbcKafkaConfiguration;
import io.eventuate.tram.commands.common.ChannelMapping;
import io.eventuate.tram.commands.common.DefaultChannelMapping;

@SpringBootApplication
@Import({BookingWebConfiguration.class, BookingCommandHandlerConfig.class,
    BookingServiceMessagingConfiguration.class, TramJdbcKafkaConfiguration.class, CommonSwagger.class})
public class BookingServiceMain
{

    public static void main(String[] args)
    {
        SpringApplication.run(BookingServiceMain.class, args);
    }


    @Bean
    public ChannelMapping channelMapping()
    {
        return new DefaultChannelMapping.DefaultChannelMappingBuilder().build();
    }
}
