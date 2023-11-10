package com.glowat.openkkutuweb.word;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class WordMapper implements RowMapper<Word> {

  @Override
  public Word mapRow(ResultSet rs, int rowNum) throws SQLException {
    Word word = new Word();
    word.setId(rs.getInt("id"));
    word.setWord(rs.getString("word"));
    word.setMeaning(rs.getString("meaning"));
    word.setCreatedAt(rs.getTimestamp("created_at"));
    word.setUpdatedAt(rs.getTimestamp("updated_at"));
    return word;
  }

}
