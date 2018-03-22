package com.outfittery.booking.service.support;

import javax.inject.Inject;

import org.springframework.cloud.contract.verifier.messaging.MessageVerifier;
import org.springframework.cloud.contract.verifier.messaging.internal.ContractVerifierMessage;
import org.springframework.cloud.contract.verifier.messaging.internal.ContractVerifierMessaging;

import io.eventuate.tram.messaging.common.Message;

public class EventuateContractVerifierMessaging extends ContractVerifierMessaging<Message> {

  @Inject
  ContractVerifierMessaging contractVerifierMessaging;

  public EventuateContractVerifierMessaging(MessageVerifier<Message> exchange) {
    super(exchange);
  }

  @Override
  protected ContractVerifierMessage convert(Message m) {
    return m == null ? null : contractVerifierMessaging.create(m.getPayload(), m.getHeaders());
  }
}