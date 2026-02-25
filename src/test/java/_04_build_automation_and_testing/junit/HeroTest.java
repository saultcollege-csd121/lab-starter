package _04_build_automation_and_testing.junit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HeroTest {

    Hero hero;

    @BeforeEach
    void setUp() {
        hero = new Hero();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testThatHeroHas100HealthToStart() {
        assertEquals(100, hero.getHealth());
    }

    @Test
    void testThatHeroTakesDamage() {
        hero.takeDamage(30);
        assertEquals(70, hero.getHealth());
    }

    @Test
    void testThatHeroHealthDoesNotGoNegative() {
        hero.takeDamage(150);
        assertEquals(0, hero.getHealth());
    }

}