package com.contactos.demojwt.service.impl;

import static com.contactos.demojwt.exception.ResourceNotFoundException.requireNotEmpty;

import com.contactos.demojwt.dto.ContactoDto;
import com.contactos.demojwt.entities.Contacto;
import com.contactos.demojwt.exception.ResourceNotFoundException;
import com.contactos.demojwt.mapper.ContactoMapper;
import com.contactos.demojwt.repository.ContactoRepository;
import com.contactos.demojwt.service.IContactoService;
import java.util.List;
import javax.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Transactional
public class ContactoServiceImpl implements IContactoService {

  @Autowired
  private ContactoRepository repository;
  @Autowired
  private ContactoMapper contactoMapper;

  public ContactoServiceImpl(ContactoRepository repository, ContactoMapper contactoMapper) {
    this.repository = repository;
    this.contactoMapper = contactoMapper;
  }

  @Override
  public ContactoDto save(ContactoDto contactoDto) {
    Contacto entity = contactoMapper.toEntity(contactoDto);
    return contactoMapper.toDto(repository.save(entity));
  }

  @Override
  public void deleteById(Long id) {
    repository.deleteById(id);
  }

  @Override
  public ContactoDto findById(Long id) {
    return contactoMapper.toDto(
        repository.findById(id).orElseThrow(ResourceNotFoundException::new));
  }


  public Page<ContactoDto> findByCondition(ContactoDto contactoDto, Pageable pageable) {
    Page<Contacto> entityPage = repository.findAll(pageable);
    List<Contacto> entities = entityPage.getContent();
    return new PageImpl<>(contactoMapper.toDto(entities), pageable, entityPage.getTotalElements());
  }

  @Override
  public ContactoDto update(ContactoDto contactoDto, Long id) {
    ContactoDto data = findById(id);
    Contacto entity = contactoMapper.toEntity(contactoDto);
    BeanUtils.copyProperties(data, entity);
    return save(contactoMapper.toDto(entity));
  }

  @Override
  public List<ContactoDto> getAllContactos() {
    return requireNotEmpty(contactoMapper.toDto(repository.findAll()));
  }
}