class HvitResept extends Resept {
    public HvitResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit){
        super(legemiddel, utskrivendeLege, pasient, reit);
    }

    public String farge(){
        return "hvit";
    }

    public int prisAaBetale(){
        return super.pris;
    }

    public String toString(){
        String utskrift = "Farge: Hvit" + "\nLegemiddel: " + legemiddel.hentNavn() + "\nLegens navn: " + utskrivendeLege.hentNavn() + "\nPasientens navn: " + pasient.hentNavn() + "\nReseptID: " + reseptId + "\nReit: " + reit;
        return utskrift;
    }

    public String tilFil(){
        String utskrift = "\n" + legemiddel.hentId() + "," + utskrivendeLege.hentNavn() + "," + pasient.hentId() + ",hvit," + reit;
        return utskrift;
    }
}
