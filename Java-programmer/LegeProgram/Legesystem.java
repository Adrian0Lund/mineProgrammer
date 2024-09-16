import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Legesystem {
    Koe<Pasient> pasientKoe = new Koe<>();
    Koe<Legemiddel> lmKoe = new Koe<>();
    Prioritetskoe<Lege> prioLege = new Prioritetskoe<>();

    public Legesystem() throws UlovligUtskrift, IOException{
        this.lesFil();
        this.start();
    }

    public void lesFil() throws FileNotFoundException, UlovligUtskrift{ //Leser fil
        File fil = new File("info.txt");
        Scanner linje = new Scanner(fil);
        int objektTeller = 0;
        while(linje.hasNextLine()){ 
            String gjeldendeLinje = linje.nextLine();
            if(gjeldendeLinje.startsWith("#")){ //Sjekker kategori. Lm, lege osv.
                objektTeller++; //Legger til teller for kategori
                continue; //Skipper til neste linje for aa begynne aa lage objekter
            }

            String[] biter = gjeldendeLinje.strip().split(",");
            if(objektTeller == 1){ //Lager pasienter
                
                pasientKoe.leggTil(new Pasient(biter[0], biter[1]));
            }
            else if(objektTeller == 2){ //Lager legemidler
                int pris = Integer.parseInt(biter[2]);
                Double virkestoff = Double.parseDouble(biter[3]);
                if(biter[1].equals("narkotisk")){
                    int styrke = Integer.parseInt(biter[4]);
                    lmKoe.leggTil(new Narkotisk(biter[0], pris, virkestoff, styrke));
                }
                else if(biter[1].equals("vanedannende")){
                    int styrke = Integer.parseInt(biter[4]);
                    lmKoe.leggTil(new Vanedannende(biter[0], pris, virkestoff, styrke));
                }
                else if(biter[1].equals("vanlig")){
                    lmKoe.leggTil(new Vanlig(biter[0], pris, virkestoff));
                }
            }
            else if(objektTeller == 3){ //Lager leger
                if(biter[1].equals("0")){
                    prioLege.leggTil(new Lege(biter[0]));
                }
                else{
                    prioLege.leggTil(new Spesialist(biter[0], biter[1]));
                }
            }
            else{ //Lager legemiddel
                Legemiddel lm = null;
                Lege lege = null;
                Pasient pasient = null;
                String farge = biter[3];

                for(Legemiddel lmdl : lmKoe){
                    if(lmdl.hentId()==Integer.parseInt(biter[0])){
                        lm = lmdl;
                        break;
                    }
                }
                for(Pasient pasientmdl : pasientKoe){
                    if(pasientmdl.hentId()==Integer.parseInt(biter[2])){
                        pasient = pasientmdl;
                        break;
                    }
                }
                for(Lege legemdl : prioLege){
                    if(legemdl.hentNavn().equals(biter[1])){
                        lege = legemdl;
                        break;
                    }
                }

                if(lege == null || lm == null || pasient == null){
                    System.out.println("Lege, pasient eller legemiddel eksisterer ikke");
                    return;
                }

                if(farge.equals("hvit")){
                    lege.skrivHvitResept(lm, pasient, Integer.parseInt(biter[4]));
                }
                else if(farge.equals("blaa")){
                    lege.skrivBlaaResept(lm, pasient, Integer.parseInt(biter[4]));
                }
                else if(farge.equals("militaer")){
                    lege.skrivMilitaerResept(lm, pasient);
                }
                else{
                    lege.skrivPResept(lm, pasient, Integer.parseInt(biter[4]));
                }
            }
        }
        linje.close();
    }

    public void start() throws UlovligUtskrift, IOException{ //Starter lokke for a bruke program
        meny();
        Scanner input = new Scanner(System.in);
        int tall = input.nextInt();

        while(tall != 5){
            if(tall == 0){ //Skriver ut alle objekter i systemet
                System.out.println("Pasienter");
                for(Pasient pasient : pasientKoe){
                    System.out.println(pasient.toString());
                    System.out.println();
                }

                System.out.println("Legemidler");
                for(Legemiddel lm : lmKoe){
                    System.out.println(lm.toString());
                    System.out.println();
                }

                System.out.println("Leger");
                for(Lege lege : prioLege){
                    System.out.println(lege.toString());
                    System.out.println();
                }

                System.out.println("Resepter");
                for(Lege lege : prioLege){
                    IndeksertListe<Resept> resepter = lege.hentResepter();
                    for(Resept resept : resepter){
                        System.out.println(resept.toString());
                        System.out.println();
                    }
                }
            }
            else if(tall == 1){ //Meny for å legge til nye objekter
                System.out.println("0: Legg til pasient");
                System.out.println("1: Legg til lege");
                System.out.println("2: Legg til legemiddel");
                System.out.println("3: Lag ny resept");
                System.out.println("4: Tilbake");
                tall = input.nextInt();
                Scanner tekst = new Scanner(System.in);
        
                while(tall != 4){
                    if(tall == 0){ //Opprette ny pasient
                        System.out.println("Navn: ");
                        String navn = tekst.nextLine();
                        System.out.println("Fodselsnummer: ");
                        String fodNr = input.next();
    
                        pasientKoe.leggTil(new Pasient(navn, fodNr));
                        System.out.println(navn + " ble lagt til som pasient!");
                    }
                    else if(tall == 1){ //Opprette ny lege
                        System.out.println("0: Lege");
                        System.out.println("1: Spesialist");
                        tall = input.nextInt();
                        String navn;

                        if(tall == 0){
                            System.out.println("Navn: ");
                            navn = tekst.nextLine();
                            prioLege.leggTil(new Lege(navn));
                        }
                        else if(tall == 1){
                            System.out.println("Navn: ");
                            navn = tekst.nextLine();

                            System.out.println("Kontrollkode: ");
                            String kontroll = input.next();

                            prioLege.leggTil(new Spesialist(navn, kontroll));
                        }
                        else{
                            System.out.println("Ugyldig valg");
                        }
                    }
                    else if(tall == 2){ //Opprette nytt legemiddel
                        System.out.println("0: Narkotisk");
                        System.out.println("1: Vanedannende");
                        System.out.println("2: Vanlig");
                        tall = input.nextInt();

                        if(tall == 0 || tall == 1 || tall == 2){
                            System.out.println("Navn: ");
                            String navn = tekst.nextLine();
                            System.out.println("Pris: ");
                            int pris = input.nextInt();
                            System.out.println("Virkestoff: ");
                            Double virkestoff = Double.parseDouble(input.next());

                            if(tall == 0){
                                System.out.println("Styrke: ");
                                int styrke = input.nextInt();
    
                                lmKoe.leggTil(new Narkotisk(navn, pris, virkestoff, styrke));
                            }
                            else if(tall == 1){
                                System.out.println("Styrke: ");
                                int styrke = input.nextInt();
    
                                lmKoe.leggTil(new Vanedannende(navn, pris, virkestoff, styrke));
                            }
                            else{
                                lmKoe.leggTil(new Vanlig(navn, pris, virkestoff));
                            }
                        }
                        else{
                            System.out.println("Ugyldig valg");
                        }
                    }
                    else if(tall == 3){ //Lage ny resept
                        int teller = 0;
                        for(Legemiddel lm : lmKoe){
                            System.out.println(teller + ": " + lm.hentNavn());
                            teller++;
                        }
   
                        tall = input.nextInt();
                        Legemiddel legeMdl = null;
                        int teller2 = 0;
                        if(tall<=teller){
                            for(Legemiddel lm : lmKoe){
                                if(tall == teller2){
                                    legeMdl = lm;
                                    break;
                                }
                                teller2++;
                            }
                        }
                        else{
                            System.out.println("Ugyldig valg");
                            break;
                        }

                        teller = 0;
                        for(Pasient pas : pasientKoe){
                            System.out.println(teller + ": " + pas.hentNavn());
                            teller++;
                        }
   
                        tall = input.nextInt();
                        Pasient pasient = null;
                        teller2 = 0;
                        if(tall<=teller){
                            for(Pasient pas : pasientKoe){
                                if(tall == teller2){
                                    pasient = pas;
                                    break;
                                }
                                teller2++;
                            }
                        }
                        else{
                            System.out.println("Ugyldig valg");
                            break;
                        }

                        teller = 0;
                        for(Lege lg : prioLege){
                            System.out.println(teller + ": " + lg.hentNavn());
                            teller++;
                        }
   
                        tall = input.nextInt();
                        Lege lege = null;
                        teller2 = 0;
                        if(tall<=teller){
                            for(Lege lg: prioLege){
                                if(tall == teller2){
                                    lege = lg;
                                    break;
                                }
                                teller2++;
                            }
                        }
                        else{
                            System.out.println("Ugyldig valg");
                            break;
                        }

                        System.out.println("0: Hvit resept");
                        System.out.println("1: Militaerresept");
                        System.out.println("2: P-resept");
                        System.out.println("3: Blaa resept");
                        System.out.println("4: Tilbake");
                        tall = input.nextInt();
                        while(tall!=4){
                            if(tall == 0){
                                System.out.println("Skriv reit:");
                                tall = input.nextInt();
                                lege.skrivHvitResept(legeMdl, pasient, tall);
                            }
                            else if(tall == 1){
                                lege.skrivMilitaerResept(legeMdl, pasient);
                            }
                            else if(tall == 2){
                                System.out.println("Skriv reit:");
                                tall = input.nextInt();
                                lege.skrivPResept(legeMdl, pasient, tall);
                            }
                            else if(tall == 3){
                                System.out.println("Skriv reit:");
                                tall = input.nextInt();
                                lege.skrivBlaaResept(legeMdl, pasient, teller);                               
                            }
                            else{
                                System.out.println("Ugyldig valg");
                            }
                            break;
                        }
                    }   
                    else{
                        System.out.println("Ugyldig valg");
                    }
                    System.out.println("0: Legg til pasient");
                    System.out.println("1: Legg til lege");
                    System.out.println("2: Legg til legemiddel");
                    System.out.println("3: Lag ny resept");
                    System.out.println("4: Tilbake");
                    tall = input.nextInt();
                }
            }
            else if(tall == 2){ //Bruke resept
                int teller = 0;
                IndeksertListe<Resept> resepter;
                int sjekkPas;
                
                for(Pasient pas : pasientKoe){
                    sjekkPas = 0;
                    for(Lege lege : prioLege){
                        resepter = lege.hentResepter();
                        for(Resept res : resepter){
                            if(pas.hentId() == res.hentPasient().hentId() && sjekkPas == 0  && res.hentReit() > 0){
                                System.out.println(teller + ": " + pas.hentNavn() + "(fnr " + pas.hentFodNr() + ")");
                                sjekkPas++;
                                teller++;
                                break;
                            }
                        }
                    }
                }

                tall = input.nextInt();
                Pasient pasient = null;
                int teller2 = 0;
                if(tall<=teller){
                    for(Pasient pas : pasientKoe){
                        sjekkPas = 0;
                        for(Lege lege : prioLege){
                            resepter = lege.hentResepter();
                            for(Resept res : resepter){
                                if(pas.hentId() == res.hentPasient().hentId() && sjekkPas == 0  && res.hentReit() > 0){
                                    sjekkPas++;
                                    if(tall == teller2){
                                        pasient = pas;
                                    }
                                    teller2++;
                                }
                            }
                        }
                    }
                }
                else{
                    System.out.println("Ugyldig valg");
                    break;
                }
                System.out.println("Valgt pasient: " + pasient.hentNavn() + "(fnr " + pasient.hentFodNr() + ")");
                System.out.println("Hvilken resept vil du bruke?");
                teller = 0;
                for(Lege lege : prioLege){
                    resepter = lege.hentResepter();
                    for(Resept res : resepter){
                        if(pasient.hentId() == res.hentPasient().hentId()){
                            System.out.println(teller + ": " + res.hentLegemiddel().hentNavn() + "(reit: " + res.hentReit() + ")");
                            teller++;
                        }
                    }
                }

                tall = input.nextInt();
                Resept resept = null;
                teller2 = 0;
                if(tall<=teller){
                    for(Lege lege : prioLege){
                        resepter = lege.hentResepter();
                        for(Resept res : resepter){
                            if(pasient.hentId() == res.hentPasient().hentId()){                               
                                if(tall == teller2){
                                    resept = res;
                                    break;
                                }
                                teller2++;
                            }
                        }
                    }
                }
                else{
                    System.out.println("Ugyldig valg");
                    break;
                }

                if(resept.bruk()){
                    System.out.println("Brukte resept paa " + resept.hentLegemiddel().hentNavn() + ". Reit: " + resept.hentReit());
                }
                else{
                    System.out.println("Kan ikke bruke resept paa " + resept.hentLegemiddel().hentNavn() + ". Reit er tom");
                }
                
            }
            else if(tall == 3){ //Viser statistikk
                System.out.println("0: Antall resepter med vanedannende legemidler");
                System.out.println("1: Antall resepter med narkotiske legemidler");
                System.out.println("2: Statistikk om mulig misbruk");
                System.out.println("3: Tilbake");
                tall = input.nextInt();

                while(tall != 3){
                    if(tall == 0){ //Teller antall resepter med vanedannende legemidler
                        int antall = tell(0);
                        System.out.println("Det er " + antall + " resepter med vanedannende legemiddel");
                    }
                    else if(tall == 1){ //Teller antall resepter med narkotiske legemidler
                        int antall = tell(1);
                        System.out.println("Det er " + antall + " resepter med narkotisk legemiddel");
                    }
                    else if(tall == 2){
                        int antResepterLege;
                        IndeksertListe<Resept> resepter;
                        for(Lege lege : prioLege){
                            antResepterLege = 0;
                            resepter = lege.hentResepter();
                            for(Resept res : resepter){
                                if(res.hentLegemiddel() instanceof Narkotisk){
                                    antResepterLege++;
                                }
                            }
                            if(antResepterLege > 0){
                                System.out.println(lege.hentNavn() + ". Antall resepter med narkotiske legemdiler: " + antResepterLege);
                            }
                        }

                        int antResepterPasient;
                        for(Pasient pas : pasientKoe){
                            antResepterPasient = 0;
                            for(Lege lege : prioLege){
                                resepter = lege.hentResepter();
                                for(Resept res : resepter){
                                    if(res.hentLegemiddel() instanceof Narkotisk && pas.hentId() == res.hentPasient().hentId()){
                                        antResepterPasient++;
                                    }
                                }
                            }
                            if(antResepterPasient > 0){
                                System.out.println(pas.hentNavn() + ". Antall resepter med narkotiske legemdiler: " + antResepterPasient);
                            }
                        }
                    }
                    else{
                        System.out.println("Ugyldig valg");
                    }

                    System.out.println("0: Antall resepter med vanedannende legemidler");
                    System.out.println("1: Antall resepter med narkotiske legemidler");
                    System.out.println("2: Statistikk om mulig misbruk");
                    System.out.println("3: Tilbake");
                    tall = input.nextInt();
                }
            }
            else if(tall == 4){ //Lese all informasjon til fil               
                FileWriter fil = new FileWriter("info.txt"); 
                fil.write("# Pasienter (navn, fnr)");
                for(Pasient pas : pasientKoe){
                    fil.write(pas.tilFil());
                }

                fil.write("\n# Legemidler (navn,type,pris,virkestoff,[styrke])");
                for(Legemiddel lm : lmKoe){
                    fil.write(lm.tilFil());
                }

                fil.write("\n# Leger (navn,kontrollid / 0 hvis vanlig lege)");
                for(Lege lege : prioLege){
                    fil.write(lege.tilFil());
                }

                fil.write("\n# Resepter (legemiddelNummer,legeNavn,pasientID,type,[reit])");
                for(Lege lege : prioLege){
                    IndeksertListe<Resept> resepter = lege.hentResepter();
                    for(Resept res : resepter){
                        fil.write(res.tilFil());
                    }
                }

                fil.close();
                System.out.println("Data lagret");
            }
            meny();
            tall = input.nextInt();
        }
    }
    
    private void meny(){ //Funksjon for  skrive ut meny
        System.out.println("0: Skriv fullstendig oversikt");
        System.out.println("1: Opprette og legge til nye elementer i systemet");
        System.out.println("2: Bruke resept");
        System.out.println("3: Skriv statistikk");
        System.out.println("4: Skriv all data til fil");
        System.out.println("5: Avslutt");
    }

    private int tell(int valg){ //Funksjon for å telle antall legemidler
        int antVane = 0;
        int antNark = 0;

        for(Lege lege : prioLege){
            IndeksertListe<Resept> resepter = lege.hentResepter();
            for(Resept res : resepter){
                if(res.hentLegemiddel() instanceof Vanedannende){
                    antVane++;
                }
                else if(res.hentLegemiddel() instanceof Narkotisk){
                    antNark++;
                }
            }
        }

        if(valg == 0){
            return antVane;
        }
        else{
            return antNark;
        }
    }
}
