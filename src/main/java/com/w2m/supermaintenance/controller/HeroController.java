package com.w2m.supermaintenance.controller;

import com.w2m.supermaintenance.annotation.LogExecutionTime;
import com.w2m.supermaintenance.dto.HeroDto;
import com.w2m.supermaintenance.exception.HeroNotFoundException;
import com.w2m.supermaintenance.service.HeroService;
import com.w2m.supermaintenance.util.HeroConverter;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/heroes")
public class HeroController {

    private final HeroService heroService;

    public HeroController(HeroService heroService) {
        this.heroService = heroService;
    }

    @GetMapping
    @Cacheable("heroes")
    @LogExecutionTime
    public ResponseEntity<List<HeroDto>> getAllHeroes() {
        List<HeroDto> heroDtos = heroService.findAllHeroes().stream()
                .map(HeroConverter::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(heroDtos);
    }

    @GetMapping("/{id}")
    @Cacheable(value = "heroe")
    @LogExecutionTime
    public ResponseEntity<HeroDto> getHeroById(@PathVariable @NonNull Long id) {
        HeroDto heroDto = heroService.findHeroById(id)
                .map(HeroConverter::convertToDto)
                .orElseThrow(() -> new HeroNotFoundException("Hero not found"));
        return ResponseEntity.ok(heroDto);
    }

    @PutMapping("/{id}")
    @Caching(evict = {
            @CacheEvict(value="heroes", allEntries=true),
            @CacheEvict(value="heroesByName", allEntries=true),
            @CacheEvict(value="heroe", allEntries=true)})
    @LogExecutionTime
    public ResponseEntity<HeroDto> updateHero(@PathVariable @NonNull Long id, @RequestBody HeroDto heroDto) {
        HeroDto updatedHeroDto = heroService.updateHero(id, HeroConverter.convertToEntity(heroDto))
                .map(HeroConverter::convertToDto)
                .orElseThrow(() -> new HeroNotFoundException("Hero not found"));
        return ResponseEntity.ok(updatedHeroDto);
    }

    @DeleteMapping("/{id}")
    @Caching(evict = {
            @CacheEvict(value="heroes", allEntries=true),
            @CacheEvict(value="heroesByName", allEntries=true),
            @CacheEvict(value="heroe", allEntries=true)})
    @LogExecutionTime
    public ResponseEntity<Void> deleteHero(@PathVariable @NonNull Long id) {
        heroService.deleteHero(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/name/{nameString}")
    @Cacheable(value = "heroesByName")
    @LogExecutionTime
    public ResponseEntity<List<HeroDto>> getHeroesByName(@PathVariable String nameString) {
        List<HeroDto> heroDtos = heroService.findHeroesByName(nameString).stream()
                .map(HeroConverter::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(heroDtos);
    }
}