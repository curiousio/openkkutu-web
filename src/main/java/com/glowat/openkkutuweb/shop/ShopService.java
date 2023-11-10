package com.glowat.openkkutuweb.shop;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.IntNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.glowat.openkkutuweb.Extension.HttpSessionExt;
import com.glowat.openkkutuweb.Extension.JsonNodeExt;
import com.glowat.openkkutuweb.User.User;
import com.glowat.openkkutuweb.User.UserDAO;
import com.glowat.openkkutuweb.oauth.OAuthUser;
import com.glowat.openkkutuweb.shop.response.ResponseGood;
import com.glowat.openkkutuweb.shop.response.ResponseGoodDetail;
import com.glowat.openkkutuweb.shop.response.ShopResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import org.postgresql.util.PGobject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Getter
public class ShopService {

  private final Logger logger = LoggerFactory.getLogger(ShopService.class);

  private final ObjectMapper objectMapper;
  private final ShopDao shopDao;
  private final ShopDetailDao shopDetailDao;
  private final UserDAO userDao;

  private final HttpSessionExt httpSessionExt = new HttpSessionExt();
  private final JsonNodeExt jsonNodeExt = new JsonNodeExt();

  @Autowired
  public ShopService(ObjectMapper objectMapper, ShopDao shopDao, ShopDetailDao shopDetailDao,
      UserDAO userDao) {
    this.objectMapper = objectMapper;
    this.shopDao = shopDao;
    this.shopDetailDao = shopDetailDao;
    this.userDao = userDao;
  }

  public ShopResponse getGoods() {
    List<ResponseGood> responseGoods = shopDao.getGoods().stream()
        .map(ResponseGood::fromGood)
        .toList();

    return new ShopResponse(responseGoods);
  }

  public String buyGood(String id, HttpSession session) throws IOException, SQLException {
    if (httpSessionExt.isGuest(session)) {
      return "{\"error\":423}";
    }

    OAuthUser oAuthUser = httpSessionExt.getOauthUser(session);
    if (oAuthUser == null) {
      return "{\"error\":423}";
    }

    Good good = shopDao.getGood(id);
    if (good == null || good.getCost() < 0) {
      return "{\"error\":400}";
    }

    String userId = oAuthUser.getUserId();
    User user = userDao.getUser(userId);
    if (user == null) {
      return "{\"error\":400}";
    }

    int afterBuyMoney = (int) (user.getMoney() - good.getCost());
    if (afterBuyMoney < 0) {
      return "{\"error\":400}";
    }

    obtainGood(user.getBox(), id, 1, good.getTerm(), false);

    PGobject userBoxJsonObj = new PGobject();
    userBoxJsonObj.setType("json");
    userBoxJsonObj.setValue(jsonNodeExt.toJson(user.getBox()));

    userDao.updateUser(user.getId(), Map.of("money", afterBuyMoney, "box", userBoxJsonObj));
    shopDao.increaseHit(id);

    String userName =
        user.getNickname() == null ? userId : user.getNickname() + " (" + userId + ")";
    logger.info(userName + " 님이 " + id + " 상품을 구매했습니다.");

    return "{\"result\": 200, \"money\": " + afterBuyMoney + ", \"box\": " + jsonNodeExt.toJson(
        user.getBox())
        + "}";

  }

  public String paybackGood(String id, HttpSession session) throws IOException, SQLException {
    if (httpSessionExt.isGuest(session)) {
      return "{\"error\":400}";
    }

    OAuthUser oAuthUser = httpSessionExt.getOauthUser(session);
    if (oAuthUser == null) {
      return "{\"error\":400}";
    }

    String userId = oAuthUser.getUserId();
    User user = userDao.getUser(userId);
    if (user == null) {
      return "{\"error\":400}";
    }

    JsonNode box = user.getBox();
    if (!box.has(id)) {
      return "{\"error\":430}";
    }

    boolean isDyn = id.startsWith("$");
    Good good = shopDao.getGood(isDyn ? id.substring(0, 4) : id);
    if (good == null) {
      return "{\"error\":430}";
    }

    consumeGood(user.getBox(), id, 1, true);

    PGobject userBoxJsonObj = new PGobject();
    userBoxJsonObj.setType("json");
    userBoxJsonObj.setValue(jsonNodeExt.toJson(user.getBox()));

    int afterPaybackMoney = (int) (user.getMoney() + 0.2 * good.getCost());
    userDao.updateUser(user.getId(), Map.of("money", afterPaybackMoney, "box", userBoxJsonObj));

    String userName =
        user.getNickname() == null ? userId : user.getNickname() + " (" + userId + ")";
    logger.info(userName + " 님이 " + id + " 상품을 환불했습니다.");

    return "{\"result\": 200, \"money\": " + afterPaybackMoney + ", \"box\": " + jsonNodeExt.toJson(
        user.getBox()) + "}";
  }

  public void obtainGood(JsonNode box, String goodId, int value, Integer term, boolean addValue) {
    if (box instanceof ObjectNode) {
      ObjectNode boxObjectNode = (ObjectNode) box;
      ObjectNode goodJson = boxObjectNode.has(goodId) ? (ObjectNode) boxObjectNode.get(goodId)
          : objectMapper.createObjectNode();

      if (term == null || term == 0) {
        goodJson.put("value",
            goodJson.has("value") ? goodJson.get("value").intValue() + value : value);
      } else {
        if (goodJson.has("expire")) {
          if (addValue) {
            goodJson.put("value", goodJson.get("value").intValue() + value);
          } else {
            goodJson.put("expire", goodJson.get("expire").intValue() + term);
          }
        } else if (goodJson.has("value")) {
          // no action
        } else {
          long currentTime = System.currentTimeMillis();
          goodJson.put("value", value);
          if (addValue) {
            goodJson.put("expire", term);
          } else {
            goodJson.put("expire", (long) (currentTime * 0.001 + term));
          }
        }
      }
      boxObjectNode.set(goodId, goodJson);
    }
  }

  public void consumeGood(JsonNode box, String goodId, int value, boolean force) {
    if (box instanceof ObjectNode) {
      ObjectNode boxObjectNode = (ObjectNode) box;
      JsonNode goodJson = boxObjectNode.get(goodId);

      if (goodJson instanceof ObjectNode) {
        ObjectNode goodObjectNode = (ObjectNode) goodJson;
        int afterConsumeValue = goodObjectNode.get("value").intValue() - value;
        goodObjectNode.put("value", afterConsumeValue);

        if (afterConsumeValue <= 0) {
          if (force) {
            boxObjectNode.remove(goodId);
          }
        }
      } else if (goodJson instanceof IntNode) {
        int afterConsumeCount = goodJson.intValue() - value;
        boxObjectNode.put(goodId, afterConsumeCount);

        if (afterConsumeCount <= 0) {
          boxObjectNode.remove(goodId);
        }
      }
    }
  }

  public Map<String, ResponseGoodDetail> getGoodDetails() {
    Map<String, ResponseGoodDetail> resultMap = new HashMap<>();

    List<GoodDetail> goodDetails = shopDetailDao.getGoodDetails();
    for (GoodDetail goodDetail : goodDetails) {
      resultMap.put(goodDetail.getId(),
          new ResponseGoodDetail(goodDetail.getNamekoKR(), goodDetail.getDesckoKR()));
    }

    return resultMap;
  }
}
