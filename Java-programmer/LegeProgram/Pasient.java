public class Pasient {
    public String navn;
    public String fodNr;
    public int id;
    private static int antId;

    public Pasient(String navn, String fodNr){
        this.navn = navn;
        this.fodNr = fodNr;
        id = antId;
        antId++;
    }

    public String hentNavn(){
        return navn;
    }

    public String hentFodNr(){
        return fodNr;
    }

    public int hentId(){
        return id;
    }

    public String toString(){
        String utsrkift = "Navn: " + navn + "\nFodselsnummer: " + fodNr + "\nID: " + id;
        return utsrkift;
    }

    public String tilFil(){
        String utskrift = "\n" + navn + "," + fodNr;
        return utskrift;
    }
}
