package com.dkmo.living_chatting.domain.model;

public class NotificationReference {
private String title;
private String body;
private String tokenTarget;
/**
 * @return the title
 */
public String getTitle() {
  return title;
}
/**
 * @return the body
 */
public String getBody() {
  return body;
}
/**
 * @return the tokenTarget
 */
public String getTokenTarget() {
  return tokenTarget;
}
/**
 * @param title
 * @param body
 * @param tokenTarget
 */
public NotificationReference(String title, String body, String tokenTarget) {
  this.title = title;
  this.body = body;
  this.tokenTarget = tokenTarget;
}

}
