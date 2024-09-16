class Spesialist extends Lege implements Godkjenningsfritak{
    public String kontrollkode;

    public Spesialist(String navn, String kontrollkode){
        super(navn);
        this.kontrollkode = kontrollkode;
    }

    public String hentKontrollkode(){
        return kontrollkode;
    }

    @Override
    public String toString(){
        String utskrift = "Navn: " + navn + "\nKontrollkode: " + kontrollkode;
        return utskrift;
    }

    @Override
    public String tilFil(){
        String utsrkift = "\n" + navn + "," + kontrollkode;
        return utsrkift;
    }
}