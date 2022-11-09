package com.contactos.demojwt.service;

import com.contactos.demojwt.dto.UsuarioDto;

public interface IUsuarioService {

  UsuarioDto save(UsuarioDto usuarioDto);

  void deleteById(Long id);

  UsuarioDto findById(Long id);

  UsuarioDto update(UsuarioDto usuarioDto, Long id);
}
