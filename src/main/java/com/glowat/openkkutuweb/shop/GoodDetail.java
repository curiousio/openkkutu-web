package com.glowat.openkkutuweb.shop;

import lombok.Getter;

@Getter
public class GoodDetail {

  private final String id;
  private final String namekoKR;
  private final String desckoKR;
  private final String nameenUS;
  private final String descenUS;

  public GoodDetail(String id, String namekoKR, String desckoKR, String nameenUS, String descenUS) {
    this.id = id;
    this.namekoKR = namekoKR;
    this.desckoKR = desckoKR;
    this.nameenUS = nameenUS;
    this.descenUS = descenUS;
  }
}
