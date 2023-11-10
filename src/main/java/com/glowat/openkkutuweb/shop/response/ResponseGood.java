package com.glowat.openkkutuweb.shop.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.glowat.openkkutuweb.shop.Good;
import lombok.Getter;

@Getter
public class ResponseGood {

  @JsonProperty("_id")
  private final String id;
  private final String cost;
  private final int term;
  private final String group;
  private final String updatedAt;
  private final JsonNode options;

  public ResponseGood(String id, String cost, int term, String group, String updatedAt,
      JsonNode options) {
    this.id = id;
    this.cost = cost;
    this.term = term;
    this.group = group;
    this.updatedAt = updatedAt;
    this.options = options;
  }

  public static ResponseGood fromGood(Good good) {
    return new ResponseGood(good.getId(), String.valueOf(good.getCost()), good.getTerm(),
        good.getGroup(), String.valueOf(good.getUpdatedAt()), good.getOptions());
  }
}
