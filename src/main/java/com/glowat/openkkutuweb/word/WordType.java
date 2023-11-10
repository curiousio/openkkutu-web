package com.glowat.openkkutuweb.word;

import java.util.Arrays;
import java.util.List;
import lombok.Getter;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeType;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@Getter
public enum WordType {
  CLASS_0("0", "품사없음"),
  CLASS_1("1", "명사"),
  CLASS_2("2", "대명사"),
  CLASS_3("3", "수사"),
  CLASS_4("4", "조사"),
  CLASS_5("5", "동사"),
  CLASS_6("6", "형용사"),
  CLASS_7("7", "관형사"),
  CLASS_8("8", "부사"),
  CLASS_9("9", "감탄사"),
  CLASS_10("10", "접사"),
  CLASS_11("11", "의존명사"),
  CLASS_12("12", "보조동사"),
  CLASS_13("13", "보조형용사"),
  CLASS_14("14", "어미"),
  CLASS_15("15", "관형사·명사"),
  CLASS_16("16", "수사·관형사"),
  CLASS_17("17", "명사·부사"),
  CLASS_18("18", "감탄사·명사"),
  CLASS_19("19", "대명사·부사"),
  CLASS_20("20", "대명사·감탄사"),
  CLASS_21("21", "동사·형용사"),
  CLASS_22("22", "관형사·감탄사"),
  CLASS_23("23", "부사·감탄사"),
  CLASS_24("24", "의존명사·조사"),
  CLASS_25("25", "수사·관형사·명사"),
  CLASS_26("26", "대명사·관형사"),
  CLASS_N("n", "명사"),
  CLASS_A("a", "형용사 (a)"),
  CLASS_S("s", "형용사 (s)"),
  CLASS_V("v", "동사"),
  CLASS_R("r", "부사"),
  CLASS_VI("vi", "자동사"),
  CLASS_VT("vt", "타동사"),
  CLASS_P("p", "대명사"),
  CLASS_INT("int", "감탄사"),
  CLASS_PREP("prep", "전치사"),
  CLASS_AUX("aux", "조동사"),
  INJEONG("INJEONG", "어인정");

  private final String typeCode;
  private final String typeName;

  WordType(String typeCode, String typeName) {
    this.typeCode = typeCode;
    this.typeName = typeName;
  }

  public WordType findByCode(String typeCode) {
    for (WordType wordType : WordType.values()) {
      if (wordType.typeCode.equals(typeCode)) {
        return wordType;
      }
    }
    return null;
  }

  /*
   * === 지원하는 JSON 형식 ===
   * 1. {"key": "ID" }
   * 2. {"key": {"typeCode": "ID"}}
   */

  public static WordType formId(String typeCode) {
    List<WordType> findFlags = Arrays.stream(WordType.values())
        .filter(it -> it.typeCode.equals(typeCode))
        .toList();

    return findFlags.isEmpty() ? null : findFlags.get(0);
  }

  @JsonCreator
  public static WordType formObject(JsonNode node) {
    String identityField = "typeCode";
    String id;
    if(node.get(identityField).getNodeType() == JsonNodeType.STRING) {
      id = node.asText();
    } else {
      if (!node.has(identityField)) {
        throw new IllegalArgumentException("Node does not have the required field: " + identityField);
      }
      id = node.get(identityField).asText();
    }
    return formId(id);
  }




}
