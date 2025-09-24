package com.example.hippocardioo.Services.DAO;
import java.util.List;

public interface idao <T, ID> {

    List<T> getAll();

    T getById(ID id);

    T create(T entity);

    T update(T entity);

    void delete(ID id);


}
