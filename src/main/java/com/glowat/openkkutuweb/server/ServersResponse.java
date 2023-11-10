package com.glowat.openkkutuweb.server;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class ServersResponse {
  private final List<Integer> list = new ArrayList<>();
  private final int max;
}
