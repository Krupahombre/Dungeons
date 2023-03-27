package pl.javakurselo;

public class Hero extends Person{

    public Hero(String name, int damage, int health) {
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
