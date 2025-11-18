package com.dkmo.living_chatting;

public record MessageDto(String from, String message) {
  public static class Builder {
    private String from;
    private String message;

    public Builder from(String from) {
      this.from = from;
      return this;
    }

    public Builder message(String message) {
      this.message = message;
      return this;
    }


  public MessageDto build() {
      return new MessageDto(from, message);
  }

  }
 public static Builder builder(){
    return new Builder();
  }
}
