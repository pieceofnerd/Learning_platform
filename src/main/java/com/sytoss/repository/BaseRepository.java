package com.sytoss.repository;


 interface BaseRepository<T, R> {

    T getById(Long id);

    T create(R saveRecord);

    void update(Long id, R saveRecord);

    void delete(Long id);

}


