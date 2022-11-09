package com.contactos.demojwt.controller;

import com.contactos.demojwt.dto.ContactoDto;
import java.util.Collections;
import java.util.List;

public class ContactoBuilder {

  public static List<String> getIds() {
    return Collections.singletonList("1");
  }

  public static Object getDto() {
    ContactoDto dto = new ContactoDto();
    dto.setId(1L);
    return dto;
  }
}