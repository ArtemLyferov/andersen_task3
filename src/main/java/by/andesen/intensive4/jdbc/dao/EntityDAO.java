package by.andesen.intensive4.jdbc.dao;


import by.andesen.intensive4.entities.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Connection;
import java.util.List;

@AllArgsConstructor
public abstract class EntityDAO<T extends Entity> {

    @Getter
    private Connection connection;

    public abstract int create(T entity);

    public abstract List<T> findAll();

    public abstract T findById(int id);

    public abstract int update(T entity);

    public abstract int delete(int id);
}