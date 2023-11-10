package com.glowat.openkkutuweb.result;

import lombok.Getter;

@Getter
public enum NickChangeResult {
  INVALID_LENGTH("600"),
  INVALID_PATTERN("601"),
  HAS_BAD_WORDS("602"),
  HAS_BANNED_WORDS("603"),
  ALREADY_USING("620");

  public final String code;

  NickChangeResult(String code) {
    this.code = code;
  }
}
