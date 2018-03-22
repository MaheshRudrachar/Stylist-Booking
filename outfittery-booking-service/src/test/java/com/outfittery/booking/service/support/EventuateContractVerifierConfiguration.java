package com.outfittery.booking.service.support;

import org.springframework.cloud.contract.verifier.messaging.MessageVerifier;
import org.springframework.cloud.contract.verifier.messaging.internal.ContractVerifierMessaging;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.eventuate.tram.messaging.common.Message;

@Configuration
public class EventuateContractVerifierConfiguration {

  @Bean
  public MessageVerifier eventuateTramMessageVerifier() {

    return new EventuateTramMessageVerifier();
  }

  @Bean
  public ContractVerifierMessaging<Message> eventuateContractVerifierMessaging(MessageVerifier<Message> exchange) {
    return new EventuateContractVerifierMessaging(exchange);
  }

}