package com.glowat.openkkutuweb.oauth;

public enum AuthVendor {
  GOOGLE, NAVER;

  public static AuthVendor fromName(String vendorName) {
    for (AuthVendor authVendor : AuthVendor.values()) {
      if (authVendor.name().equalsIgnoreCase(vendorName)) {
        return authVendor;
      }
    }
    return null;
  }

}
