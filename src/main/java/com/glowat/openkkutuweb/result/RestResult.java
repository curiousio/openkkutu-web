package com.glowat.openkkutuweb.result;

import lombok.Getter;

@Getter
public enum RestResult {
  SUCCESS("성공"),
  BAD_REQUEST("잘못된 요청");

  public final String name;

  RestResult(String name) {
    this.name = name;
  }

}
