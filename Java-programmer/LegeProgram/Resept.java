abstract class Resept {
    public final Legemiddel legemiddel;
    public final Lege utskrivendeLege;
    public final Pasient pasient;
    public final int reseptId;
    public int reit;
    public int pris;
    private static int antId;

    public Resept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit) {
        this.legemiddel = legemiddel;
        this.utskrivendeLege = utskrivendeLege;
        this.pasient = pasient;
        this.reit = reit;
        this.reseptId = antId;
        antId++;
    }
     
    public int hentId(){
        return reseptId;
    }
    
    public Legemiddel hentLegemiddel(){
        return legemiddel;
    }

    public Lege hentLege(){
        return utskrivendeLege;
    }

    public Pasient hentPasient(){
        return pasient;
    }

    public int hentReit(){
        return reit;
    }

    public boolean bruk(){
        if(reit == 0){
            return false;
        }
        else {
            reit--;
            return true;
        }
    }

    abstract public String farge();
    abstract public int prisAaBetale();
    abstract public String tilFil();    
}