package com.dkmo.living_chatting.infrastructure.gateways;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;

import com.dkmo.living_chatting.application.gateway.CreateIdGateway;

public class CreateaIdHash implements CreateIdGateway {
  

 @Override
 public String createIdConversa(String user1, String user2) {
    List<String> sorted = Arrays.asList(user1,user2).stream().sorted().toList();
     String base = sorted.get(0)+"_"+sorted.get(1);
     return DigestUtils.sha256Hex(base); 
    
}
}
