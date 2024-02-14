package com.w2m.supermaintenance.service;

import com.w2m.supermaintenance.models.Hero;
import com.w2m.supermaintenance.repository.HeroRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class HeroServiceTest {

    @Mock
    private HeroRepository heroRepository;

    private HeroService heroService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        heroService = new HeroService(heroRepository);
    }

    @Test
    public void testFindAllHeroes() {
        Hero hero = new Hero();
        when(heroRepository.findAll()).thenReturn(Arrays.asList(hero));
        assertEquals(1, heroService.findAllHeroes().size());
        verify(heroRepository, times(1)).findAll();
    }

    @Test
    public void testFindHeroById() {
        Hero hero = new Hero();
        when(heroRepository.findById(1L)).thenReturn(Optional.of(hero));
        assertTrue(heroService.findHeroById(1L).isPresent());
        verify(heroRepository, times(1)).findById(1L);
    }

    @Test
    public void testUpdateHero() {
        Hero hero = new Hero();
        Hero updatedHero = new Hero();
        updatedHero.setName("Updated Name");
        updatedHero.setDescription("Updated Description");
        when(heroRepository.findById(1L)).thenReturn(Optional.of(hero));
        when(heroRepository.save(any(Hero.class))).thenReturn(updatedHero);
        Optional<Hero> result = heroService.updateHero(1L, updatedHero);
        assertTrue(result.isPresent());
        assertEquals("Updated Name", result.get().getName());
        assertEquals("Updated Description", result.get().getDescription());
        verify(heroRepository, times(1)).findById(1L);
        verify(heroRepository, times(1)).save(any(Hero.class));
    }

    @Test
    public void testDeleteHero() {
        when(heroRepository.existsById(1L)).thenReturn(true);
        assertTrue(heroService.deleteHero(1L));
        verify(heroRepository, times(1)).existsById(1L);
        verify(heroRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testFindHeroesByName() {
        Hero hero = new Hero();
        when(heroRepository.findAllByNameContainsIgnoreCase(anyString())).thenReturn(Arrays.asList(hero));
        assertEquals(1, heroService.findHeroesByName("name").size());
        verify(heroRepository, times(1)).findAllByNameContainsIgnoreCase(anyString());
    }
}