package com.contactos.demojwt.security;

import static lombok.AccessLevel.PRIVATE;

import lombok.*;
import lombok.experimental.FieldDefaults;


@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = PRIVATE)
public class AuthCredentiales {
  String email;
  String password;

}
