package com.glowat.openkkutuweb.shop;

import com.glowat.openkkutuweb.shop.response.ShopResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShopApi {

  private final ShopService shopService;

  @Autowired
  public ShopApi(ShopService shopService) {
    this.shopService = shopService;
  }

  @GetMapping("/shop")
  public ShopResponse getGoods() {
    return shopService.getGoods();
  }

  @PostMapping(value = "/buy/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public String buyGood(@PathVariable String id, HttpSession session)
      throws IOException, SQLException {
    return shopService.buyGood(id, session);
  }

  @PostMapping(value = "/payback/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public String paybackGood(@PathVariable String id, HttpSession session)
      throws SQLException, IOException {
    return shopService.paybackGood(id, session);
  }
}
