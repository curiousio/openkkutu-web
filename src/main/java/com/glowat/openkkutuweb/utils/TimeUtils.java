package com.glowat.openkkutuweb.utils;


import com.glowat.openkkutuweb.Factory.SecondsFactory;

public class TimeUtils {
  SecondsFactory secondsFactory = new SecondsFactory();

  public String getTimeTextForSecond(Long Seconds) {
    StringBuilder timeText = new StringBuilder();
    Long totalSeconds = Seconds;
    if (totalSeconds / secondsFactory.ONE_WEEK > 0) {
      timeText.append(totalSeconds / secondsFactory.ONE_WEEK).append("주 ");
      totalSeconds %= secondsFactory.ONE_WEEK;
    }
    if (totalSeconds / secondsFactory.ONE_DAY > 0) {
      timeText.append(totalSeconds / secondsFactory.ONE_DAY).append("일 ");
      totalSeconds %= secondsFactory.ONE_DAY;
    }
    if (totalSeconds / secondsFactory.ONE_HOUR > 0) {
      timeText.append(totalSeconds / secondsFactory.ONE_HOUR).append("시간 ");
      totalSeconds %= secondsFactory.ONE_HOUR;
    }
    if (totalSeconds / secondsFactory.ONE_MINUTES > 0) {
      timeText.append(totalSeconds / secondsFactory.ONE_MINUTES).append("분 ");
      totalSeconds %= secondsFactory.ONE_MINUTES;
    }
    if (totalSeconds > 0) {
      timeText.append(totalSeconds).append("초");
    }
    return timeText.toString();
  }
}
