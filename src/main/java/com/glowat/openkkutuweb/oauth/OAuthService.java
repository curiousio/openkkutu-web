package com.glowat.openkkutuweb.oauth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.scribejava.core.oauth.OAuth20Service;
import java.util.Map;

public abstract class OAuthService {

  protected OAuth20Service oAuth20Service;
  protected boolean allowRegister = true;
  protected Map<String, String> authorizationUrlParams = Map.of();

  public abstract void init(String apiKey, String apiSecret, String callbackUrl,
      boolean allowRegister);

  public abstract OAuthUser login(String code) throws Exception;

  public String getAuthorizationUrl(String state) {
    return oAuth20Service.createAuthorizationUrlBuilder()
        .state(state)
        .additionalParams(authorizationUrlParams)
        .build();
  }

}
