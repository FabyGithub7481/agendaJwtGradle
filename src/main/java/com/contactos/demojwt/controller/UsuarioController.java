package com.contactos.demojwt.controller;

import com.contactos.demojwt.dto.UsuarioDto;
import com.contactos.demojwt.exception.ResourceNotFoundException;
import com.contactos.demojwt.service.IUsuarioService;
import com.contactos.demojwt.service.impl.UsuarioServiceImpl;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/usuario")
@RestController
@Slf4j
public class UsuarioController {

  @Autowired
  private IUsuarioService iUsuarioService;

  public UsuarioController(UsuarioServiceImpl iUsuarioService) {
    this.iUsuarioService = iUsuarioService;
  }

  @PostMapping
  public ResponseEntity<Void> save(@RequestBody @Validated UsuarioDto usuarioDto) {
    iUsuarioService.save(usuarioDto);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/{id}")
  public ResponseEntity<UsuarioDto> findById(@PathVariable("id") Long id) {
    UsuarioDto usuario = iUsuarioService.findById(id);
    return ResponseEntity.ok(usuario);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
    Optional.ofNullable(iUsuarioService.findById(id)).orElseThrow(() -> {
      log.error("Unable to delete non-existent data！");
      return new ResourceNotFoundException("Unable to delete non-existent data！");
    });
    iUsuarioService.deleteById(id);
    return ResponseEntity.ok().build();
  }



  @PutMapping("/{id}")
  public ResponseEntity<Void> update(@RequestBody @Validated UsuarioDto usuarioDto,
      @PathVariable("id") Long id) {
    iUsuarioService.update(usuarioDto, id);
    return ResponseEntity.ok().build();
  }
}