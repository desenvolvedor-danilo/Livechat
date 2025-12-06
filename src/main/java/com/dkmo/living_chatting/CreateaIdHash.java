package com.dkmo.living_chatting;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;

public class CreateaIdHash {
 public static String createId(String user1,String user2){
    List<String> sorted = Arrays.asList(user1,user2).stream().sorted().toList();
     String base = sorted.get(0)+"_"+sorted.get(1);
     return DigestUtils.sha256Hex(base); 
  }   
}
