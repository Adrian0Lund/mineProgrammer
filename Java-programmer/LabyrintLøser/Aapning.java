public class Aapning extends HvitRute {
    public Aapning(int rad, int kol, Labyrint lab){
        super(rad, kol, lab);
    }

    @Override
    public void finn(Rute fra, boolean forste){
        System.out.println("(" + radNr + "," + kolNr + ")");
        besokt();
        for (Rute nabo : naboer) {
            if(nabo != fra && nabo.ubesokt()){
                nabo.finn(this, false);
            }
        }
    }
}
