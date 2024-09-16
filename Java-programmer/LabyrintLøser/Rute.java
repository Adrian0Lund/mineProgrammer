import java.util.ArrayList;

abstract class Rute {
    public int radNr, kolNr;
    public Labyrint lab;
    public Rute nord, syd, vest, oest;
    public ArrayList<Rute> naboer = new ArrayList<>();
    public boolean ubesokt = true;

    public Rute(int radnr, int kolnr, Labyrint lab){
        radNr = radnr;
        kolNr = kolnr;
        this.lab = lab;
    }

    public void settNabo(Rute nabo, int rad, int kol){
        if(rad == -1){
            nord = nabo;
            naboer.add(nord);
        }
        else if(rad == 1){
            syd = nabo;
            naboer.add(syd);
        }
        else if(kol == -1){
            vest = nabo;
            naboer.add(vest);
        }
        else{
            oest = nabo;
            naboer.add(oest);
        }
    }

    
    public void besokt(){
        ubesokt = false;
    }

    public boolean ubesokt(){
        return ubesokt;
    }

    public void restart(){
        ubesokt = true;
    }

    public void finn(Rute fra, boolean forste){
        besokt();
        for (Rute nabo : naboer) {
            if(nabo != fra && nabo.ubesokt()){
                nabo.finn(this, false);
            }
        }
    }
}
