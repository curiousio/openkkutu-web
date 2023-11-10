package com.glowat.openkkutuweb.result;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ActionResult {
  private boolean success;
  private String resultCode;

  public ActionResult success() {
    return new ActionResult(success = true, resultCode = RestResult.SUCCESS.name);
  }

}
