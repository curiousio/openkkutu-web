package com.glowat.openkkutuweb.Extension;

public class ByteArrayExt {

  public String toHeyString(Byte[] byteArray) {
    StringBuilder result = new StringBuilder();
    for (byte b : byteArray) {
      result.append(String.format("%02x", b));
    }
    return result.toString();
  }
}
