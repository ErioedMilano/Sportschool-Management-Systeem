package gym;

import gym.models.*;
import gym.services.LidService;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;
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
                case "1" -> beheerLeden();
                case "5" -> running = false;
                default -> System.out.println("ongeldige keuze");
            }
        }
        System.out.println("\nBedankt voor het gebruiken van ons systeem. Tot ziens!");
    }
    private static void printHoofdmenu(){
        System.out.println("\n***SPORTSCHOOL-BEHEERSYSTEEM***");
        System.out.println("1. Ledenbeheer");
        System.out.println("5. Afsluiten");
        System.out.print("Maak uw keuze: ");
    }
    //**********Ledenbeheer**********
    private static void beheerLeden(){

        boolean terug = false;

        while (!terug){
            System.out.println("\n--- LEDENBEHEER ---");
            System.out.println("1. Nieuw lid registreren");
            System.out.println("2. Alle leden tonen");
            System.out.println("5. Terug naar hoofdmenu");
            System.out.print("Keuze: ");

            int keuze = scanner.nextInt();
            switch (keuze){
                case 1 -> registreerLid();
                case 2 -> toonAlleLeden();
                case 5 -> terug = true;
                default -> System.out.println("Ongeldige keuze");
            }
        }
    }
    private static void registreerLid(){

        try {
            System.out.print("\nNaam: ");
            String naam = scanner.nextLine();

            System.out.print("Email: ");
            String email = scanner.nextLine();

            System.out.print("Geboortedatum(jaar-maand-dag)");
            LocalDate geboortedatum = LocalDate.parse(scanner.nextLine());

            Lid lid = new Lid(naam,email,geboortedatum);
            lidService.registreerLid(lid);

            System.out.println("\nLid succesvol geregistreerd!");

        }catch (DateTimeException e){
            System.out.println("\nOngeldige datumindeling!");

        }catch (Exception e){
            System.out.println("\nFout: " + e.getMessage());
        }
    }
    private static void toonAlleLeden(){
        List<Lid> leden = lidService.getAlleLeden();
        if (leden.isEmpty()){
            System.out.println("\nGeen leden gevonden.");
            return;
        }
        System.out.println("\n--- ALLE LEDEN ---");
        for (Lid lid : leden){
            System.out.printf("ID: %d | Naam: %s | Email: %s | Geboortedatum: %s%n",
                    lid.getId(), lid.getNaam(), lid.getEmail(), lid.getGeboortedatum());
        }
    }
}
