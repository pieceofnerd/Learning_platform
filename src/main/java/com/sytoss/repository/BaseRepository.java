package com.sytoss.repository;


interface BaseRepository<T, R> {

    T getById(R id);

    T create(T saveRecord);

    void update(R id, T saveRecord);

    void delete(R id);

}


