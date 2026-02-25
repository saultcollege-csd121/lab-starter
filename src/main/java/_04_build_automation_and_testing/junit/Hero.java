package _04_build_automation_and_testing.junit;

public class Hero {

    private int health;

    public Hero(int health) {
        this.health = health;
    }

    public Hero() {
        this.health = 100;
    }

    public int getHealth() {
        return health;
    }

    public void takeDamage(int damage) {
        health = Math.max(health - damage, 0);
    }
}
