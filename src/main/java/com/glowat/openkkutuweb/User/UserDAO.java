package com.glowat.openkkutuweb.User;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.postgresql.util.PGobject;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

@Component
@RequiredArgsConstructor
public class UserDAO {

  private final JdbcTemplate jdbcTemplate;

  private final UserMapper userMapper;

  public User getUser(String id) {
    String sql = "SELECT * FROM users WHERE id = ?";
    List<User> users = jdbcTemplate.query(sql, userMapper, id);
      if (users.isEmpty()) {
          return null;
      }
    return users.get(0);
  }

  public User getUserFromNick(String nickname) {
    String sql = "SELECT * FROM users WHERE \"meanableNick\" = ?";
    List<User> users = jdbcTemplate.query(sql, userMapper, nickname);
      if (users.isEmpty()) {
          return null;
      }
    return users.get(0);
  }

  public List<String> getSimilarityNicks() {
    String sql = "SELECT \"meanableNick\" FROM users";
    return jdbcTemplate.query(sql, SingleColumnRowMapper.newInstance(String.class));
  }

  public void addUser(String id, String nickname, String similarityNick) throws SQLException {
    String sql = "INSERT INTO users (id, nickname, money, kkutu, \"meanableNick\") VALUES (?, ?, ?, ?, ?)";
    jdbcTemplate.update(sql, id, nickname, similarityNick);

    PGobject jsonObject = new PGobject();
    jsonObject.setType("json");
    jsonObject.setValue(
        "{\"score\":0,\"playTime\":0,\"connectDate\":0,\"record\":{\"EKT\":[0,0,0,0],\"ESH\":[0,0,0,0],\"KKT\":[0,0,0,0],\"KSH\":[0,0,0,0],\"CSQ\":[0,0,0,0],\"KCW\":[0,0,0,0],\"KTY\":[0,0,0,0],\"ETY\":[0,0,0,0],\"KAP\":[0,0,0,0],\"HUN\":[0,0,0,0],\"KDA\":[0,0,0,0],\"EDA\":[0,0,0,0],\"KSS\":[0,0,0,0],\"ESS\":[0,0,0,0]}}");

    jdbcTemplate.update(sql, id, nickname, 0, jsonObject, similarityNick);

  }

  public void updateUser(String id, Map<String, Object> values) {
    StringJoiner joiner = new StringJoiner(",");
    for (Map.Entry<String, Object> entry : values.entrySet()) {
      joiner.add(entry.getKey() + "=?");
    }

    String sql = "UPDATE users SET " + joiner.toString() + " WHERE id = ?";

    List<Object> valueString = new ArrayList<>(values.values());
    valueString.add(id);

    jdbcTemplate.update(sql, valueString.toArray());
  }

}


