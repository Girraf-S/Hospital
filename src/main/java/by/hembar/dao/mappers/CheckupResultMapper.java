package by.hembar.dao.mappers;

import by.hembar.models.Card;
import by.hembar.models.CheckupResult;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CheckupResultMapper implements RowMapper<CheckupResult> {
    @Override
    public CheckupResult mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new CheckupResult(
                rs.getInt("num_checkup"),
                rs.getInt("num_treatment")
        );
    }
}
