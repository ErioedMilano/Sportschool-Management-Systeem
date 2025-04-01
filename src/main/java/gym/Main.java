package gym;

import gym.models.*;
import gym.services.AbonnementService;
import gym.services.LidService;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final LidService lidService = new LidService();
    private static final AbonnementService abonnementservice = new AbonnementService();

    public static void main(String[] args){

        boolean running = true;

        while (running){

            printHoofdmenu();
            String keuze = scanner.nextLine();

            switch (keuze){
                case "1" -> beheerLeden();
                case "2" -> beheerAbonnomenten();
                case "5" -> running = false;
                default -> System.out.println("ongeldige keuze");
            }
        }
        System.out.println("\nBedankt voor het gebruiken van ons systeem. Tot ziens!");
    }
    private static void printHoofdmenu(){
        System.out.println("\n***SPORTSCHOOL-BEHEERSYSTEEM***");
        System.out.println("1. Ledenbeheer");
        System.out.println("2. Abonnementenbeheer");
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
            System.out.println("3. Lidgegevens wijzigen");
            System.out.println("4. Lid verwijderen");
            System.out.println("5. Terug naar hoofdmenu");
            System.out.print("Keuze: ");

            String keuze = scanner.nextLine();

            switch (keuze){
                case "1" -> registreerLid();
                case "2" -> toonAlleLeden();
                case "3" -> wijzigLid();
                case "4" -> verwijderLid();
                case "5" -> terug = true;
                default  -> System.out.println("Ongeldige keuze");
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
    private static void wijzigLid() {
        try {
            System.out.print("\nLid ID: ");
            int id = leesInt();

            System.out.print("Nieuwe naam: ");
            String nieuweNaam = scanner.nextLine();

            System.out.print("Nieuwe email: ");
            String nieuweEmail = scanner.nextLine();

            System.out.print("Nieuwe geboortedatum (jjjj-mm-dd): ");
            LocalDate nieuweGeboortedatum = LocalDate.parse(scanner.nextLine());

            Lid lid = new Lid(nieuweNaam, nieuweEmail, nieuweGeboortedatum);
            lid.setId(id);
            lidService.updateLid(lid);
            System.out.println("\nLidgegevens succesvol gewijzigd!");
        } catch (Exception e) {
            System.out.println("\nFout: " + e.getMessage());
        }
    }
    private static void verwijderLid() {
        try {
            System.out.print("\nLid ID: ");
            int id = leesInt();

            System.out.print("Weet u het zeker? (ja/nee): ");
            if (scanner.nextLine().equalsIgnoreCase("ja")) {
                lidService.verwijderLid(id);
                System.out.println("\nLid succesvol verwijderd!");
            }
        } catch (Exception e) {
            System.out.println("\nFout: " + e.getMessage());
        }
    }

    // =============== ABONNEMENTENBEHEER ===============
    private static void beheerAbonnomenten(){

        boolean terug = false;
        while (!terug){

            System.out.println("\n--- ABONNEMENTENBEHEER ---");
            System.out.println("1. Nieuw abonnement");
            System.out.println("2. Toon alle abonnementen");

            switch (scanner.nextLine()){
                case "1" -> registreerAbonnement();
                case "2" -> toonAlleAbonnementen();
                case "7" -> terug = true;
                default -> System.out.println("Ongeldige keuze!");
            }
        }
    }

    private static void registreerAbonnement(){
        try {
            System.out.print("\nLid ID: ");
            int lidId = leesInt();

            System.out.print("Type (BASIC/PREMIUM): ");
            String type = scanner.nextLine();

            System.out.print("Maandelijkse kosten: ");
            double kosten = leesDouble();

            System.out.print("Startdatum (jjjj-mm-dd): ");
            LocalDate start = leesDatum();

            System.out.print("Einddatum (jjjj-mm-dd): ");
            LocalDate eind = leesDatum();

            Abonnement abonnement = new Abonnement(type,kosten,start,eind,lidId);
            abonnementservice.registreerAbonnement(abonnement);
            System.out.println("\nAbonnement succesvol geregistreerd!");
        }catch(Exception e){
            System.out.println("\nFout: " + e.getMessage());
        }
    }
    private static void toonAlleAbonnementen() {
        try {
            List<Abonnement> abonnementen = abonnementservice.getAllAbonnementen();
            if (abonnementen.isEmpty()) {
                System.out.println("\nGeen abonnementen gevonden.");
                return;
            }
            System.out.println("\n--- ALLE ABONNEMENTEN ---");
            for (Abonnement a : abonnementen) {
                System.out.printf("ID: %d | Type: %s | Lid ID: %d | Kosten: €%.2f%n",
                        a.getId(), a.getType(), a.getLidId(), a.getMaandelijkseKosten());
            }
        } catch (Exception e) {
            System.out.println("\nFout: " + e.getMessage());
        }
    }
    // =============== HELPER METHODEN ===============
    private static int leesInt() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Ongeldig getal, probeer opnieuw: ");
            }
        }
    }
    private static double leesDouble() {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Ongeldig bedrag, probeer opnieuw: ");
            }
        }
    }
    private static LocalDate leesDatum() {
        while (true) {
            try {
                System.out.print("Datum (jjjj-mm-dd): ");
                return LocalDate.parse(scanner.nextLine());
            } catch (DateTimeParseException e) {
                System.out.println("Ongeldige datum!");
            }
        }
    }
}
