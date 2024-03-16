package by.hembar.dao;

import by.hembar.dao.mappers.CardMapper;
import by.hembar.dao.mappers.PatientMapper;
import by.hembar.models.Card;
import by.hembar.models.Patient;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class PatientDAO extends AbstractDAO<Patient>{
    @Override
    public void create(Patient entity) {
        jdbcTemplate.update("insert into patient firstname, lastname, surname, patient.condition, citizenship, num_card values ?,?,?,?,?,?",
                entity.getName(),
                entity.getLastname(),
                entity.getSurname(),
                entity.getCondition(),
                entity.getCitizenship(),
                entity.getNumOfCard()
        );
    }
    @Override
    public void update(int id, Patient entity) {
        jdbcTemplate.update("update patient set firstname=?, lastname=?, surname=?, patient.condition=?, citizenship=?, num_card=? where id =?",
                entity.getName(),
                entity.getLastname(),
                entity.getSurname(),
                entity.getCondition(),
                entity.getCitizenship(),
                entity.getNumOfCard(),
                id
        );
    }
    @Override
    public void delete(int id) {
        jdbcTemplate.update("delete from patient where id =?", id);
    }

    @Override
    public Patient read(int id) {
        return jdbcTemplate.query("select * from patient where id=?", new Object[]{id}, new PatientMapper())
                .stream().findAny().orElse(null);
    }

    @Override
    public List<Patient> readAll() {
        return jdbcTemplate.query("select * from patient",  new PatientMapper());
    }
    public Patient findByName(String firstname, String lastname, String surname){
        List<Patient> patients = jdbcTemplate.query("select * from patient where firstname = '{?}';",
                new Object[]{firstname}, new PatientMapper());
        if(patients.size()>1){
            patients = patients.stream().filter(p->p.getLastname().equals(lastname)).toList();
        }
        if(patients.size()>1){
            patients = patients.stream().filter(p->p.getSurname().equals(surname)).toList();
        }
        return patients.stream().findFirst().get();
    }
}
