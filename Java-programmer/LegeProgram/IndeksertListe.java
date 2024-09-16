public class IndeksertListe<E> extends LenkeListe<E>{
    public void leggTil(int pos, E x){
        if(pos < 0 || pos > stoerrelse()) {
            throw new UgyldigListeindeks(pos); 
        }

        Node<E> node = new Node<>(x);

        if(forste == null){
            forste = node;
            bakerst = node;
        }
        else if(pos == stoerrelse()){
            bakerst.settNeste(node);
            bakerst = node;
        }
        else if(pos == 0){
            node.settNeste(forste);
            forste = node;
        }
        else{
            Node<E> gjeldende = forste;
            for(int i = 0; i < pos-1; i++){
                gjeldende = gjeldende.hentNeste();
            }
    
            node.settNeste(gjeldende.hentNeste());
            gjeldende.settNeste(node);
        }
    }

    public void sett(int pos, E x){
        if(pos < 0 || pos >= stoerrelse()) {
            throw new UgyldigListeindeks(pos); 
        }

        Node<E> node = new Node<>(x);

        Node<E> gjeldende = forste;
        for(int i = 0; i < pos-1; i++){
            gjeldende = gjeldende.hentNeste();
        }

        node.settNeste(gjeldende.hentNeste().hentNeste());
        gjeldende.hentNeste().slett();
        gjeldende.settNeste(node);
    }

    public E hent(int pos){
        if(pos < 0 || pos >= stoerrelse()) {
            throw new UgyldigListeindeks(pos); 
        }

        Node<E> gjeldende = forste;
        for(int i = 0; i <= pos-1; i++){
            gjeldende = gjeldende.hentNeste();
        }

        E data = gjeldende.hentData();

        return data;
    }

    public E fjern(int pos){
        if(pos < 0 || pos >= stoerrelse()) {
            throw new UgyldigListeindeks(pos); 
        }

        Node<E> gjeldende = forste;
        for(int i = 0; i < pos-1; i++){
            gjeldende = gjeldende.hentNeste();
        }

        Node<E> mid = gjeldende.hentNeste();

        gjeldende.settNeste(gjeldende.hentNeste().hentNeste());

        return mid.hentData();
    }
}
