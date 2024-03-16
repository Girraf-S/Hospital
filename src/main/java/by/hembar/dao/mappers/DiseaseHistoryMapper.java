package by.hembar.dao.mappers;

import by.hembar.models.DiseaseHistory;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DiseaseHistoryMapper implements RowMapper<DiseaseHistory> {
    @Override
    public DiseaseHistory mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new DiseaseHistory(
                rs.getInt("id"),
                rs.getString("diagnosis"),
                rs.getString("conclusion"),
                rs.getString("treatment_period"),
                rs.getInt("num_card"),
                rs.getInt("num_doctor")
        );
    }
}
