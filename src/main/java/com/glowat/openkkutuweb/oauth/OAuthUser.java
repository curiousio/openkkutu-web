package com.glowat.openkkutuweb.oauth;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;

/*
    val authVendor: AuthVendor,
    val vendorId: String,
    val name: String,
    val profileImage: String?,
    val gender: Gender?,
    val minAge: Int?,
    val maxAge: Int?
 */

@Data
@Getter
public class OAuthUser {
  private final AuthVendor authVendor;
  private final String vendorId;
  private final String name;
  private final String profileImage;
  private final Gender gender;
  private final Integer minAge;
  private final Integer maxAge;

  @JsonIgnore
  public String getUserId() {
    return authVendor.name().toLowerCase() + "_" + vendorId;
  }



}
