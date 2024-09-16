public class Verden {
    public int antRader;
    public int antKolonner;
    public int genNr;
    public Rutenett rutenett;

    public Verden(int rad, int kol){
        antRader = rad;
        antKolonner = kol;
        genNr = 0;
        rutenett = new Rutenett(antRader, antKolonner);
    }

    public void tegn(){
        rutenett.tegnGUIRutenett();
    }
}
