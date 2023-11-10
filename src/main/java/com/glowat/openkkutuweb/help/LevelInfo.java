package com.glowat.openkkutuweb.help;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LevelInfo {
  private int level;
  private Long requiredExp;
  private Long TotalExp;
}
