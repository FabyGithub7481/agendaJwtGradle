package com.contactos.demojwt.controller;

import com.contactos.demojwt.dto.UsuarioDto;
import com.contactos.demojwt.service.impl.UsuarioServiceImpl;
import java.util.Collections;
import org.hamcrest.Matchers;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class UsuarioControllerTest {

  private static final String ENDPOINT_URL = "/api/usuario";
  @InjectMocks
  private UsuarioController usuarioController;
  @Mock
  private UsuarioServiceImpl usuarioServiceImpl;
  private MockMvc mockMvc;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);
    mockMvc = MockMvcBuilders
        .standaloneSetup(usuarioController)
        //.setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
        //.addFilter(CustomFilter::doFilter)
        .build();
  }



  @Test
  public void getById() throws Exception {
    Mockito.when(usuarioServiceImpl.findById(ArgumentMatchers.anyLong()))
        .thenReturn(UsuarioBuilder.getDto());

    mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL + "/1"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content()
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.is(1)));
    Mockito.verify(usuarioServiceImpl, Mockito.times(1)).findById(1L);
    Mockito.verifyNoMoreInteractions(usuarioServiceImpl);
  }

  @Test
  public void save() throws Exception {
    Mockito.when(usuarioServiceImpl.save(ArgumentMatchers.any(UsuarioDto.class)))
        .thenReturn(UsuarioBuilder.getDto());

    mockMvc.perform(
            MockMvcRequestBuilders.post(ENDPOINT_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(CustomUtils.asJsonString(UsuarioBuilder.getDto())))
        .andExpect(MockMvcResultMatchers.status().isCreated());
    Mockito.verify(usuarioServiceImpl, Mockito.times(1)).save(ArgumentMatchers.any(UsuarioDto.class));
    Mockito.verifyNoMoreInteractions(usuarioServiceImpl);
  }

  @Test
  public void update() throws Exception {
    Mockito.when(usuarioServiceImpl.update(ArgumentMatchers.any(), ArgumentMatchers.anyLong()))
        .thenReturn(UsuarioBuilder.getDto());

    mockMvc.perform(
            MockMvcRequestBuilders.put(ENDPOINT_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(CustomUtils.asJsonString(UsuarioBuilder.getDto())))
        .andExpect(MockMvcResultMatchers.status().isOk());
    Mockito.verify(usuarioServiceImpl, Mockito.times(1))
        .update(ArgumentMatchers.any(UsuarioDto.class), ArgumentMatchers.anyLong());
    Mockito.verifyNoMoreInteractions(usuarioServiceImpl);
  }

  @Test
  public void delete() throws Exception {
    Mockito.doNothing().when(usuarioServiceImpl).deleteById(ArgumentMatchers.anyLong());
    mockMvc.perform(
            MockMvcRequestBuilders.delete(ENDPOINT_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(CustomUtils.asJsonString(UsuarioBuilder.getIds())))
        .andExpect(MockMvcResultMatchers.status().isOk());
    Mockito.verify(usuarioServiceImpl, Mockito.times(1)).deleteById(Mockito.anyLong());
    Mockito.verifyNoMoreInteractions(usuarioServiceImpl);
  }
}