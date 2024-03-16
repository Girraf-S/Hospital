package by.hembar.dao;

import by.hembar.dao.mappers.DiseaseHistoryMapper;
import by.hembar.dao.mappers.PatientMapper;
import by.hembar.models.DiseaseHistory;
import by.hembar.models.Patient;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class DiseaseHistoryDAO extends AbstractDAO<DiseaseHistory> {
    @Override
    public void create(DiseaseHistory entity) {
        jdbcTemplate.update("insert into disease_history diagnosis, conclusion, treatment_period, num_card, num_doctor values ?,?,?,?,?",
                entity.getDiagnosis(),
                entity.getConclusion(),
                entity.getTreatmentPeriod(),
                entity.getNumOfCard(),
                entity.getNumOfDoctor()
        );
    }
    @Override
    public void update(int id, DiseaseHistory entity) {
        jdbcTemplate.update("update disease_history set diagnosis=?, conclusion=?, treatment_period=?, num_card=?, num_doctor=? where id =?",
                entity.getDiagnosis(),
                entity.getConclusion(),
                entity.getTreatmentPeriod(),
                entity.getNumOfCard(),
                entity.getNumOfDoctor(),
                id
        );
    }
    @Override
    public void delete(int id) {
        jdbcTemplate.update("delete from disease_history where id =?", id);
    }

    @Override
    public DiseaseHistory read(int id) {
        return jdbcTemplate.query("select * from disease_history where id=?", new Object[]{id}, new DiseaseHistoryMapper())
                .stream().findAny().orElse(null);
    }

    @Override
    public List<DiseaseHistory> readAll() {
        return jdbcTemplate.query("select * from disease_history",  new DiseaseHistoryMapper());
    }
    public List<DiseaseHistory> readAllHistByDoctorID(int id){
        return jdbcTemplate.query("select * from disease_history where num_doctor =?;",
                new Object[]{id},  new DiseaseHistoryMapper());
    }
    public List<DiseaseHistory> readAllHistByCardID(int id){
        return jdbcTemplate.query("select * from disease_history where num_card =?;",
                new Object[]{id},  new DiseaseHistoryMapper());
    }
}
