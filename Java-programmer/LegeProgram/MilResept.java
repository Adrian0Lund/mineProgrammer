class MilResept extends HvitResept {
    public MilResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient){
        super(legemiddel, utskrivendeLege, pasient, 3);
        super.pris = 0;
    }

    @Override
    public String toString(){
        String utskrift = "Farge: Hvit" + "\nLegemiddel: " + legemiddel.hentNavn() + "\nLegens navn: " + utskrivendeLege.hentNavn() + "\nPasientens navn: " + pasient.hentNavn() + "\nReseptID: " + reseptId + "\nReit: " + reit + "\nPris: " + pris;
        return utskrift;
    }

    public String tilFil(){
        String utskrift = "\n" + legemiddel.hentId() + "," + utskrivendeLege.hentNavn() + "," + pasient.hentId() + ",militaer";
        return utskrift;
    }
}