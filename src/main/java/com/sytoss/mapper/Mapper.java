package com.sytoss.mapper;

import org.modelmapper.ModelMapper;

import java.util.List;

public interface Mapper<E, D> {

    E toEntity(Object dto);

    D toDTO(E entity);

    List<E> toListEntity(List<D> dList);

    List<D> toListDTO(List<E> eList);
}
