package com.w2m.supermaintenance.controller;

import com.w2m.supermaintenance.annotation.LogExecutionTime;
import com.w2m.supermaintenance.dto.HeroDto;
import com.w2m.supermaintenance.exception.HeroNotFoundException;
import com.w2m.supermaintenance.service.HeroService;
import com.w2m.supermaintenance.util.HeroConverter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/heroes")
public class HeroController {

    private final HeroService heroService;

    public HeroController(HeroService heroService) {
        this.heroService = heroService;
    }

    @GetMapping
    @LogExecutionTime
    public ResponseEntity<List<HeroDto>> getAllHeroes() {
        List<HeroDto> heroDtos = heroService.findAllHeroes().stream()
                .map(HeroConverter::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(heroDtos);
    }

    @GetMapping("/{id}")
    @LogExecutionTime
    public ResponseEntity<HeroDto> getHeroById(@PathVariable @NonNull Long id) {
        HeroDto heroDto = heroService.findHeroById(id)
                .map(HeroConverter::convertToDto)
                .orElseThrow(() -> new HeroNotFoundException("Hero not found"));
        return ResponseEntity.ok(heroDto);
    }

    @PostMapping
    @LogExecutionTime
    public ResponseEntity<HeroDto> createHero(@RequestBody HeroDto heroDto) {
        HeroDto savedHeroDto = HeroConverter.convertToDto(heroService.saveHero(HeroConverter.convertToEntity(heroDto)));
        return ResponseEntity.status(HttpStatus.CREATED).body(savedHeroDto);
    }

    @PutMapping("/{id}")
    @LogExecutionTime
    public ResponseEntity<HeroDto> updateHero(@PathVariable @NonNull Long id, @RequestBody HeroDto heroDto) {
        HeroDto updatedHeroDto = heroService.updateHero(id, HeroConverter.convertToEntity(heroDto))
                .map(HeroConverter::convertToDto)
                .orElseThrow(() -> new HeroNotFoundException("Hero not found"));
        return ResponseEntity.ok(updatedHeroDto);
    }

    @DeleteMapping("/{id}")
    @LogExecutionTime
    public ResponseEntity<Void> deleteHero(@PathVariable @NonNull Long id) {
        heroService.deleteHero(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/name/{nameString}")
    @LogExecutionTime
    public ResponseEntity<List<HeroDto>> getHeroesByName(@PathVariable String nameString) {
        List<HeroDto> heroDtos = heroService.findHeroesByName(nameString).stream()
                .map(HeroConverter::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(heroDtos);
    }
}