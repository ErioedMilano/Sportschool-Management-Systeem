package gym;

import gym.models.*;
import gym.services.AbonnementService;
import gym.services.LesService;
import gym.services.LidService;
import gym.services.MedewerkerService;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final LidService lidService = new LidService();
    private static final AbonnementService abonnementservice = new AbonnementService();
    private static final LesService lesService = new LesService();
    private static final MedewerkerService medewerkerService = new MedewerkerService();

    public static void main(String[] args){

        boolean running = true;

        while (running){

            printHoofdmenu();
            String keuze = scanner.nextLine();

            switch (keuze){
                case "1" -> beheerLeden();
                case "2" -> beheerAbonnomenten();
                case "3" -> beheerLessen();
                case "4" -> beheerMedewerkers();
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
        System.out.println("3. Lesbeheer");
        System.out.println("4. Medewerkersbeheer");
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
            System.out.println("3. Zoek abonnement op ID");
            System.out.println("4. Zoek abonnement op Lid ID");
            System.out.println("5. Wijzig abonnement");
            System.out.println("6. Verwijder abonnement");
            System.out.println("7. Terug naar hoofdmenu");
            System.out.print("Keuze: ");

            switch (scanner.nextLine()){
                case "1" -> registreerAbonnement();
                case "2" -> toonAlleAbonnementen();
                case "3" -> zoekAbonnementOpId();
                case "4" -> zoekAbonnementOpLidId();
                case "5" -> wijzigAbonnement();
                case "6" -> verwijderAbonnement();
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
    private static void zoekAbonnementOpId() {
        try {
            System.out.print("\nAbonnement ID: ");
            int id = Integer.parseInt(scanner.nextLine());
            Abonnement abonnement = abonnementservice.getAbonnementById(id);

            if (abonnement == null) {
                System.out.println("Geen abonnement gevonden met dit ID!");
                return;
            }

            System.out.printf("\n--- ABONNEMENT %d ---\n", id);
            System.out.println("Type: " + abonnement.getType());
            System.out.println("Lid ID: " + abonnement.getLidId());
            System.out.printf("Kosten: €%.2f p/maand\n", abonnement.getMaandelijkseKosten());
            System.out.println("Geldig van: " + abonnement.getStartdatum() + " tot " + abonnement.getEinddatum());
        }  catch (Exception e) {
            System.out.println("\nFout: " + e.getMessage());
        }
    }
    private static void zoekAbonnementOpLidId() {
        try {
            System.out.print("\nLid ID: ");
            int lidId = Integer.parseInt(scanner.nextLine());
            Abonnement abonnement = abonnementservice.getAbonnementByLidId(lidId);

            if (abonnement == null) {
                System.out.println("Dit lid heeft geen abonnement!");
                return;
            }

            System.out.printf("\n--- ABONNEMENT VAN LID %d ---\n", lidId);
            System.out.println("Type: " + abonnement.getType());
            System.out.printf("Kosten: €%.2f p/maand\n", abonnement.getMaandelijkseKosten());
            System.out.println("Einddatum: " + abonnement.getEinddatum());
        } catch (Exception e) {
            System.out.println("\nFout: " + e.getMessage());
        }
    }
    private static void wijzigAbonnement() {
        try {
            System.out.print("\nAbonnement ID: ");
            int id = leesInt();

            Abonnement abonnement = abonnementservice.getAbonnementById(id);

            if (abonnement == null) {
                System.out.println("\nAbonnement niet gevonden!");
                return;
            }

            System.out.print("Nieuw type (BASIC/PREMIUM): ");
            String type = scanner.nextLine();

            System.out.print("Nieuwe maandelijkse kosten: ");
            double kosten = leesDouble();

            System.out.print("Nieuwe startdatum (jjjj-mm-dd): ");
            LocalDate start = leesDatum();

            System.out.print("Nieuwe einddatum (jjjj-mm-dd): ");
            LocalDate eind = leesDatum();

            abonnement.setType(type);
            abonnement.setMaandelijkseKosten(kosten);
            abonnement.setStartdatum(start);
            abonnement.setEinddatum(eind);

            abonnementservice.wijzigAbonnement(abonnement);

            System.out.println("\nAbonnement succesvol gewijzigd!");
        } catch (Exception e) {
            System.out.println("\nFout: " + e.getMessage());
        }
    }
    private static void verwijderAbonnement() {
        try {
            System.out.print("\nAbonnement ID: ");
            int id = leesInt();

            System.out.print("Weet u het zeker? (j/n): ");
            if (scanner.nextLine().equalsIgnoreCase("j")) {
                abonnementservice.verwijderAbonnement(id);
                System.out.println("\nAbonnement verwijderd!");
            }
        } catch (Exception e) {
            System.out.println("\nFout: " + e.getMessage());
        }
    }
    // =============== LESBEHEER ===============
    private static void beheerLessen() {

        boolean terug = false;
        while (!terug){

            System.out.println("\n--- LESBEHEER ---");
            System.out.println("1. Nieuwe les plannen");
            System.out.println("2. Alle lessen tonen");
            System.out.println("3. Les wijzigen");
            System.out.println("4. Les annuleren");
            System.out.println("5. Lid inschrijven");
            System.out.println("6. Lid uitschrijven");
            System.out.println("7. Terug naar hoofdmenu");
            System.out.print("Keuze: ");

            switch (scanner.nextLine()){
                case "1" -> planLes();
                case "2" -> toonLessen();
                case "3" -> wijzigLes();
                case "4" -> annuleerLes();
                case "5" -> schrijfIn();
                case "6" -> schrijfUit();
                case "7" -> terug = true;
                default -> System.out.println("Ongeldige keuze!");
            }
        }
    }
    private static void planLes() {
        try {
            System.out.print("\nLesnaam: ");
            String naam = scanner.nextLine();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
            System.out.print("Datum/tijd (dd-MM-yyyy HH:mm): ");
            LocalDateTime tijd = LocalDateTime.parse(scanner.nextLine(), formatter);


            System.out.print("Capaciteit: ");
            int capaciteit = leesInt();

            System.out.print("Medewerker ID: ");
            int medewerkerId = leesInt();

            Les les = new Les(naam, tijd, capaciteit);

            lesService.planLes(les, medewerkerId);
            System.out.println("\nLes succesvol gepland!");
        } catch (Exception e) {
            System.out.println("\nFout: " + e.getMessage());
        }
    }
    private static void toonLessen() {
        try {
            List<Les> lessen = lesService.getAllLessen();
            if (lessen.isEmpty()) {
                System.out.println("\nGeen lessen gevonden.");
                return;
            }
            System.out.println("\n--- ALLE LESSEN ---");
            for (Les les : lessen) {
                System.out.printf("ID: %d | %s | %s | Cap: %d | Trainer: %s (ID: %d)%n",
                        les.getId(), les.getNaam(), les.getTijdslot(),
                        les.getCapaciteit(), les.getMedewerker().getNaam(), les.getMedewerker().getId());
            }
        } catch (Exception e) {
            System.out.println("\nFout: " + e.getMessage());
        }
    }
    private static void wijzigLes() {
        try {
            System.out.print("\nLes ID: ");
            int id = leesInt();

            Les les = lesService.getLesById(id);
            if (les == null) {
                System.out.println("\nLes niet gevonden!");
                return;
            }

            System.out.print("Nieuwe naam: ");
            les.setNaam(scanner.nextLine());

            System.out.print("Nieuwe datum/tijd (jjjj-mm-ddTHH:mm): ");
            les.setTijdslot(LocalDateTime.parse(scanner.nextLine()));

            System.out.print("Nieuwe capaciteit: ");
            les.setCapaciteit(leesInt());

            System.out.print("Nieuwe medewerker ID: ");
            int medewerkerId = leesInt();
            les.getMedewerker().setId(medewerkerId);

            lesService.updateLes(les);
            System.out.println("\nLes succesvol gewijzigd!");
        } catch (Exception e) {
            System.out.println("\nFout: " + e.getMessage());
        }
    }
    private static void annuleerLes() {
        try {
            System.out.print("\nLes ID: ");
            int id = leesInt();

            System.out.print("Weet u het zeker? (ja/nee): ");
            if (scanner.nextLine().equalsIgnoreCase("ja")) {
                lesService.annuleerLes(id);
                System.out.println("\nLes geannuleerd!");
            }
        } catch (Exception e) {
            System.out.println("\nFout: " + e.getMessage());
        }
    }

    private static void schrijfIn() {
        try {
            System.out.print("\nLid ID: ");
            int lidId = leesInt();

            System.out.print("Les ID: ");
            int lesId = leesInt();

            lesService.meldAanVoorLes(lidId, lesId);
            System.out.println("\nInschrijving succesvol!");
        } catch (Exception e) {
            System.out.println("\nFout: " + e.getMessage());
        }
    }

    private static void schrijfUit() {
        try {
            System.out.print("\nLid ID: ");
            int lidId = leesInt();

            System.out.print("Les ID: ");
            int lesId = leesInt();

            lesService.verwijderUitLes(lidId, lesId);
            System.out.println("\nUitschrijving succesvol!");
        } catch (Exception e) {
            System.out.println("\nFout: " + e.getMessage());
        }
    }
    // =============== MEDEWERKERBEHEER ===============
    private static void beheerMedewerkers() {
        boolean terug = false;
        while (!terug) {
            System.out.println("\n--- MEDEWERKERBEHEER ---");
            System.out.println("1. Nieuwe medewerker");
            System.out.println("2. Medewerkers tonen");
            System.out.println("3. Medewerker wijzigen");
            System.out.println("4. Medewerker verwijderen");
            System.out.println("5. Terug naar hoofdmenu");
            System.out.print("Keuze: ");

            switch (scanner.nextLine()) {
                case "1" -> registreerMedewerker();
                case "2" -> toonMedewerkers();
                case "3" -> wijzigMedewerker();
                case "4" -> verwijderMedewerker();
                case "5" -> terug = true;
                default -> System.out.println("Ongeldige keuze!");
            }
        }
    }

    private static void registreerMedewerker() {
        try {
            System.out.print("\nNaam: ");
            String naam = scanner.nextLine();

            System.out.print("Functie: ");
            String functie = scanner.nextLine();

            Medewerker medewerker = new Medewerker(naam, functie);
            medewerkerService.registreerMedewerker(medewerker);
            System.out.println("\nMedewerker succesvol geregistreerd!");
        } catch (Exception e) {
            System.out.println("\nFout: " + e.getMessage());
        }
    }

    private static void toonMedewerkers() {
        try {
            List<Medewerker> medewerkers = medewerkerService.getAllMedewerkers();
            if (medewerkers.isEmpty()) {
                System.out.println("\nGeen medewerkers gevonden.");
                return;
            }
            System.out.println("\n--- ALLE MEDEWERKERS ---");
            for (Medewerker m : medewerkers) {
                System.out.printf("ID: %d | Naam: %s | Functie: %s%n",
                        m.getId(), m.getNaam(), m.getFunctie());
            }
        } catch (Exception e) {
            System.out.println("\nFout: " + e.getMessage());
        }
    }

    private static void wijzigMedewerker() {
        try {
            System.out.print("\nMedewerker ID: ");
            int id = leesInt();

            Medewerker medewerker = medewerkerService.getMedewerkerById(id);
            if (medewerker == null) {
                System.out.println("\nMedewerker niet gevonden!");
                return;
            }

            System.out.print("Nieuwe naam: ");
            medewerker.setNaam(scanner.nextLine());

            System.out.print("Nieuwe functie: ");
            medewerker.setFunctie(scanner.nextLine());

            medewerkerService.updateMedewerker(medewerker);
            System.out.println("\nMedewerker gewijzigd!");
        } catch (Exception e) {
            System.out.println("\nFout: " + e.getMessage());
        }
    }

    private static void verwijderMedewerker() {
        try {
            System.out.print("\nMedewerker ID: ");
            int id = leesInt();

            System.out.print("Weet u het zeker? (ja/nee): ");
            if (scanner.nextLine().equalsIgnoreCase("ja")) {
                medewerkerService.verwijderMedewerker(id);
                System.out.println("\nMedewerker verwijderd!");
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
