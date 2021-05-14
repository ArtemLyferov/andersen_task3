package by.andesen.intensive4.service;

import by.andesen.intensive4.entities.Entity;
import by.andesen.intensive4.jdbc.dao.EntityDAO;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class EntityService<T extends Entity> {

    private EntityDAO<T> entityDAO;

    public boolean create(T entity) {
        return entityDAO.create(entity) > 0;
    }

    public List<T> findAll() {
        return entityDAO.findAll();
    }

    public T findById(int id) {
        return entityDAO.findById(id);
    }

    public boolean update(T entity) {
        return entityDAO.update(entity) > 0;
    }

    public boolean delete(int id) {
        return entityDAO.delete(id) > 0;
    }
}
