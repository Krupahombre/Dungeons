package pl.javakurselo;

import java.util.Scanner;

public class Dungeon {

    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        boolean shouldContinue = true;

        Journey journey = new Journey();

        while(shouldContinue) {
            System.out.println("-------------------------------");
            System.out.println("        Witaj w Lochach        ");
            System.out.println("-------------------------------\n");

            System.out.println("Wybierz, co chcesz zrobić: ");
            System.out.println("\t1. Nowa przygoda");
            System.out.println("\t2. Koniec przygody");

            int userChoice = scanner.nextInt();

            switch(userChoice) {
                case 1 -> journey.start();
                case 2 -> shouldContinue = false;
            }
        }
    }
}
