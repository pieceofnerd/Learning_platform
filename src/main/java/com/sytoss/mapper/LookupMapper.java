package com.sytoss.mapper;

import com.sytoss.model.Lookup;
import com.sytoss.web.dto.LookupDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class LookupMapper extends BaseMapper<Lookup, LookupDTO> {

    protected LookupMapper() {
        super(Lookup.class, LookupDTO.class);
    }

    LookupMapper(Class<Lookup> entityClass, Class<LookupDTO> dtoClass) {
        super(entityClass, dtoClass);
    }

    protected LookupMapper(ModelMapper mapper) {
        super(mapper);
    }
}
