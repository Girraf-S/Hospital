package by.hembar.dao.mappers;

import by.hembar.models.Checkup;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CheckupMapper implements RowMapper<Checkup>{
    @Override
    public Checkup mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Checkup(
                rs.getInt("id"),
                rs.getBoolean("routine"),
                rs.getString("checkup_date"),
                rs.getString("checkup_time"),
                rs.getInt("num_doctor"),
                rs.getInt("num_patient")
        );
    }
}
