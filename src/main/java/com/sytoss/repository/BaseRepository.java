package com.sytoss.repository;


import java.util.List;

interface BaseRepository<E, K> {

    E getById(K id);

    List<E> getAll();

    boolean create(E entity);

    boolean update(K id, E entity);

    boolean delete(K id);

}


