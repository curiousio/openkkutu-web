package com.glowat.openkkutuweb.view;


import jakarta.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpHeaders;

public class views {
  private Map<view, DesktopAndMobile> viewMap = new HashMap<>();

  public views() {
    viewMap.put(view.LAYOUT, new DesktopAndMobile("layout", "layout"));
    viewMap.put(view.PORTAL, new DesktopAndMobile("portal", "portal"));
    viewMap.put(view.KKUTU, new DesktopAndMobile("kkutu", "kkutu"));
    viewMap.put(view.REACT, new DesktopAndMobile("react", "react"));
  }

  String getView(HttpServletRequest httpServletRequest, view view) {
    DesktopAndMobile desktopAndMobile = viewMap.get(view);
    if (desktopAndMobile == null) {
      throw new IllegalArgumentException(view.name() + " 를 ViewMap 에서 찾을 수 없습니다.");
    }

    String userAgent = httpServletRequest.getHeader(HttpHeaders.USER_AGENT);
    if (userAgent != null && userAgent.toLowerCase().contains("mobile")) {
      return desktopAndMobile.getMobile();
    } else {
      return desktopAndMobile.getDesktop();
    }
  }


}
