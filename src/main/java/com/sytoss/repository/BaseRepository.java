package com.sytoss.repository;


 interface BaseRepository<T> {

    T getById(Long id);

    T create(T saveRecord);

    void update(Long id, T saveRecord);

    void delete(Long id);

}


