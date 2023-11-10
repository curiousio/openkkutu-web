package com.glowat.openkkutuweb.User;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
@RequiredArgsConstructor
public class UserMapper implements RowMapper<User> {

  private final ObjectMapper objectMapper;

  @Override
  public User mapRow(ResultSet rs, int rowNum) throws SQLException {
    User user = new User();
    user.setId(rs.getString("id"));
    user.setNickname(rs.getString("nickname"));
    user.setMoney(rs.getLong("money"));
    try {
      user.setKkutu(objectMapper.readTree(rs.getString("kkutu")));
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
    user.setLastLogin(rs.getLong("lastLogin"));
    try {
      user.setBox(objectMapper.readTree(rs.getString("box")));
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
    try {
      user.setEquip(objectMapper.readTree(rs.getString("equip")));
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
    user.setExordial(rs.getString("exordial"));
    user.setBlack(rs.getString("black"));
    user.setServer(rs.getString("server"));
    user.setPassword(rs.getString("password"));
    try {
      user.setFriends(objectMapper.readTree(rs.getString("friends")));
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
    return user;
  }


}
