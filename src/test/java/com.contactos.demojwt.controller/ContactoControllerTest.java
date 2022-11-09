package com.contactos.demojwt.controller;

import com.contactos.demojwt.dto.ContactoDto;
import com.contactos.demojwt.service.impl.ContactoServiceImpl;
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
public class ContactoControllerTest {

  private static final String ENDPOINT_URL = "/api/contacto";
  @InjectMocks
  private ContactoController contactoController;
  @Mock
  private ContactoServiceImpl contactoServiceImpl;
  private MockMvc mockMvc;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);
    mockMvc = MockMvcBuilders
        .standaloneSetup(contactoController)
        //.setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
        //.addFilter(CustomFilter::doFilter)
        .build();
  }



  @Test
  public void getById() throws Exception {
    Mockito.when(contactoServiceImpl.findById(ArgumentMatchers.anyLong()))
        .thenReturn((ContactoDto) ContactoBuilder.getDto());

    mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL + "/1"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content()
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.is(1)));
    Mockito.verify(contactoServiceImpl, Mockito.times(1)).findById(1L);
    Mockito.verifyNoMoreInteractions(contactoServiceImpl);
  }

  @Test
  public void save() throws Exception {
    Mockito.when(contactoServiceImpl.save(ArgumentMatchers.any(ContactoDto.class)))
        .thenReturn((ContactoDto) ContactoBuilder.getDto());

    mockMvc.perform(
            MockMvcRequestBuilders.post(ENDPOINT_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(CustomUtils.asJsonString(ContactoBuilder.getDto())))
        .andExpect(MockMvcResultMatchers.status().isCreated());
    Mockito.verify(contactoServiceImpl, Mockito.times(1)).save(ArgumentMatchers.any(ContactoDto.class));
    Mockito.verifyNoMoreInteractions(contactoServiceImpl);
  }

  @Test
  public void update() throws Exception {
    Mockito.when(contactoServiceImpl.update(ArgumentMatchers.any(), ArgumentMatchers.anyLong()))
        .thenReturn((ContactoDto) ContactoBuilder.getDto());

    mockMvc.perform(
            MockMvcRequestBuilders.put(ENDPOINT_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(CustomUtils.asJsonString(ContactoBuilder.getDto())))
        .andExpect(MockMvcResultMatchers.status().isOk());
    Mockito.verify(contactoServiceImpl, Mockito.times(1))
        .update(ArgumentMatchers.any(ContactoDto.class), ArgumentMatchers.anyLong());
    Mockito.verifyNoMoreInteractions(contactoServiceImpl);
  }

  @Test
  public void delete() throws Exception {
    Mockito.doNothing().when(contactoServiceImpl).deleteById(ArgumentMatchers.anyLong());
    mockMvc.perform(
            MockMvcRequestBuilders.delete(ENDPOINT_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(CustomUtils.asJsonString(ContactoBuilder.getIds())))
        .andExpect(MockMvcResultMatchers.status().isOk());
    Mockito.verify(contactoServiceImpl, Mockito.times(1)).deleteById(Mockito.anyLong());
    Mockito.verifyNoMoreInteractions(contactoServiceImpl);
  }
}