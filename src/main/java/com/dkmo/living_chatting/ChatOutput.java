package com.dkmo.living_chatting;

public record ChatOutput(String idMensagem, String email, String from, String content, String timeStamp) {
  public static class Builder {
    private String idMensagem;
    private String email;
    private String from;
    private String content;
    private String timeStamp;

    public Builder idMensagem(String idMensagem) {
      this.idMensagem = idMensagem;
      return this;
    }

    public Builder email(String email) {
      this.email = email;
      return this;
    }

    public Builder from(String from) {
      this.from = from;
      return this;
    }

    public Builder content(String content) {
      this.content = content;
      return this;
    }

    public Builder timeStamp(String timeStamp) {
      this.timeStamp = timeStamp;
      return this;
    }

    public ChatOutput build() {

      return new ChatOutput(idMensagem, email, from, content, timeStamp);
    }
  }

  public static Builder builder() {
    return new Builder();
  }
}
