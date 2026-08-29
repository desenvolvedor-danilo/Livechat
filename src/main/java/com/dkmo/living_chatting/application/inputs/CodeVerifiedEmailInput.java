package com.dkmo.living_chatting.application.inputs;

public class CodeVerifiedEmailInput {

  private String recipient;

  /**
   * @return the sender
   */

  /**
   * @param sender
   * @param recipient
   */
  public CodeVerifiedEmailInput(String recipient) {
    this.recipient = recipient;
  }

  public String getRecipient() {
    return recipient;
  }

  /**
   * @param recipient the recipient to set
   */
  public void setRecipient(String recipient) {
    this.recipient = recipient;
  }

}
