package com.contactos.demojwt.service;

import com.contactos.demojwt.dto.ContactoDto;
import java.util.List;

public interface IContactoService {

  ContactoDto save(ContactoDto contactoDto);

  void deleteById(Long id);

  ContactoDto findById(Long id);

  ContactoDto update(ContactoDto contactoDto, Long id);

  List<ContactoDto> getAllContactos();
}
