package by.hembar.dao.mappers;

import by.hembar.models.Card;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CardMapper implements RowMapper<Card> {
    @Override
    public Card mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Card(
                rs.getInt("id"),
                rs.getString("passport_number"),
                rs.getString("registration_place"),
                rs.getString("birth_date")
        );
    }
}
