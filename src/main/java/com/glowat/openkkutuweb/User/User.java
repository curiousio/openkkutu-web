package com.glowat.openkkutuweb.User;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;

/*
    val id: String,
    val nickname: String?,
     val money: Long,
            val kkutu: JsonNode,
            val lastLogin: Long?,
            val box: JsonNode,
            val equip: JsonNode,
            val exordial: String?,
            val black: String?,
            val server: String?,
            val password: String?,
            val friends: JsonNode


 */
@Data
public class User {

  private String id;
  private String nickname;
  private Long money;
  private JsonNode kkutu;
  private Long lastLogin;
  private JsonNode box;
  private JsonNode equip;
  private String exordial;
  private String black;
  private String server;
  private String password;
  private JsonNode friends;

}
