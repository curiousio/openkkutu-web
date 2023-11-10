package com.glowat.openkkutuweb.charfactory;

import lombok.Data;

@Data
public class Reward {
  private String key;
  private Double rate;
  private Boolean allowExpire = false;

}
