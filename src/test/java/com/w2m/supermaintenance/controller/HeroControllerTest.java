package com.w2m.supermaintenance.controller;

import com.w2m.supermaintenance.dto.HeroDto;
import com.w2m.supermaintenance.service.HeroService;
import com.w2m.supermaintenance.util.HeroConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class HeroControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HeroService heroService;

    private HeroDto heroDto;

    @BeforeEach
    public void setUp() {
        heroDto = new HeroDto();
        heroDto.setId(1L);
        heroDto.setName("Superman");
        heroDto.setDescription("Man of Steel");
    }

    @Test
    public void testGetAllHeroes() throws Exception {
        when(heroService.findAllHeroes()).thenReturn(Arrays.asList(HeroConverter.convertToEntity(heroDto)));
        mockMvc.perform(get("/heroes"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetHeroById() throws Exception {
        when(heroService.findHeroById(anyLong())).thenReturn(Optional.of(HeroConverter.convertToEntity(heroDto)));
        mockMvc.perform(get("/heroes/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateHero() throws Exception {
        when(heroService.saveHero(any())).thenReturn(HeroConverter.convertToEntity(heroDto));
        mockMvc.perform(post("/heroes")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":1,\"name\":\"Superman\",\"description\":\"Man of Steel\"}"))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateHero() throws Exception {
        when(heroService.updateHero(anyLong(), any())).thenReturn(Optional.of(HeroConverter.convertToEntity(heroDto)));
        mockMvc.perform(put("/heroes/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":1,\"name\":\"Superman\",\"description\":\"Man of Steel\"}"))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteHero() throws Exception {
        when(heroService.deleteHero(anyLong())).thenReturn(true);
        mockMvc.perform(delete("/heroes/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testGetHeroesByName() throws Exception {
        when(heroService.findHeroesByName(anyString())).thenReturn(Arrays.asList(HeroConverter.convertToEntity(heroDto)));
        mockMvc.perform(get("/heroes/name/{nameString}", "perm"))
                .andExpect(status().isOk());
    }

}