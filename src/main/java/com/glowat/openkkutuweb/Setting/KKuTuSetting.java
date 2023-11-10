package com.glowat.openkkutuweb.Setting;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.glowat.openkkutuweb.Extension.JsonNodeExt;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import java.io.BufferedReader;
import java.io.IOException;
import org.springframework.stereotype.Component;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import jakarta.annotation.PostConstruct;
public class KKuTuSetting {
  private final JsonNodeExt jsonNodeExt = new JsonNodeExt();
  @Autowired
  private ApplicationArguments applicationArguments;

  @Autowired
  private ObjectMapper objectMapper;
  private final Logger logger = LoggerFactory.getLogger(KKuTuSetting.class);
  private final String runnerUID = UUID.randomUUID().toString();
  private JsonNode kkutu;
  private JsonNode games;
  private JsonNode moremi;
  private JsonNode themes;


  @PostConstruct
  public void init() {
    List<String> optionValues = applicationArguments.getOptionValues("SETTING_DIR");
    if (optionValues == null || optionValues.isEmpty()) {
      logger.error("프로그램 실행 인수에 SETTING_DIR 값이 누락되었습니다.");
      return;
    }

    String settingDir = optionValues.get(0);
    try (BufferedReader reader = Files.newBufferedReader(Paths.get(settingDir, "kkutu.json"))) {
      String jsonText = reader.lines().reduce(String::concat).orElse("");
      JsonNode jsonNode = objectMapper.readTree(jsonText);
      kkutu = jsonNode;
    } catch (IOException e) {
    }

    try (BufferedReader reader = Files.newBufferedReader(Paths.get(settingDir, "games.json"))) {
      String jsonText = reader.lines().reduce(String::concat).orElse("");
      JsonNode jsonNode = objectMapper.readTree(jsonText);
      games = jsonNode;
    } catch (IOException e) {
    }

    try (BufferedReader reader = Files.newBufferedReader(Paths.get(settingDir, "moremi.json"))) {
      String jsonText = reader.lines().reduce(String::concat).orElse("");
      JsonNode jsonNode = objectMapper.readTree(jsonText);
      moremi = jsonNode;
    } catch (IOException e) {
    }

    try (BufferedReader reader = Files.newBufferedReader(Paths.get(settingDir, "themes.json"))) {
      String jsonText = reader.lines().reduce(String::concat).orElse("");
      JsonNode jsonNode = objectMapper.readTree(jsonText);
      themes = jsonNode;
    } catch (IOException e) {
    }
  }

  public String getVersion() {
    return kkutu.get("version").textValue();
  }

  public int getMaxPlayers() {
    return kkutu.get("maxPlayers").intValue();
  }

  public List<GameServerSetting> getGameServers() {
    List<GameServerSetting> serverSettings = new ArrayList<>();
    for (JsonNode serverNode : kkutu.get("gameServers")) {
      boolean isSecure = serverNode.get("isSecure").booleanValue();
      String publicHost = serverNode.get("publicHost").textValue();
      String key = serverNode.get("key").textValue();
      String host = serverNode.get("host").textValue();
      int port = serverNode.get("port").intValue();
      short cid = (short) serverNode.get("cid").intValue();

      GameServerSetting serverSetting = new GameServerSetting(isSecure, publicHost, key, host, port, cid);
      serverSettings.add(serverSetting);
    }
    return serverSettings;
  }

  public static class GameServerSetting {

    private final boolean isSecure;
    private final String publicHost;
    private final String key;
    private final String host;
    private final int port;
    private final short cid;

    public GameServerSetting(boolean isSecure, String publicHost, String key, String host, int port,
        short cid) {
      this.isSecure = isSecure;
      this.publicHost = publicHost;
      this.key = key;
      this.host = host;
      this.port = port;
      this.cid = cid;
    }
  }



  /*
  fun getAdminIds(): List<String> = getAdmins().map { it.id }

    fun getAdmins(): List<AdminSetting> = kkutu["admins"].toList().map {
        AdminSetting(
            it["id"].textValue(),
            it["name"].textValue(),
            it["team"].textValue(),
            it["privileges"].toList().map { privilege -> AdminSetting.Privilege.valueOf(privilege.textValue()) }
        )
    }
   */
  

}
