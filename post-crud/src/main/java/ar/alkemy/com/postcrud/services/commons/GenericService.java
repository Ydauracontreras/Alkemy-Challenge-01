package ar.alkemy.com.postcrud.services.commons;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

public class GenericService<T> implements IService<T> {

    @Autowired
    protected JpaRepository<T, Integer> repository;

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    public boolean delete(Integer id) {
        repository.deleteById(id);
        return true;
    }

    @Override
    public T findById(Integer id) {
        Optional<T> t = repository.findById(id);
        return (t.isPresent() ? t.get() : null);
    }

    @Override
    public T create(T entity) {
        repository.save(entity);
        return entity;
    }

    @Override
    public T update(T entity) {
        repository.save(entity);
        return entity;
    }

}