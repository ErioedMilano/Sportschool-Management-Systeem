package gym;

import gym.services.LidService;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final LidService lidService = new LidService();

    public static void main(String[] args){

        boolean running = true;

        while (running){

            printHoofdmenu();
            String keuze = scanner.nextLine();

            switch (keuze){
                case "5" -> running = false;
                default -> System.out.println("ongeldige keuze");
            }
        }
        System.out.println("\nBedankt voor het gebruiken van ons systeem. Tot ziens!");
    }
    private static void printHoofdmenu(){
        System.out.println("\n***SPORTSCHOOL-BEHEERSYSTEEM***");
        System.out.println("1. Ledenbeheer");
    }
    //**********Ledenbeheer**********
    private static void beheerLeden(){

        boolean terug = false;

        while (!terug){
            System.out.println("\n--- LEDENBEHEER ---");
            System.out.println("1. Nieuw lid registreren");
            System.out.println("5. Terug naar hoofdmenu");
            System.out.print("Keuze: ");

            switch (scanner.nextLine()){
                case "" -> registreerLid();
                case "5" -> terug = true;
                default -> System.out.println("Ongeldige keuze");
            }
        }
    }
    private static void registreerLid(){

    }
}
