package controllers;

import java.util.List;

/**
 *
 * @author CarlosMacaneta
 */
public interface CRUD {   
   
    public void create(Object object);
    public List<Object> findAll();
    public Object findById(Integer id);
    public void edit(Object object);
    public void delete(int id);
}
