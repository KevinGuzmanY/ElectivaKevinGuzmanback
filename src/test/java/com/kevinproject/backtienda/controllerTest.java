package com.kevinproject.backtienda;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kevinproject.backtienda.controller.controller;
import com.kevinproject.backtienda.model.producto;
import com.kevinproject.backtienda.service.productoServ;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@WebMvcTest(controller.class)
class controllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private productoServ productoServ;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void productoList() throws Exception {
        List<producto> productList = Arrays.asList(new producto(), new producto());
        when(productoServ.listarTodo()).thenReturn(productList);

        mockMvc.perform(MockMvcRequestBuilders.get("/tienda/tabla"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0]").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[1]").exists());

        verify(productoServ, times(1)).listarTodo();
        verifyNoMoreInteractions(productoServ);
    }

    @Test
    void save() throws Exception {
        producto product = new producto();
        product.setId(1);
        product.setNombre("Test Product");

        when(productoServ.guardar(any(producto.class))).thenReturn(product);

        mockMvc.perform(MockMvcRequestBuilders.post("/tienda/guardar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(product)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nombre").value("Test Product"));

        verify(productoServ, times(1)).guardar(any(producto.class));
        verifyNoMoreInteractions(productoServ);
    }

    @Test
    void getbyid() throws Exception {
        producto product = new producto();
        product.setId(1);
        product.setNombre("Test Product");

        when(productoServ.listarPorId(1)).thenReturn(Optional.of(product));

        mockMvc.perform(MockMvcRequestBuilders.get("/tienda/getbyid/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nombre").value("Test Product"));

        verify(productoServ, times(1)).listarPorId(1);
        verifyNoMoreInteractions(productoServ);
    }

    @Test
    void actualizar() throws Exception {
        producto product = new producto();
        product.setId(1);
        product.setNombre("Test Product");

        when(productoServ.edit(any(producto.class))).thenReturn(product);

        mockMvc.perform(MockMvcRequestBuilders.put("/tienda/updateProduct")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(product)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nombre").value("Test Product"));

        verify(productoServ, times(1)).edit(any(producto.class));
        verifyNoMoreInteractions(productoServ);
    }

    @Test
    void deletebyid() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/tienda/delete/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(productoServ, times(1)).borrarPorId(1);
        verifyNoMoreInteractions(productoServ);
    }

    @Test
    void balancetotal() throws Exception {
        when(productoServ.balanceTienda()).thenReturn(100);

        mockMvc.perform(MockMvcRequestBuilders.get("/tienda/balancetotal"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("100"));

        verify(productoServ, times(1)).balanceTienda();
        verifyNoMoreInteractions(productoServ);
    }

    @Test
    void supplyProduct() throws Exception {
        producto product = new producto();
        product.setId(1);

        mockMvc.perform(MockMvcRequestBuilders.put("/tienda/supply/10/value20.0")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(product)))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(productoServ, times(1)).abastecerProducto(1, 10, 20.0);
        verifyNoMoreInteractions(productoServ);
    }

    @Test
    void getStock() throws Exception {
        when(productoServ.stock()).thenReturn(50);

        mockMvc.perform(MockMvcRequestBuilders.get("/tienda/stock"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("50"));

        verify(productoServ, times(1)).stock();
        verifyNoMoreInteractions(productoServ);
    }

    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
