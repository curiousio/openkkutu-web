package com.glowat.openkkutuweb.Extension;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonNodeExt {

  private final ObjectMapper objectMapper = new ObjectMapper();

  public String toJson(JsonNode jsonNode) {
    try {
      return objectMapper.writeValueAsString(jsonNode);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}
