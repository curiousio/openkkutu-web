package com.glowat.openkkutuweb.Extension;

import jakarta.servlet.http.HttpServletRequest;

public class HttpServletRequestExt {

  public String getIp(HttpServletRequest request) {
    String cfHeader = request.getHeader("HTTP_CF-CONNECTING-IP");
    String forwardedForHeader = request.getHeader("X-FORWARDED-FOR");

    if (cfHeader != null) {
      if (cfHeader.contains(",")) {
        return cfHeader.split(",")[0].trim();
      } else {
        return cfHeader;
      }
    } else if (forwardedForHeader != null) {
      if (forwardedForHeader.contains(",")) {
        return forwardedForHeader.split(",")[0].trim();
      } else {
        return forwardedForHeader;
      }
    } else {
      return request.getRemoteAddr();
    }
  }
}
