package ar.alkemy.com.postcrud.services.commons;

import java.util.List;

public interface IService<T> {

    T create(T entity);

    List<T> findAll();

    boolean delete(Integer id);

    T findById(Integer id);

    T update(T entity);

}
