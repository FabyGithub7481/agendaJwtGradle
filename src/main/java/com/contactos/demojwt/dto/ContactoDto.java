package com.contactos.demojwt.dto;

import java.time.LocalDate;

public class ContactoDto  {

  private Long id;
  private String nombre;
  private LocalDate fechaNacimiento;
  private String cellular;
  private String email;

  public ContactoDto() {
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getId() {
    return this.id;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getNombre() {
    return this.nombre;
  }

  public void setFechaNacimiento(java.time.LocalDate fechaNacimiento) {
    this.fechaNacimiento = fechaNacimiento;
  }

  public java.time.LocalDate getFechaNacimiento() {
    return this.fechaNacimiento;
  }

  public void setCellular(String cellular) {
    this.cellular = cellular;
  }

  public String getCellular() {
    return this.cellular;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getEmail() {
    return this.email;
  }
}