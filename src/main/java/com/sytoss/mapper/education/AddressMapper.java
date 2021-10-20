package com.sytoss.mapper.education;

import com.sytoss.mapper.BaseMapper;
import com.sytoss.model.education.Address;
import com.sytoss.web.dto.AddressDTO;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper extends BaseMapper<Address, AddressDTO> {
    AddressMapper() {
        super(Address.class, AddressDTO.class);
    }

    @Override
    public Address toEntity(Object dto) {
        return super.toEntity(dto);
    }

    @Override
    public AddressDTO toDTO(Address entity) {
        return super.toDTO(entity);
    }
}
