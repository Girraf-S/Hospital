package by.hembar.dao;

import by.hembar.dao.mappers.CardMapper;
import by.hembar.dao.mappers.CheckupMapper;
import by.hembar.models.Card;
import by.hembar.models.Checkup;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class CheckupDAO extends AbstractDAO<Checkup>{
    @Override
    public void create(Checkup entity) {
        jdbcTemplate.update("insert into checkup checkup.routine, checkup_date, checkup_time, num_doctor, num_patient values ?,?,?,?,?",
                entity.isRoutine(),
                entity.getDate(),
                entity.getTime(),
                entity.getNumOfDoctor(),
                entity.getNumOfPatient()
        );
    }
    @Override
    public void update(int id, Checkup entity) {
        jdbcTemplate.update("update checkup set checkup.routine=?, checkup_date=?, checkup_time=?, num_doctor=?, num_patient=? where id =?",
                entity.isRoutine(),
                entity.getDate(),
                entity.getTime(),
                entity.getNumOfDoctor(),
                entity.getNumOfPatient(),
                id
        );
    }
    @Override
    public void delete(int id) {
        jdbcTemplate.update("delete from checkup where id =?", id);
    }

    @Override
    public Checkup read(int id) {
        return jdbcTemplate.query("select * from checkup where id=?", new Object[]{id}, new CheckupMapper())
                .stream().findAny().orElse(null);
    }

    @Override
    public List<Checkup> readAll() {
        return jdbcTemplate.query("select * from checkup",  new CheckupMapper());
    }
    public List<Checkup> findAllByPatientID(int id) {
        return jdbcTemplate.query("select * from checkup where id =?", new Object[]{id},  new CheckupMapper());
    }

}
