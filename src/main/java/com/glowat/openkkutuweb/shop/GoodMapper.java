package com.glowat.openkkutuweb.shop;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class GoodMapper implements RowMapper<Good> {

  private final ObjectMapper objectMapper;

  @Autowired
  public GoodMapper(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  @Override
  public Good mapRow(ResultSet rs, int rowNum) throws SQLException {
    String id = rs.getString("_id");
    long cost = rs.getLong("cost");
    int hit = rs.getInt("hit");
    int term = rs.getInt("term");
    String group = rs.getString("group");
    long updatedAt = rs.getLong("updatedAt");
    String options = rs.getString("options");

    JsonNode jsonNode;
    try {
      jsonNode = objectMapper.readTree(options);
    } catch (IOException e) {
      throw new SQLException("Error reading JSON options field", e);
    }

    return new Good(id, cost, hit, term, group, updatedAt, jsonNode);
  }
}
