package com.dkmo.living_chatting.application.usecases;

import com.dkmo.living_chatting.application.gateway.LogoutGateway;

public class LogoutUseCase {
  private final LogoutGateway logoutGateway;

  /**
   * @param logoutGateway
   */
  public LogoutUseCase(LogoutGateway logoutGateway) {
    this.logoutGateway = logoutGateway;
  }

  public void execute() {
    logoutGateway.Logout();
  }
}
