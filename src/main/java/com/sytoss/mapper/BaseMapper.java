package com.sytoss.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public abstract class BaseMapper<E, D> implements Mapper<E, D> {

    private final ModelMapper mapper;

    private Class<E> entityClass;
    private Class<D> dtoClass;

    @Autowired
    BaseMapper(Class<E> entityClass, Class<D> dtoClass, ModelMapper modelMapper) {
        this.entityClass = entityClass;
        this.dtoClass = dtoClass;
        this.mapper = modelMapper;
    }

    protected BaseMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public E toEntity(D dto) {

        if (dto == null) return null;
        else return mapper.map(dto, entityClass);

    }

    @Override
    public D toDTO(E entity) {
        if (entity == null) return null;
        else return mapper.map(entity, dtoClass);
    }
}
