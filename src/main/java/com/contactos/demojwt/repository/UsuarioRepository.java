package com.contactos.demojwt.repository;

import com.contactos.demojwt.entities.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

  Optional<Usuario> findOneByEmail(String email);

}