package com.w2m.supermaintenance.controller;

import com.w2m.supermaintenance.models.Hero;
import com.w2m.supermaintenance.service.HeroService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/heroes")
public class HeroController {

    @Autowired
    private HeroService heroService;

    @GetMapping
    public List<Hero> getAllHeroes() {
        return heroService.findAllHeroes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hero> getHeroById(@PathVariable Long id) {
        return heroService.findHeroById(id)
                .map(hero -> new ResponseEntity<>(hero, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public Hero createHero(@RequestBody Hero hero) {
        return heroService.saveHero(hero);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Hero> updateHero(@PathVariable Long id, @RequestBody Hero heroDetails) {
        return heroService.updateHero(id, heroDetails)
                .map(updatedHero -> new ResponseEntity<>(updatedHero, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHero(@PathVariable Long id) {
        if (heroService.deleteHero(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/name/{nameString}")
    public List<Hero> getHeroesByName(@PathVariable String nameString) {
        return heroService.findHeroesByName(nameString);
    }

}
