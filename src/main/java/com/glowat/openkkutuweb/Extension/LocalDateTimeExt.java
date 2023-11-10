package com.glowat.openkkutuweb.Extension;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class LocalDateTimeExt {

  public static Timestamp toTimestamp(LocalDateTime localDateTime) {
    return Timestamp.valueOf(localDateTime);
  }

}
