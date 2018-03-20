package com.outfittery.booking.service.a;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MessagingStubConfig {
    
    private Set<String> channels = new HashSet<>();

    public MessagingStubConfig(String... channels) {
      this.channels = new HashSet<>(Arrays.asList(channels));
    }

    public void add(String channel) {
      channels.add(channel);
    }

    public Set<String> getChannels() {
      return channels;
    }
}