package com.glowat.openkkutuweb.Setting;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
/*
 data class OAuthVendorSetting(
    val order: Short,
    val clientId: String,
    val clientSecret: String,
    val callbackUrl: String,
    val allowRegister: Boolean
)
*/

@Data
@AllArgsConstructor
public class OAuthVendorSetting {
  private short order;
  private String clientID;
  private String clientSecret;
  private String callbackUrl;
  private boolean allowRegister;

  public OAuthVendorSetting(short order, String clientID, String clientSecret, String callbackUrl, boolean aBoolean) {
    this.order=order;
    this.clientID=clientID;
    this.clientSecret=clientSecret;
    this.callbackUrl=callbackUrl;
    this.allowRegister=aBoolean;
  }
}
