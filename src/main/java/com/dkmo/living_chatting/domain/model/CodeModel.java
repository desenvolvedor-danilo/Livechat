package com.dkmo.living_chatting.domain.model;

import java.util.UUID;

public class CodeModel {
  private UUID id;
  private String hashCode;

  /**
   * @param hashCode
   */
  private CodeModel(String hashCode) {
    this.id = UUID.randomUUID();
    this.hashCode = hashCode;
  }

  private CodeModel(UUID id, String hashCode) {
    this.id = id;
    this.hashCode = hashCode;
  }

  public static CodeModel create(String hashCode) {
    return new CodeModel(hashCode);
  }

  public static CodeModel create(UUID id, String hashCode) {
    return new CodeModel(id, hashCode);
  }

  /**
   * @return the id
   */
  public UUID id() {
    return id;
  }

  /**
   * @return the hashCode
   */
  public String hash() {
    return hashCode;
  }

}
