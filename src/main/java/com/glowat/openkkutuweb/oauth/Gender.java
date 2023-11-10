package com.glowat.openkkutuweb.oauth;

import lombok.Getter;

@Getter
public enum Gender {
  MALE("M"),
  FEMALE("F"),
  OTHER("");

  private final String genderAttribute;

  Gender(String genderAttribute) {
    this.genderAttribute = genderAttribute;
  }

  public static Gender fromName(String name) {
    for(Gender gender : Gender.values()) {
      if(gender.name().equalsIgnoreCase(name) || gender.getGenderAttribute().equalsIgnoreCase(name)) {
        return gender;
      }
    }
    return OTHER;
  }

}
