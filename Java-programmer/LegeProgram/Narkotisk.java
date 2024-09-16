class Narkotisk extends Legemiddel{
    public final int styrke;

    public Narkotisk(String nNavn, int nPris, double nVirkestoff, int nStyrke){
        super(nNavn, nPris, nVirkestoff);
        styrke = nStyrke;
    }

    @Override
    public String toString(){
        String utskrift = "Navn: " + navn + "\nVirkestoff: " + virkestoff + "\nPris: " + pris + "\nStyrke: " + styrke + "\nID: " + id;
        return utskrift;
    }

    @Override
    public String tilFil(){
        String utskrift = "\n" + navn + ",narkotisk," + pris + "," + virkestoff + "," + styrke;
        return utskrift;
    }
}
