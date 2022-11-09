package com.contactos.demojwt.controller;

import com.contactos.demojwt.dto.UsuarioDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Collections;
import java.util.List;

public class UsuarioBuilder {

  public static List<String> getIds() {
    return Collections.singletonList("1");
  }

  public static UsuarioDto getDto() {
    UsuarioDto dto = new UsuarioDto();
    dto.setId(1L);
    return dto;
  }
}