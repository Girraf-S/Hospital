package by.hembar.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public abstract class AbstractDAO<E> {
    @Autowired
    protected JdbcTemplate jdbcTemplate;
    public abstract void create(E entity);
    public abstract void update(int id, E entity);
    public abstract void delete(int id);
    public abstract E read(int id);
    public abstract List<E> readAll();

}
