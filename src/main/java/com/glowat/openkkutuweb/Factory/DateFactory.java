package com.glowat.openkkutuweb.Factory;

import java.time.format.DateTimeFormatter;
import lombok.Getter;

@Getter
public class DateFactory {

  private final DateTimeFormatter DATABASE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
  private final DateTimeFormatter PRETTY_FORMAT = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분 ss초");
}
