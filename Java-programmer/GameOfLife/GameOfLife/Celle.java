public class Celle {
    public boolean levende;
    public int antNaboer;
    public int antLevendeNaboer;
    public Celle[] naboer;

    public Celle(){
        levende = false;
        antNaboer = 0;
        antLevendeNaboer = 0;
        naboer = new Celle[8];
    }

    public void settDoed(){
        levende = false;
    }

    public void settLevende(){
        levende = true;
    }

    public boolean erLevende(){
        if(levende){
            return true;
        }
        else{
            return false;
        }
    }

    public char hentStatusTegn(){
        if(levende){
            return 'O';
        }
        else{
            return '.';
        }
    }

    public void leggTilNabo(Celle nabo){
        for(int i = 0; i<naboer.length; i++){
            if(naboer[i] == null){
                naboer[i] = nabo;
                antNaboer++;
                break;
            }
        }
    }

    public void tellLevendeNaboer(){
        int antLevende = 0;
        for(int i = 0; i<naboer.length; i++){
            if(naboer[i] != null){
                if(naboer[i].erLevende()){
                    antLevende++;
                }
            }
        }
        antLevendeNaboer = antLevende;
    }

    public void oppdaterStatus(){
        if(levende){
            if(antLevendeNaboer < 2 || antLevendeNaboer > 3){
                levende = false;
            }
        }
        else{
            if(antLevendeNaboer == 3){
                levende = true;
            }
        }
    }
}
