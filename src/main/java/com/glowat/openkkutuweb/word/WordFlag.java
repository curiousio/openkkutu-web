package com.glowat.openkkutuweb.word;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import java.util.Arrays;
import java.util.List;
import lombok.Getter;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@Getter
public enum WordFlag {
  NONE(0b00000000, "일반"),
  LOANWORD(0b00000001, "외래어"),
  INJEONG(0b00000010, "어인정"),
  SPACED(0b00000100, "띄어쓰기를 해야 하는 어휘"),
  SATURI(0b00001000, "방언"),
  OLD(0b00010000, "옛말"),
  MUNHWA(0b00100000, "문화어"),
  KUNG(0b01000000, "쿵쿵따 전용 단어");

  private int flag;
  private final String flagName;

  WordFlag(int flag, String flagName) {
    this.flag = flag;
    this.flagName = flagName;
  }

  @JsonIgnore
  private final String flagString =  String.format("0b%8s", Integer.toBinaryString(flag)).replace(' ', '0');

  public WordFlag findByFlag(int flag) {
    for (WordFlag wordFlag : WordFlag.values()) {
      if (wordFlag.getFlag() == flag) {
        return wordFlag;
      }
    }
    return null;
  }

  public WordFlag findByName(String flagName) {
    for (WordFlag wordFlag : WordFlag.values()) {
      if (wordFlag.getFlagName().equals(flagName)) {
        return wordFlag;
      }
    }
    return null;
  }

  public static WordFlag formId(int flag) {
    List<WordFlag> findFlags = Arrays.stream(WordFlag.values())
        .filter(it -> it.flag == flag)
        .toList();

    return findFlags.isEmpty() ? null : findFlags.get(0);
  }

  @JsonCreator
  public static WordFlag fromObject(JsonNode node) {
    String identityField = "flag";
    int id;
    if (node.getNodeType() == JsonNodeType.NUMBER) {
      return formId(node.asInt());
    } else {
      if (!node.has(identityField)) {
        throw new IllegalArgumentException("Node does not have the required field: " + identityField);
      }
      id = node.get(identityField).asInt();
    }
    return formId(id);
  }
}
