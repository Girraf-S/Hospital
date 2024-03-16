package by.hembar.dao.mappers;

import by.hembar.models.Patient;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PatientMapper implements RowMapper<Patient> {
    @Override
    public Patient mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Patient(
                rs.getInt("id"),
                rs.getString("firstname"),
                rs.getString("lastname"),
                rs.getString("surname"),
                rs.getString("condition"),
                rs.getString("citizenship"),
                rs.getInt("num_card")
        );
    }
}
