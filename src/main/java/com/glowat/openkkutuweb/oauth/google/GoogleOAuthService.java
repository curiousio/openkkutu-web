package com.glowat.openkkutuweb.oauth.google;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.glowat.openkkutuweb.oauth.AuthVendor;
import com.glowat.openkkutuweb.oauth.Gender;
import com.glowat.openkkutuweb.oauth.OAuthService;
import com.glowat.openkkutuweb.oauth.OAuthUser;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.github.scribejava.apis.GoogleApi20;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Verb;

@Service
@RequiredArgsConstructor
public class GoogleOAuthService extends OAuthService {

  private final ObjectMapper objectMapper;
  private final String GOOGLE_AUTH_URL = "https://www.googleapis.com/userinfo/v2/me";

  @Override
  public void init(String apiKey, String apiSecret, String callbackUrl, boolean allowRegister) {
    this.allowRegister = allowRegister;
    this.oAuth20Service = new ServiceBuilder(apiKey)
        .apiSecret(apiSecret)
        .callback(callbackUrl)
        .defaultScope("profile")
        .build(GoogleApi20.instance());
  }

  @Override
  public OAuthUser login(String code) throws IOException, ExecutionException, InterruptedException {
    final String accessToken = String.valueOf(oAuth20Service.getAccessToken(code));
    final OAuthRequest request = new OAuthRequest(Verb.GET, GOOGLE_AUTH_URL);
    oAuth20Service.signRequest(accessToken, request);
    final String responseBody = oAuth20Service.execute(request).getBody();
    final JsonNode jsonResponse = objectMapper.readTree(responseBody);

    return new OAuthUser( AuthVendor.GOOGLE,
        jsonResponse.get("id").textValue(),
        jsonResponse.get("name").textValue(),
        jsonResponse.get("picture").textValue(),
        jsonResponse.has("gender") ? Gender.fromName(jsonResponse.get("gender").textValue()) : null,
        jsonResponse.has("ageRange") && jsonResponse.get("ageRange").has("min") ? jsonResponse.get("ageRange").get("min").intValue() : null,
        jsonResponse.has("ageRange") && jsonResponse.get("ageRange").has("max") ? jsonResponse.get("ageRange").get("max").intValue() : null
    );
  }



}
