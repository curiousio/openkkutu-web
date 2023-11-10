package com.glowat.openkkutuweb.ip;

import java.util.HashMap;
import java.util.Map;

/*
        "SK-3G-1/LTE-2/1" to IpAddressMatcher("203.226.192.0/19"),
        "SK-3G-1/LTE-2/2" to IpAddressMatcher("203.226.224.0/20"),
        "SK-3G-1/LTE-2/3" to IpAddressMatcher("203.226.240.0/21"),
        "SK-3G-1/LTE-2/4" to IpAddressMatcher("203.226.248.0/22"),
        "SK-3G-1/LTE-2/5" to IpAddressMatcher("203.226.252.0/24"),
        "SK-3G-2/1" to IpAddressMatcher("211.234.128.0/18"),
        "SK-3G-2/2" to IpAddressMatcher("211.234.192.0/19"),
        "SK-3G-2/3" to IpAddressMatcher("211.234.224.0/20"),
        "SK-3G-3/1" to IpAddressMatcher("211.234.244.0/22"),
        "SK-3G-3/2" to IpAddressMatcher("211.234.248.0/21"),
        "SK-LTE-1/1" to IpAddressMatcher("27.160.0.0/12"),
        "SK-LTE-1/2" to IpAddressMatcher("27.176.0.0/13"),
        "SK-LTE-3/5G-1/1" to IpAddressMatcher("223.32.0.0/11"),

        "KT-3G-1/LTE-1/5G-1/1" to IpAddressMatcher("39.7.0.0/16"),
        "KT-3G-2/LTE-2/5G-2/1" to IpAddressMatcher("110.70.0.0/16"),
        "KT-3G-3/LTE-4/1" to IpAddressMatcher("175.223.0.0/16"),
        "KT-3G-4/LTE-5/1" to IpAddressMatcher("221.246.0.0/16"),
        "KT-LTE-3/5G-3/1" to IpAddressMatcher("118.235.0.0/16"),

        "LG-3G-1/1" to IpAddressMatcher("61.43.0.0/16"),
        "LG-3G-2/1" to IpAddressMatcher("211.234.0.0/18"),
        "LG-3G-2/2" to IpAddressMatcher("211.234.64.0/19"),
        "LG-LTE-1/1" to IpAddressMatcher("106.102.0.0/16"),
        "LG-LTE-2/1" to IpAddressMatcher("117.111.0.0/16"),
        "LG-LTE-3/1" to IpAddressMatcher("211.36.128.0/19"),
        "LG-LTE-4/1" to IpAddressMatcher("211.36.224.0/19"),
        "LG-5G-1/1" to IpAddressMatcher("106.101.0.0/16"),

        "Private-1" to IpAddressMatcher("10.0.0.0/8"),
        "Private-2" to IpAddressMatcher("172.16.0.0/12"),
        "Private-3" to IpAddressMatcher("192.168.0.0/16")
 */

public class IpCheckService {
  private final Map<String, IpAddressMatcher> whitelistMatchers = new HashMap<>();

  public IpCheckService() {
    whitelistMatchers.put("SK-3G-1/LTE-2/1", new IpAddressMatcher("203.226.192.0/19"));
    whitelistMatchers.put("SK-3G-1/LTE-2/2", new IpAddressMatcher("203.226.224.0/20"));
    whitelistMatchers.put("SK-3G-1/LTE-2/3", new IpAddressMatcher("203.226.240.0/21"));
    whitelistMatchers.put("SK-3G-1/LTE-2/4", new IpAddressMatcher("203.226.248.0/22"));
    whitelistMatchers.put("SK-3G-1/LTE-2/5", new IpAddressMatcher("203.226.252.0/24"));
    whitelistMatchers.put("SK-3G-2/1", new IpAddressMatcher("211.234.128.0/18"));
    whitelistMatchers.put("SK-3G-2/2", new IpAddressMatcher("211.234.192.0/19"));
    whitelistMatchers.put("SK-3G-2/3", new IpAddressMatcher("211.234.224.0/20"));
    whitelistMatchers.put("SK-3G-3/1", new IpAddressMatcher("211.234.244.0/22"));
    whitelistMatchers.put("SK-3G-3/2", new IpAddressMatcher("211.234.248.0/21"));
    whitelistMatchers.put("SK-LTE-1/1", new IpAddressMatcher("27.160.0.0/12"));
    whitelistMatchers.put("SK-LTE-1/2", new IpAddressMatcher("27.176.0.0/13"));
    whitelistMatchers.put("SK-LTE-3/5G-1/1", new IpAddressMatcher("223.32.0.0/11"));
    whitelistMatchers.put("KT-3G-1/LTE-1/5G-1/1", new IpAddressMatcher("39.7.0.0/16"));
    whitelistMatchers.put("KT-3G-2/LTE-2/5G-2/1", new IpAddressMatcher("110.70.0.0/16"));
    whitelistMatchers.put("KT-3G-3/LTE-4/1", new IpAddressMatcher("175.223.0.0/16"));
    whitelistMatchers.put("KT-3G-4/LTE-5/1", new IpAddressMatcher("221.246.0.0/16"));
    whitelistMatchers.put("KT-LTE-3/5G-3/1", new IpAddressMatcher("118.235.0.0/16"));
    whitelistMatchers.put("LG-3G-1/1", new IpAddressMatcher("61.43.0.0/16"));
    whitelistMatchers.put("LG-3G-2/1", new IpAddressMatcher("211.234.0.0/18"));
    whitelistMatchers.put("LG-3G-2/2", new IpAddressMatcher("211.234.64.0/19"));
    whitelistMatchers.put("LG-LTE-1/1", new IpAddressMatcher("106.102.0.0/16"));
    whitelistMatchers.put("LG-LTE-2/1", new IpAddressMatcher("117.111.0.0/16"));
    whitelistMatchers.put("LG-LTE-3/1", new IpAddressMatcher("211.36.128.0/19"));
    whitelistMatchers.put("LG-LTE-4/1", new IpAddressMatcher("211.36.224.0/19"));
    whitelistMatchers.put("LG-5G-1/1", new IpAddressMatcher("106.101.0.0/16"));
    whitelistMatchers.put("Private-1", new IpAddressMatcher("10.0.0.0/8"));
    whitelistMatchers.put("Private-2", new IpAddressMatcher("172.16.0.0/12"));
    whitelistMatchers.put("Private-3", new IpAddressMatcher("192.168.0.0/16"));
  }

  public String getBlacklistType(String ip) {
    for(Map.Entry<String, IpAddressMatcher> entry : whitelistMatchers.entrySet()) {
      if(entry.getValue().matches(ip)) {
        return entry.getKey();
      }
    }
    return null;
  }



}






