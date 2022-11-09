package com.contactos.demojwt.mapper;

import com.contactos.demojwt.dto.UsuarioDto;
import com.contactos.demojwt.entities.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper extends EntityMapper<UsuarioDto, Usuario> {

}