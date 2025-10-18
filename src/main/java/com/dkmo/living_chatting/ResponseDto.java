package com.dkmo.living_chatting;

public record ResponseDto(String usuario, String email) {
  public static class Builder {
    private String usuario;
    private String email;

    public Builder usuario(String usuario) {
      this.usuario = usuario;
      return this;
    }

    public Builder email(String email) {
      this.email = email;
      return this;
    }

    public ResponseDto build() {
      return new ResponseDto(usuario, email);

    }
  }

  public static Builder builder() {
    return new Builder();
  }
}
