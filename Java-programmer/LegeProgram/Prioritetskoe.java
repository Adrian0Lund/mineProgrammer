public class Prioritetskoe<E extends Comparable<E>> extends LenkeListe<E> {
    @Override
    public void leggTil(E x){
        if(stoerrelse() == 0){
            super.leggTil(x);
        }
        else{
            Node<E> gjeldende = forste;
            for(int i = 0; i < stoerrelse(); i++){
                if(gjeldende.hentData().compareTo(x) >= 0){
                    leggTilSpes(i, x);
                    return;
                }
                gjeldende = gjeldende.hentNeste();
            }
            super.leggTil(x);
        }
    }
}