package com.glowat.openkkutuweb.Extension;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.glowat.openkkutuweb.SessionAttribute;
import com.glowat.openkkutuweb.oauth.OAuthUser;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;


public class HttpSessionExt {

  private final ObjectMapper objectMapper = new ObjectMapper();

  public Boolean isGuest(HttpSession httpSession) {
    Object attribute = httpSession.getAttribute(SessionAttribute.IS_GUEST.getAttributeName());
    return attribute != null ? (Boolean) attribute : true;
  }

  public OAuthUser getOauthUser(HttpSession httpSession) throws IOException {
    String jsonValue = httpSession.getAttribute(SessionAttribute.OAUTH_USER.getAttributeName()).toString();
    return objectMapper.readValue(jsonValue, OAuthUser.class);
  }

  public void SetOAuthUser(OAuthUser oAuthUser, HttpSession httpSession) throws IOException {
    String jsonValue = objectMapper.writeValueAsString(oAuthUser);
    httpSession.setAttribute(SessionAttribute.OAUTH_USER.getAttributeName(), jsonValue);
  }

  public void setAttribute(String attributeName, Object value, HttpSession httpSession) {
    httpSession.setAttribute(attributeName, value);
  }

  public void removeAttribute(String attributeName, HttpSession httpSession) {
    httpSession.removeAttribute(attributeName);
  }

}
