package com.contactos.demojwt.entities;

import static lombok.AccessLevel.PRIVATE;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = PRIVATE)
@Entity
@Table(name = "contactos")
public class Contacto {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "idcontacto")
  Long id;
  String nombre;
  @Column(name = "fechanac")
  LocalDate fechaNacimiento;
  String cellular;
  String email;


}
