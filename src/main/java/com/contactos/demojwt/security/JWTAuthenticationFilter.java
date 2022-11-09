package com.contactos.demojwt.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Collections;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request,
      HttpServletResponse response) throws AuthenticationException {
    AuthCredentiales authCredentiales = new AuthCredentiales();

    try {
      authCredentiales = new ObjectMapper().readValue(request.getReader(), AuthCredentiales.class);
    } catch (IOException e) {

    }
    UsernamePasswordAuthenticationToken userNamePath = new UsernamePasswordAuthenticationToken(
        authCredentiales.getEmail(),
        authCredentiales.getPassword(),
        Collections.emptyList()
    );

    return getAuthenticationManager().authenticate(userNamePath);
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
      FilterChain chain, Authentication authResult) throws IOException, ServletException {
    UserDetailsImpl userDetails= (UserDetailsImpl)authResult.getPrincipal();
    String token= TokenUtils.createToken(userDetails.getNombre(),userDetails.getUsername());
    response.addHeader("Authorization","Bearer " + token);
    response.getWriter().flush();

    super.successfulAuthentication(request, response, chain, authResult);
  }
}
