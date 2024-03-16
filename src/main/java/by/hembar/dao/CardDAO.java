package by.hembar.dao;

import by.hembar.dao.mappers.CardMapper;
import by.hembar.models.Card;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class CardDAO extends AbstractDAO<Card>{
    @Override
    public void create(Card entity) {
        jdbcTemplate.update("insert into cards passport_number, birth_date, registration_place values ?,?,?",
                entity.getPassportNumber(),
                entity.getBirthDate(),
                entity.getRegistrationPlace()
        );
    }
    @Override
    public void update(int id, Card entity) {
        jdbcTemplate.update("update cards set passport_number=?, birth_date=?, registration_place=? where id =?",
                entity.getPassportNumber(),
                entity.getBirthDate(),
                entity.getRegistrationPlace(),
                id
        );
    }
    @Override
    public void delete(int id) {
        jdbcTemplate.update("delete from cards where id =?", id);
    }

    @Override
    public Card read(int id) {
        return jdbcTemplate.query("select * from cards where id=?", new Object[]{id}, new CardMapper())
                .stream().findAny().orElse(null);
    }

    @Override
    public List<Card> readAll() {
        return jdbcTemplate.query("select * from cards",  new CardMapper());
    }
}
