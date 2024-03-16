package by.hembar.dao;

import by.hembar.dao.mappers.DoctorMapper;
import by.hembar.dao.mappers.PatientMapper;
import by.hembar.models.Doctor;
import by.hembar.models.Patient;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class DoctorDAO extends AbstractDAO<Doctor> {
    @Override
    public void create(Doctor entity) {
        jdbcTemplate.update("insert into doctors firstname, lastname, surname,passport_number, categoty values ?,?,?",
                entity.getName(),
                entity.getLastname(),
                entity.getSurname(),
                entity.getPassportNumber(),
                entity.getCategory()
        );
    }
    @Override
    public void update(int id, Doctor entity) {
        jdbcTemplate.update("update doctors set firstname=?, lastname=?, surname=?, passport_number=?, category=? where id =?",
                entity.getName(),
                entity.getLastname(),
                entity.getSurname(),
                entity.getPassportNumber(),
                entity.getCategory(),
                id
        );
    }
    @Override
    public void delete(int id) {
        jdbcTemplate.update("delete from doctors where id =?", id);
    }

    @Override
    public Doctor read(int id) {
        return jdbcTemplate.query("select * from doctors where id=?", new Object[]{id}, new DoctorMapper())
                .stream().findAny().orElse(null);
    }

    @Override
    public List<Doctor> readAll() {
        return jdbcTemplate.query("select * from doctors",  new DoctorMapper());
    }
    public Doctor findByName(String firstname, String lastname, String surname){
        List<Doctor> doctors = jdbcTemplate.query("select * from doctors where firstname = '{?}';",
                new Object[]{firstname}, new DoctorMapper());
        if(doctors.size()>1){
            doctors = doctors.stream().filter(p->p.getLastname().equals(lastname)).toList();
        }
        if(doctors.size()>1){
            doctors = doctors.stream().filter(p->p.getSurname().equals(surname)).toList();
        }
        return doctors.stream().findFirst().get();
    }
}
