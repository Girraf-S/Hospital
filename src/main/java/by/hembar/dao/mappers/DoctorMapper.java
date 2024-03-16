package by.hembar.dao.mappers;

import by.hembar.models.Doctor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DoctorMapper implements RowMapper<Doctor> {
    @Override
    public Doctor mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Doctor(
                rs.getInt("id"),
                rs.getString("firstname"),
                rs.getString("lastname"),
                rs.getString("surname"),
                rs.getString("passport_number"),
                rs.getString("category")
        );
    }
}
