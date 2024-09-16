abstract class Legemiddel {
    public final String navn;
    public int pris;
    public final double virkestoff;
    public final int id;
    private static int antId;

    public Legemiddel(String nNavn, int nPris, double nVirkestoff){
        navn = nNavn;
        pris = nPris;
        virkestoff = nVirkestoff;
        id = antId;
        antId++;
    }

    public int hentPris(){
        return pris;
    }

    public void settNyPris(int nyPris){
        pris = nyPris;
    }

    public String hentNavn(){
        return navn;
    }

    public int hentId(){
        return id;
    }

    public String toString(){
        String utskrift = "Navn: " + navn + "\nVirkestoff: " + virkestoff + "\nPris: " + pris + "\nID: " + id;
        return utskrift;
    }

    public String tilFil(){
        String utskrift = "\n" + navn + ",vanlig," + pris + "," + virkestoff;
        return utskrift;
    }
}