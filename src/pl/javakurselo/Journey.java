package pl.javakurselo;

import java.io.IOException;
import java.util.Scanner;

public class Journey {
    private int enemiesKilled = 0;
    private int coinsCollected = 0;
    private int roomsPassed = 0;

    private Scanner scanner = new Scanner(System.in);
    private RandomUtilities randomUtilities = new RandomUtilities();

    public void start() throws InterruptedException {

        //profession();

        System.out.println("Podaj imię bohatera:");
        String name = scanner.nextLine();

        int dmg = randomUtilities.damage();
        int hp = randomUtilities.health();

        Hero hero = new Hero(name, dmg, hp);

        heroDescription(hero);
        delay();
        travel(hero);
        endGame();

        try {
            System.out.println("\nKliknij ENTER, by kontynuować ...");
            System.in.read();
        }
        catch (IOException e){

        }
    }

    public void travel(Hero hero) throws InterruptedException {
        boolean isAlive = true;

        System.out.println("\n" + hero.getName() + " wyrusza do przodu w nieznane ...");
        Thread.sleep(1500);

        while(isAlive) {
            int rnd = spawnRoom();

            if(rnd % 5 == 0) {
                System.out.println("Znalazłeś skarb!");
                int coins = randomUtilities.coins();
                System.out.println("Otrzymujesz " + coins + " złota!");

                coinsCollected += coins;
            }
            else if(rnd % 2 == 0 || rnd % 3 == 0) {

                System.out.println("W pokoju znajduje się przeciwnik!");
                fight(hero);
                if(hero.getHealth() >= 0) {
                    int coins = randomUtilities.coins();
                    System.out.println("Otrzymujesz " + coins + " złota!");
                    coinsCollected += coins;
                }
            }
            else {

                System.out.println("Pokój jest pusty, ruszaj dalej.");
            }

            Thread.sleep(1500);
            System.out.println("\nPozostałe punkty życia twojej postaci: " + hero.getHealth());

            if(hero.getHealth() <= 0) {
                isAlive = false;
            }
            else continue;
        }
    }

    public Enemy spawnEnemy() throws InterruptedException {
        Enemy enemy = new Enemy("Elo ", 18,90);

        switch(randomUtilities.whichEnemy()) {
            case 1 -> {
                enemy.setName("Szkielet");
                enemy.setDamage(randomUtilities.damage()/6);
                enemy.setHealth(randomUtilities.health()/4);

                System.out.println("Naprzeciw tobie wychodzi " + enemy.getName() + ".");
                Thread.sleep(1500);
            }
            case 2 -> {
                enemy.setName("Belzebub");
                enemy.setDamage(randomUtilities.damage()/3);
                enemy.setHealth(randomUtilities.health()/3);

                System.out.println("Naprzeciw tobie wychodzi " + enemy.getName() + ".");
                Thread.sleep(1500);
            }
            case 3 -> {
                enemy.setName("Goblin");
                enemy.setDamage(randomUtilities.damage()/4);
                enemy.setHealth(randomUtilities.health()/6);

                System.out.println("Naprzeciw tobie wychodzi " + enemy.getName() + ".");
                Thread.sleep(1500);
            }
        }
        return enemy;
    }

    public int spawnRoom() throws InterruptedException {
        roomsPassed++;

        Thread.sleep(1500);
        System.out.println("\nNatrafiasz na " + roomsPassed + ". pokój.");
        Thread.sleep(1500);

        return randomUtilities.room();
    }

    public void delay() throws InterruptedException {

        Thread.sleep(2000);
        System.out.println("Ruszasz do boju za: ");

        for(int i = 3; i > 0; i--) {
            System.out.print("\t" + i);
            Thread.sleep(1000);
        }

        System.out.println("\nTwoja przygoda właśnie się zaczyna!");
        Thread.sleep(1000);
    }

    public void heroDescription(Hero hero) {
        System.out.println("Informacje o tobie:");
        System.out.println("\tImię: " + hero.getName());
        System.out.println("\tObrażenia: " + hero.getDamage());
        System.out.println("\tPunkty życia: " + hero.getHealth());
        System.out.println();
    }

    public void enemyDescription(Enemy enemy) {
        System.out.println("\nInformacje o przeciwniku:");
        System.out.println("\tImię: " + enemy.getName());
        System.out.println("\tObrażenia: " + enemy.getDamage());
        System.out.println("\tPunkty życia: " + enemy.getHealth());
        System.out.println();
    }

    public void fight(Hero hero) throws InterruptedException {
        int firstAttack = randomUtilities.whoIsFirst();

        Enemy enemy = spawnEnemy();
        Thread.sleep(1500);
        enemyDescription(enemy);
        Thread.sleep(1500);

        if(firstAttack % 2 == 0) System.out.println("Atakujesz pierwszy!");
        else System.out.println("Byłeś zbyt wolny, przeciwnk zaczyna atakować cię pierwszy.");

        Thread.sleep(1500);

        for(int i = firstAttack; hero.getHealth() >= 0 && enemy.getHealth() >= 0; i++) {
            if(i % 2 == 0) {
                int dmgH = hero.giveDamage();
                enemy.receiveDamage(dmgH);
                System.out.println("Trafiasz przeciwnika za: " + hero.getDamage());
                Thread.sleep(800);
            }
            else {
                int dmgE = enemy.giveDamage();
                hero.receiveDamage(dmgE);
                System.out.println("Przeciwnik trafia cię za: " + enemy.getDamage());
                Thread.sleep(800);
            }
        }

        if(hero.getHealth() > 0) {
            System.out.println("\n----Przeciwnik pokonany!----");
            enemiesKilled++;
        }
    }

    public void endGame() throws InterruptedException {
        System.out.println("\n------Jesteś martwy!------");
        Thread.sleep(800);
        System.out.println("Twoje statystyki podróży:");
        System.out.println("\tPrzebyte pokoje: " + roomsPassed);
        System.out.println("\tZabite potwory: " + enemiesKilled);
        System.out.println("\tZebrane monety: " + coinsCollected);
        System.out.println("--------------------------");

        roomsPassed = 0;
        enemiesKilled = 0;
        coinsCollected = 0;
    }

//    public void profession() {
//        System.out.println("Podaj imię bohatera:");
//        String name = scanner.nextLine();
//
//        System.out.println("Wybierz swoją profesję:");
//        System.out.println("\t1. Wojownik - z natury jest .\n\t(mniejsze obrażenia, więcej punktów życia)\n");
//        System.out.println("\t2. Mag - przemądrzały .\n\t(większe obrażenia, mniej punktów życia)\n");
//        System.out.println("\t3. Zwiadowca - .\n\t(zbalansowane obrażenia i punkty życia)\n");
//
//        int userChoice = scanner.nextInt();
//
//        switch(userChoice) {
//            case 1 -> {
//                int dmg = randomUtilities.damage()/3;
//                int hp = randomUtilities.health();
//
//                Hero hero = new Hero(name, dmg, hp);
//            }
//            case 2 -> {
//                int dmg = randomUtilities.damage();
//                int hp = randomUtilities.health()/3;
//
//                Hero hero = new Hero(name, dmg, hp);
//            }
//            case 3 -> {
//                int dmg = randomUtilities.damage()/2;
//                int hp = randomUtilities.health()/2;
//
//                Hero hero = new Hero(name, dmg, hp);
//            }
//        }
//    }
}
