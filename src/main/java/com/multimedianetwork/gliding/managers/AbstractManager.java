/*
 * 
 * 
 */
package com.multimedianetwork.gliding.managers;

import com.multimedianetwork.gliding.dao.AbstractDao;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Camelia Rus
 */
public class AbstractManager<T> implements Serializable{
    
    private Class<T> entityClass;
    AbstractDao<T> dao;
    
    public AbstractManager(Class<T> entityClass) {
        this.entityClass = entityClass;
    }
    
    public void insert(T obj) {
        dao.insert(obj);
    }
    
    public void update(T obj) {
        dao.update(obj);
    }
    
    public void insertOrUpdate(T obj) {
        dao.insertOrUpdate(obj);
    }
    
    public void delete(T obj) {
        dao.delete(obj);
    }
    
    public List<T> getList() {
        return dao.getList();
    }
    
    public T getById(int id) {
        return dao.getById(id);
    }
}
