package com.glowat.openkkutuweb.Setting;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.glowat.openkkutuweb.oauth.AuthVendor;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;
import java.nio.file.Files;
import java.nio.file.Paths;
import jakarta.annotation.PostConstruct;

@Component
public class OAuthSetting {

  private final Logger logger = LoggerFactory.getLogger(OAuthSetting.class);
  @Autowired
  private ApplicationArguments applicationArguments;
  @Autowired
  private ObjectMapper objectMapper;
  private JsonNode settingNode;

  @PostConstruct
  public void init() {
    List<String> optionValues = applicationArguments.getOptionValues("SETTING_DIR");
    if (optionValues == null || optionValues.isEmpty()) {
      logger.error("프로그램 실행 인수에 SETTING_DIR 값이 누락되었습니다.");
      return;
    }
    String settingDir = optionValues.get(0);
    try {
      String jsonText = Files.readString(Paths.get(settingDir, "oauth.json"));
      settingNode = objectMapper.readTree(jsonText);
    } catch (IOException e) {
      logger.error("오류 발생: {}", e.getMessage());
    }
  }
  public Map<AuthVendor,OAuthVendorSetting> getSetting(){
    Map<AuthVendor, OAuthVendorSetting> settings = new HashMap<>();
    settingNode.fieldNames().forEachRemaining(oAuthVendorName -> {
      boolean isEnable = settingNode.get(oAuthVendorName).get("enable").asBoolean();
      if (!isEnable) return;

      AuthVendor vendorType = AuthVendor.fromName(oAuthVendorName);
      if (vendorType == null) return;

      settings.put(vendorType, new OAuthVendorSetting(
          (short) settingNode.get(oAuthVendorName).get("order").asInt(),
          settingNode.get(oAuthVendorName).get("client-id").asText(),
          settingNode.get(oAuthVendorName).get("client-secret").asText(),
          settingNode.get(oAuthVendorName).get("callback-url").asText(),
          settingNode.get(oAuthVendorName).get("allow-register").asBoolean()
      ));
    });
    return settings;
  }
}
