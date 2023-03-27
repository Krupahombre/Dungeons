package pl.javakurselo;

public class Enemy extends Person{

    public Enemy(String name, int damage, int health) {
        super(name, damage, health);
    }

    @Override
    public int giveDamage() {
        return this.getDamage();
    }

    @Override
    public void receiveDamage(int dmg) {
        this.setHealth(this.getHealth() - dmg);
    }
}
