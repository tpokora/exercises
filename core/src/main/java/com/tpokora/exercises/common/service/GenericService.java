package com.tpokora.exercises.common.service;

import java.util.List;

/**
 * Created by pokor on 21.06.2017.
 */
public interface GenericService<T> {

    public List<T> getAll();
    public T getById(Integer id);
    public T createOrUpdate(T t);
    public void delete(Integer id);
}
