package com.glowat.openkkutuweb;

import lombok.Getter;

@Getter
public enum SessionAttribute {
  IS_GUEST("isGuest"),
  OAUTH_STATE("oAuthState"),
  OAUTH_USER("oAuthUser");

  private final String attributeName;

  SessionAttribute(String attributeName) {
    this.attributeName = attributeName;
  }
}
