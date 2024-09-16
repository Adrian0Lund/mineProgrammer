class BlaaResept extends Resept {
    public BlaaResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit){
        super(legemiddel, utskrivendeLege, pasient, reit);
        settPris(legemiddel.hentPris());
    }

    void settPris(int pris){
        super.pris = (int)Math.round(pris*0.25);
    }

    public int prisAaBetale(){
        return super.pris;
    }

    @Override
    public String toString(){
        String utskrift = "Farge: Blaa" + "\nLegemiddel: " + legemiddel.hentNavn() + "\nLegens navn: " + utskrivendeLege.hentNavn() + "\nPasientens navn: " + pasient.hentNavn() + "\nReseptID: " + reseptId + "\nReit: " + reit + "\nPris: " + pris;
        return utskrift;
    }

    @Override
    public String farge() {
        return "blaa";
    }

    public String tilFil(){
        String utskrift = "\n" + legemiddel.hentId() + "," + utskrivendeLege.hentNavn() + "," + pasient.hentId() + ",blaa," + reit;
        return utskrift;
    }
}
