package com.glowat.openkkutuweb.shop.response;

import java.util.List;
import lombok.Getter;

@Getter
public class ShopResponse {

  private final List<ResponseGood> goods;

  public ShopResponse(List<ResponseGood> goods) {
    this.goods = goods;
  }
}
