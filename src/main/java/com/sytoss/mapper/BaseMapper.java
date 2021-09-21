package com.sytoss.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public abstract class BaseMapper<E, D> implements Mapper<E, D> {
    @Autowired
    private ModelMapper mapper;

    private Class<E> entityClass;
    private Class<D> dtoClass;

    BaseMapper(Class<E> entityClass, Class<D> dtoClass) {
        this.entityClass = entityClass;
        this.dtoClass = dtoClass;
    }

    protected BaseMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public E toEntity(Object dto) {
        if (dto == null) return null;
        else return mapper.map(dto, entityClass);

    }

    @Override
    public D toDTO(E entity) {
        if (entity == null) return null;
        else return mapper.map(entity, dtoClass);
    }

    @Override
    public List<E> toListEntity(List<Object> dList) {
        if (dList.isEmpty()) return null;
        List<E> eList = new ArrayList<>();
        for (Object d :
                dList) {
            eList.add(mapper.map(d, entityClass));
        }
        return eList;
    }

    @Override
    public List<D> toListDTO(List<E> eList) {
        if (eList.isEmpty()) return null;
        List<D> dList = new ArrayList<>();
        for (E e :
                eList) {
            dList.add(mapper.map(e, dtoClass));
        }
        return dList;
    }
}
