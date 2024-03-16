package by.hembar.dao.mappers;

import by.hembar.models.Treatment;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TreatmentMapper implements RowMapper<Treatment> {
    @Override
    public Treatment mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Treatment(
                rs.getInt("id"),
                rs.getString("medicines"),
                rs.getString("supplies"),
                rs.getString("procedures"),
                rs.getInt("num_history")
        );
    }
}
