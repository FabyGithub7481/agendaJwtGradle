package com.contactos.demojwt.service.impl;

import com.contactos.demojwt.dto.UsuarioDto;
import com.contactos.demojwt.entities.Usuario;
import com.contactos.demojwt.mapper.UsuarioMapper;
import com.contactos.demojwt.repository.UsuarioRepository;
import com.contactos.demojwt.service.IUsuarioService;
import javax.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Transactional
public class UsuarioServiceImpl implements IUsuarioService {

  @Autowired
  private UsuarioRepository repository;
  @Autowired
  private UsuarioMapper usuarioMapper;

  public UsuarioServiceImpl(UsuarioRepository repository, UsuarioMapper usuarioMapper) {
    this.repository = repository;
    this.usuarioMapper = usuarioMapper;
  }

  @Override
  public UsuarioDto save(UsuarioDto usuarioDto) {
    Usuario entity = usuarioMapper.toEntity(usuarioDto);
    return usuarioMapper.toDto(repository.save(entity));
  }

  @Override
  public void deleteById(Long id) {
    repository.deleteById(id);
  }

  @Override
  public UsuarioDto findById(Long id) {
    return usuarioMapper.toDto(repository.findById(id).orElseThrow());
  }


  @Override
  public UsuarioDto update(UsuarioDto usuarioDto, Long id) {
    UsuarioDto data = findById(id);
    Usuario entity = usuarioMapper.toEntity(usuarioDto);
    BeanUtils.copyProperties(data, entity);
    return save(usuarioMapper.toDto(entity));
  }
}