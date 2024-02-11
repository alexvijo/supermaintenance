package com.w2m.supermaintenance.service;

import java.util.List;
import java.util.Optional;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.w2m.supermaintenance.models.Hero;
import com.w2m.supermaintenance.repository.HeroRepository;

@Service
@Transactional
public class HeroService {

    private final HeroRepository heroRepository;

    public HeroService(HeroRepository heroRepository) {
        this.heroRepository = heroRepository;
    }

    public List<Hero> findAllHeroes() {
        return heroRepository.findAll();
    }

    public Optional<Hero> findHeroById(@NonNull Long id) {
        return heroRepository.findById(id);
    }

    public Hero saveHero(Hero hero) {
        Hero savedHero = null;
        if (hero != null) {
            Long heroId = hero.getId();
            if (heroId != null && heroRepository.existsById(heroId)) {
                savedHero = heroRepository.save(hero);
            }
        }
        return savedHero;
    }

    public Optional<Hero> updateHero(@NonNull Long id, Hero heroDetails) {
        return heroRepository.findById(id)
                .map(hero -> {
                    hero.setName(heroDetails.getName());
                    hero.setDescription(heroDetails.getDescription());
                    return heroRepository.save(hero);
                });
    }

    public boolean deleteHero(@NonNull Long id) {
        if (heroRepository.existsById(id)) {
            heroRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
    
    public List<Hero> findHeroesByName(String nameString) {
        return heroRepository.findAllByNameContainsIgnoreCase(nameString);
    }
}
