package com.contactos.demojwt.mapper;

import com.contactos.demojwt.dto.ContactoDto;
import com.contactos.demojwt.entities.Contacto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContactoMapper extends EntityMapper<ContactoDto, Contacto> {

}