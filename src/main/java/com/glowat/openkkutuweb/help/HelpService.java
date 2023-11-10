package com.glowat.openkkutuweb.help;

import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HelpService {
  private final int MAX_LEVEL = 720;
  @Getter
  private List<LevelInfo> levels = new ArrayList<>();

  @PostConstruct
  public long init() {
    long totalExp = 0L;
    for (int lv = 1; lv <= MAX_LEVEL; lv++) {
      long requiredScore = getRequiredExp(lv);
      totalExp += requiredScore;

      levels.add(
          new LevelInfo(
              lv,
              (lv == MAX_LEVEL) ? null : requiredScore,
              (lv == MAX_LEVEL) ? null : totalExp
          )
      );
    }
  }

    public Long getRequiredExp(int lv) {
      float perLevel5Multiplier = (lv % 5 == 0) ? 0.3F + 1F : 1F;
      float perLevel15Multiplier = (lv % 15 == 0) ? 0.4F + 1F : 1F;
      float perLevel45Multiplier = (lv % 45 == 0) ? 0.5F + 1F : 1F;
      float totalMultiplier = perLevel5Multiplier * perLevel15Multiplier * perLevel45Multiplier;

      if (lv <= 240) {
        return Math.round(totalMultiplier * (120 + Math.floor(lv / 5.0) * 60 + Math.floor(lv * lv / 225.0) * 120 + Math.floor(lv * lv / 2025.0) * 180));
      } else if (lv <= 480) {
        return Math.round(totalMultiplier * (120 + Math.floor(lv / 5.0) * 100 + Math.floor(lv * lv / 225.0) * 170 + Math.floor(lv * lv / 2025.0) * 240));
      } else {
        return Math.round(totalMultiplier * (120 + Math.floor(lv / 5.0) * 140 + Math.floor(lv * lv / 225.0) * 220 + Math.floor(lv * lv / 2025.0) * 300));
      }
    }

}
