package com.glowat.openkkutuweb.shop.response;

import lombok.Getter;

@Getter
public class ResponseGoodDetail {

  private final String name;
  private final String detail;

  public ResponseGoodDetail(String name, String detail) {
    this.name = name;
    this.detail = detail;
  }
}
