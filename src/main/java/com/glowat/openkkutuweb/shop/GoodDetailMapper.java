package com.glowat.openkkutuweb.shop;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class GoodDetailMapper implements RowMapper<GoodDetail> {

  private final ObjectMapper objectMapper;

  @Autowired
  public GoodDetailMapper(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  @Override
  public GoodDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
    String id = rs.getString("_id");
    String nameKoKR = rs.getString("name_ko_KR");
    String descKoKR = rs.getString("desc_ko_KR");
    String nameEnUS = rs.getString("name_en_US");
    String descEnUS = rs.getString("desc_en_US");

    return new GoodDetail(id, nameKoKR, descKoKR, nameEnUS, descEnUS);
  }
}
