package com.sytoss.mapper;

import org.modelmapper.ModelMapper;

public interface Mapper<E, D> {

    E toEntity(D dto);

    D toDTO(E entity);

}
