package com.glowat.openkkutuweb.Setting;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.ArrayList;

/*
data class AdminSetting(
    val id: String,
    val name: String,
    val team: String,
    val privileges: List<Privilege>
) {
    enum class Privilege {
        CONNECTION_LOG,
        SUSPICION_LOG,
        WORD
    }
}
*/

@Data
@AllArgsConstructor
public class AdminSetting {
  private String id;
  private  String name;
  private String team;
  private List<Privilege> privileges;
}
