package com.glowat.openkkutuweb.shop;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class ShopDao {

  private final JdbcTemplate jdbcTemplate;
  private final GoodMapper shopMapper;

  @Autowired
  public ShopDao(JdbcTemplate jdbcTemplate, GoodMapper shopMapper) {
    this.jdbcTemplate = jdbcTemplate;
    this.shopMapper = shopMapper;
  }

  public Good getGood(String id) {
    String sql = "SELECT * FROM kkutu_shop WHERE _id = ?";

    List<Good> goods = jdbcTemplate.query(sql, shopMapper, id);
    return goods.isEmpty() ? null : goods.get(0);
  }

  public List<Good> getGoods() {
    String sql = "SELECT * FROM kkutu_shop";
    return jdbcTemplate.query(sql, shopMapper);
  }

  public void increaseHit(String id) {
    String sql = "UPDATE kkutu_shop SET hit = hit + 1 WHERE _id = ?";
    jdbcTemplate.update(sql, id);
  }
}
