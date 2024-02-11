package com.w2m.supermaintenance.util;

import com.w2m.supermaintenance.dto.HeroDto;
import com.w2m.supermaintenance.models.Hero;

public class HeroConverter {
    public static HeroDto convertToDto(Hero hero) {
        HeroDto heroDto = new HeroDto();
        heroDto.setId(hero.getId());
        heroDto.setName(hero.getName());
        heroDto.setDescription(hero.getDescription());
        return heroDto;
    }

    public static Hero convertToEntity(HeroDto heroDto) {
        Hero hero = new Hero();
        hero.setId(heroDto.getId());
        hero.setName(heroDto.getName());
        hero.setDescription(heroDto.getDescription());
        return hero;
    }
}
