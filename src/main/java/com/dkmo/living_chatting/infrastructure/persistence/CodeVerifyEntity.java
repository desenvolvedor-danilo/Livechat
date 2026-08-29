package com.dkmo.living_chatting.infrastructure.persistence;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "codes")
public class CodeVerifyEntity {
  @Id
  private String id;
  private String hash;

  /**
   * @param id
   * @param hash
   */
  public CodeVerifyEntity(String id, String hash) {
    this.id = id;
    this.hash = hash;
  }

}
