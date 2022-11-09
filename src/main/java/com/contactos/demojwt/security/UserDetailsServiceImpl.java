package com.contactos.demojwt.security;

import com.contactos.demojwt.entities.Usuario;
import com.contactos.demojwt.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {


  @Autowired
  private UsuarioRepository usuarioRepository;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    Usuario usuario = usuarioRepository.findOneByEmail(email)
        .orElseThrow(
            () -> new UsernameNotFoundException("El usuario con email " + email + "no existe"));
    // TODO Auto-generated method
    return new UserDetailsImpl(usuario);
  }
}
