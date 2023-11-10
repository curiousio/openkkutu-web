package com.glowat.openkkutuweb.User;

import com.glowat.openkkutuweb.Extension.HttpSessionExt;
import com.glowat.openkkutuweb.Extension.JsonNodeExt;
import com.glowat.openkkutuweb.oauth.OAuthUser;
import com.glowat.openkkutuweb.shop.ShopDao;
import com.glowat.openkkutuweb.shop.ShopService;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserDAO userDAO;
  private final ShopDao shopDao;
  private final ShopService shopService;
  private final HttpSessionExt httpSessionExt = new HttpSessionExt();
  private final JsonNodeExt jsonNodeExt = new JsonNodeExt();

  private Logger logger = LoggerFactory.getLogger(this.getClass());
  private final Pattern similarityRegex = Pattern.compile("[-_ ]*");

  private String getBox(HttpSession session, String data) throws IOException {
    if(httpSessionExt.isGuest(session)) {
      return "{\"error\":400}";
    }
    OAuthUser oAuthUser = httpSessionExt.getOauthUser(session);
    String userId = oAuthUser.getUserId();
    User user = userDAO.getUser(userId);

    if(user == null) {
      return "{\"error\":400}";
    }
    return jsonNodeExt.toJson(user.getBox());
  }

  private String exordial(HttpSession session, String data) throws IOException {
    int maxExordialLength = 100;
    if(httpSessionExt.isGuest(session)) {
      return "{\"error\":400}";
    }
    OAuthUser oAuthUser = httpSessionExt.getOauthUser(session);
    String userId = oAuthUser.getUserId();

    int endIndex = Math.min(data.length(), maxExordialLength);
    String resultData = data.substring(0, endIndex).trim();

    Map<String, Object> updateMap = new HashMap<>();
    updateMap.put("exordial", resultData.isEmpty() ? null : resultData);

    userDAO.updateUser(userId, updateMap);

    logger.info(userId + "님이 프로필을 수정하였습니다. 소개 한마디 : " + resultData);
    return "{\"text\":\"" + resultData + "\"}";

  }

  private String getUserData(String nickname) throws IOException {
    User user = userDAO.getUser(nickname);
    if(user == null) {
      return "{\"error\":405}";
    }
    return "{\"result\":200,\"id\":\"" + user.getId() + "\",\"data\":" + jsonNodeExt.toJson(user.getKkutu()) +
        ",\"equip\":" + jsonNodeExt.toJson(user.getEquip()) + ",\"exordial\":\"" +
        (user.getExordial() != null ? user.getExordial() : "") +
        "\",\"profile\":{\"authtype\":\"offline\",\"id\":\"" + user.getId() +
        "\",\"title\":\"" + user.getNickname() + "\"}}";
  }
}
