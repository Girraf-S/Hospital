package by.hembar.dao;

import by.hembar.dao.mappers.CheckupMapper;
import by.hembar.dao.mappers.CheckupResultMapper;
import by.hembar.models.Checkup;
import by.hembar.models.CheckupResult;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class CheckUpResultDAO extends AbstractDAO<CheckupResult>{
    @Override
    public void create(CheckupResult entity) {
        jdbcTemplate.update("insert into checkup_results num_checkup, num_treatment values ?,?",
                entity.getNum_checkup(),
                entity.getNum_treatment()
        );
    }
    @Override
    public void update(int id, CheckupResult entity) {
        jdbcTemplate.update("update checkup_results num_checkup=?, num_treatment=? where id =?",
                entity.getNum_checkup(),
                entity.getNum_treatment(),
                id
        );
    }
    @Override
    public void delete(int id) {
        jdbcTemplate.update("delete from checkup_results where id =?", id);
    }

    @Override
    public CheckupResult read(int id) {
        return jdbcTemplate.query("select * from checkup_results where id=?", new Object[]{id}, new CheckupResultMapper())
                .stream().findAny().orElse(null);
    }

    @Override
    public List<CheckupResult> readAll() {
        return jdbcTemplate.query("select * from checkup_results",  new CheckupResultMapper());
    }
}
