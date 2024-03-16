package by.hembar.dao;

import by.hembar.dao.mappers.CardMapper;
import by.hembar.dao.mappers.TreatmentMapper;
import by.hembar.models.Card;
import by.hembar.models.Treatment;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class TreatmentDAO extends AbstractDAO<Treatment>{
    @Override
    public void create(Treatment entity) {
        jdbcTemplate.update("insert into treatment medicines, supplies, procedures, num_history values ?,?,?,?,?",
                entity.getMedicines(),
                entity.getSupplies(),
                entity.getProcedures(),
                entity.getNumOfHistory()
        );
    }
    @Override
    public void update(int id, Treatment entity) {
        jdbcTemplate.update("update treatment set medicines=?, supplies=?, procedures=?, num_history=? where id =?",
                entity.getMedicines(),
                entity.getSupplies(),
                entity.getProcedures(),
                entity.getNumOfHistory(),
                id
        );
    }
    @Override
    public void delete(int id) {
        jdbcTemplate.update("delete from treatment where id =?", id);
    }

    @Override
    public Treatment read(int id) {
        return jdbcTemplate.query("select * from treatment where id=?", new Object[]{id}, new TreatmentMapper())
                .stream().findAny().orElse(null);
    }

    @Override
    public List<Treatment> readAll() {
        return jdbcTemplate.query("select * from treatment",  new TreatmentMapper());
    }
    public Treatment getLastTreatment(int id){
        return jdbcTemplate.query("select * from treatment t\n" +
                        "    left join checkup_results cr on t.id = cr.num_treatment\n" +
                        "    left join checkup c on cr.num_checkup = c.id where num_patient =?", new Object[]{id}, new TreatmentMapper())
                .stream().findAny().orElse(null);
    }
}
