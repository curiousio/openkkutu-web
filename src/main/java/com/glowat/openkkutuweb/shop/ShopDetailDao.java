package com.glowat.openkkutuweb.shop;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class ShopDetailDao {

  private final JdbcTemplate jdbcTemplate;
  private final GoodDetailMapper shopDetailMapper;

  @Autowired
  public ShopDetailDao(JdbcTemplate jdbcTemplate, GoodDetailMapper shopDetailMapper) {
    this.jdbcTemplate = jdbcTemplate;
    this.shopDetailMapper = shopDetailMapper;
  }

  public List<GoodDetail> getGoodDetails() {
    String sql = "SELECT * FROM kkutu_shop_desc";
    return jdbcTemplate.query(sql, shopDetailMapper);
  }
}
