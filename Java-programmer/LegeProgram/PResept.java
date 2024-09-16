class PResept extends HvitResept {
    public PResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit){
        super(legemiddel, utskrivendeLege, pasient, reit);
        settPris(legemiddel.hentPris());
    }

    void settPris(int pris){
        if(pris > 108){
            super.pris = pris-108;
        }
        else{
            super.pris = 0;
        }
    }

    @Override
    public String toString(){
        String utskrift = "Farge: Hvit" + "\nLegemiddel: " + legemiddel.hentNavn() + "\nLegens navn: " + utskrivendeLege.hentNavn() + "\nPasientens navn: " + pasient.hentNavn() + "\nReseptID: " + reseptId + "\nReit: " + reit + "\nPris: " + pris;
        return utskrift;
    }

    public String tilFil(){
        String utskrift = "\n" + legemiddel.hentId() + "," + utskrivendeLege.hentNavn() + "," + pasient.hentId() + ",p," + reit;
        return utskrift;
    }
}
