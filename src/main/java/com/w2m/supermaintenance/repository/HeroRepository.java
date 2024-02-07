package com.w2m.supermaintenance.repository;

import com.w2m.supermaintenance.models.Hero;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface HeroRepository extends JpaRepository<Hero, Long> {
    List<Hero> findAllByNameContainsIgnoreCase(String name);
}
