package pl.javakurselo;

public abstract class Person implements Action{
    private String name;
    private int damage;
    private int health;

    Person(String name, int damage, int health) {
        this.setName(name);
        this.setDamage(damage);
        this.setHealth(health);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
