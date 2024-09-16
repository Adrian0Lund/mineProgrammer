public class SortRute extends Rute {
    public SortRute(int rad, int kol, Labyrint lab){
        super(rad, kol, lab);
    }

    public String toString(){
        return "#";
    }

    @Override
    public void finn(Rute fra, boolean forste){
        if(forste){
            System.out.println("Du kan ikke starte i en sort rute");
        }
    }
}
