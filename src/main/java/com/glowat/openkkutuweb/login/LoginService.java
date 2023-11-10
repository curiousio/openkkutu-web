package com.glowat.openkkutuweb.login;


import static com.fasterxml.jackson.databind.type.LogicalType.Map;

import com.glowat.openkkutuweb.User.UserDAO;
import com.glowat.openkkutuweb.oauth.AuthVendor;
import com.glowat.openkkutuweb.oauth.OAuthService;
import com.glowat.openkkutuweb.oauth.google.GoogleOAuthService;
import jakarta.annotation.PostConstruct;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

  private final UserDAO userDAO;
  private final GoogleOAuthService googleOAuthService;

  private final Long stateLength = 50L;

  @PostConstruct
  public void initOAuthServices() {
    for (Map.Entry<AuthVendor, OAuthSetting> entry : oAuthSetting.getSetting().entrySet()) {
      AuthVendor vendorType = entry.getKey();
      OAuthSetting setting = entry.getValue();

      getOAuthService(vendorType).init(setting.getClientId(), setting.getClientSecret(), setting.getCallbackUrl(), setting.isAllowRegister());
    }
  }

  public OAuthService getOAuthService(AuthVendor vendorType) {
    if (Objects.requireNonNull(vendorType) == AuthVendor.GOOGLE) {
      return googleOAuthService;
    }
    throw new IllegalArgumentException("지원하지 않는 OAuth 벤더입니다.");
  }
}
