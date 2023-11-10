package com.glowat.openkkutuweb.Setting;
import lombok.Data;
/*
data class GameServerSetting(
    val isSecure: Boolean,
    val publicHost: String,
    val key: String,
    val host: String,
    val port: Int,
    val cid: Short
)
*/

@Data
public class GameServerSetting {
  private boolean isSecure;
  private String publicHost;
  private String key;
  private String host;
  private int port;
  private short cid;
}
