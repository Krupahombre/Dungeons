package pl.javakurselo;

import java.util.Random;

public class RandomUtilities implements Randomizer{
    Random random = new Random();

    @Override
    public int damage() {
        return random.nextInt(51) + 50;
    }

    @Override
    public int health() {
        return random.nextInt(301) + 200;
    }

    @Override
    public int coins() {
        return random.nextInt(9) + 1;
    }

    @Override
    public int room() {
        return random.nextInt(9) + 1;
    }

    @Override
    public int whoIsFirst() {
        return random.nextInt(2) + 1;
    }

    @Override
    public int whichEnemy() {
        return random.nextInt(3) + 1;
    }
}
