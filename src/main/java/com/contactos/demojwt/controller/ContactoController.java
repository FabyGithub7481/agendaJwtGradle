package com.contactos.demojwt.controller;

import com.contactos.demojwt.dto.ContactoDto;
import com.contactos.demojwt.exception.ResourceNotFoundException;
import com.contactos.demojwt.service.IContactoService;
import com.contactos.demojwt.service.impl.ContactoServiceImpl;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/contacto")
@RestController
@Slf4j
public class ContactoController {

  private IContactoService iContactoService;

  public ContactoController(ContactoServiceImpl iContactoService) {
    this.iContactoService = iContactoService;
  }

  @PostMapping
  public ResponseEntity<Void> save(@RequestBody @Validated ContactoDto contactoDto) {
    iContactoService.save(contactoDto);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/{id}")
  public ResponseEntity<ContactoDto> findById(@PathVariable("id") Long id) {
    ContactoDto contacto = iContactoService.findById(id);
    return ResponseEntity.ok(contacto);
  }
  @GetMapping
  public List<ContactoDto> getAllContactos(){
    return iContactoService.getAllContactos();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
    Optional.ofNullable(iContactoService.findById(id)).orElseThrow(() -> {
      log.error("Unable to delete non-existent data！");
      return new ResourceNotFoundException("Unable to delete non-existent data！");
    });
    iContactoService.deleteById(id);
    return ResponseEntity.ok().build();
  }



  @PutMapping("/{id}")
  public ResponseEntity<Void> update(@RequestBody @Validated ContactoDto contactoDto,
      @PathVariable("id") Long id) {
    iContactoService.update(contactoDto, id);
    return ResponseEntity.ok().build();
  }
}