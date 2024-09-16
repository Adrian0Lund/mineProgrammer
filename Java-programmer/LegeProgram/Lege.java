public class Lege implements Comparable<Lege>{
    public String navn;
    public IndeksertListe<Resept> utskrevendeResepter = new IndeksertListe<>();

    public Lege(String navn){
        this.navn = navn;
    }

    public String hentNavn(){
        return navn;
    }

    public String toString(){
        String utsrkift = "Navn: " + navn;
        return utsrkift;
    }

    public String tilFil(){
        String utsrkift = "\n" + navn + ",0";
        return utsrkift;
    }

    public IndeksertListe<Resept> hentResepter(){
        return utskrevendeResepter;
    }

    public int compareTo(Lege lege) {
        return this.navn.compareTo(lege.hentNavn());
    }

    public HvitResept skrivHvitResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {
        if (legemiddel instanceof Narkotisk && !(this instanceof Spesialist)){
            throw new UlovligUtskrift(this, legemiddel);
        }
        HvitResept hvitResept = new HvitResept(legemiddel, this, pasient, reit);
        utskrevendeResepter.leggTil(hvitResept);

        return hvitResept;
    }

    public PResept skrivPResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {
        if (legemiddel instanceof Narkotisk && !(this instanceof Spesialist)){
            throw new UlovligUtskrift(this, legemiddel);
        }
        PResept resept = new PResept(legemiddel, this, pasient, reit);
        utskrevendeResepter.leggTil(resept);

        return resept;
    }

    public MilResept skrivMilitaerResept(Legemiddel legemiddel, Pasient pasient) throws UlovligUtskrift {
        if (legemiddel instanceof Narkotisk && !(this instanceof Spesialist)){
            throw new UlovligUtskrift(this, legemiddel);
        }
        MilResept resept = new MilResept(legemiddel, this, pasient);
        utskrevendeResepter.leggTil(resept);

        return resept;
    }

    public BlaaResept skrivBlaaResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {
        if (legemiddel instanceof Narkotisk && !(this instanceof Spesialist)){
            throw new UlovligUtskrift(this, legemiddel);
        }
        BlaaResept resept = new BlaaResept(legemiddel, this, pasient, reit);
        utskrevendeResepter.leggTil(resept);

        return resept;
    }
}