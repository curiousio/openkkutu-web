package com.glowat.openkkutuweb.ip;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class IpAddressMatcher {
  private int nMaskBits = 0;
  private final InetAddress requiredAddress;

  public IpAddressMatcher(String ipAddr) {
    String ipAddress;
    if (ipAddr.indexOf('/') > 0) {
      String[] addressAndMask = ipAddr.split("/");
      ipAddress = addressAndMask[0];
      nMaskBits = Integer.parseInt(addressAndMask[1]);
    } else {
      nMaskBits = -1;
      ipAddress = ipAddr;
    }
    requiredAddress = parseAddress(ipAddress);

    assert requiredAddress.getAddress().length * 8 >= nMaskBits :
        String.format("IP address %s is too short for bitmask of length %d", ipAddress, nMaskBits);
  }

  private InetAddress parseAddress(String address) {
    try {
      return InetAddress.getByName(address);
    } catch (UnknownHostException e) {
      throw new IllegalArgumentException("Failed to parse address " + address, e);
    }
  }

  public boolean matches(String address) {
    InetAddress remoteAddress = parseAddress(address);

    if (!requiredAddress.getClass().equals(remoteAddress.getClass())) {
      return false;
    }

    if (nMaskBits < 0) {
      return requiredAddress.equals(remoteAddress);
    }

    byte[] remAddr = remoteAddress.getAddress();
    byte[] reqAddr = requiredAddress.getAddress();
    int nMaskFullBytes = nMaskBits / 8;
    byte finalByte = (byte) (0xFF00 >>> (nMaskBits & 0x07));

    for (int i = 0; i < nMaskFullBytes; i++) {
      if (remAddr[i] != reqAddr[i]) {
        return false;
      }
    }

    return finalByte == 0 || (remAddr[nMaskFullBytes] & finalByte) == (reqAddr[nMaskFullBytes]
        & finalByte);
  }

}
