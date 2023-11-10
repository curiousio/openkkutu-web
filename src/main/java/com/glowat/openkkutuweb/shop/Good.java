package com.glowat.openkkutuweb.shop;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;

@Getter
public class Good {

  private final String id;
  private final long cost;
  private final int hit;
  private final int term;
  private final String group;
  private final long updatedAt;
  private final JsonNode options;

  public Good(String id, long cost, int hit, int term, String group, long updatedAt,
      JsonNode options) {
    this.id = id;
    this.cost = cost;
    this.hit = hit;
    this.term = term;
    this.group = group;
    this.updatedAt = updatedAt;
    this.options = options;
  }
}
