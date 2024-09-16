import java.util.Iterator;

abstract class LenkeListe<E> implements Liste<E>{
    public Node<E> forste;
    public Node<E> bakerst;

    public int stoerrelse(){
        int antall = 0;
        Node<E> start = forste;
        while(start != null){
            antall++;
            start = start.hentNeste();
        }

        return antall;
    }

    public void leggTil(E x){ //Legger til element bakerst
        Node<E> node = new Node<>(x);

        if(forste == null){
            forste = node;
            bakerst = node;
        }
        else{
            bakerst.settNeste(node);
            bakerst = node;
        }
    }

    public E hent(){ //Returnerer data i forste element
        return forste.hentData();
    }

    public E fjern(){ //Fjerner forste element
        if(forste == null){
            throw new UgyldigListeindeks(0);
        }
        Node<E> mid = forste;
        forste = mid.hentNeste();
        mid.slett();

        return mid.hentData();
    }

    public String toString(){
        String utskrift = "";
        Node<E> start = forste;

        while(start!=null){
            utskrift += "\n" + start.hentData();
            start = start.hentNeste();
        }

        return utskrift;
    }

    public void leggTilSpes(int pos, E x){
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

    public Iterator<E> iterator(){
        return new LenkeListeIterator();
    }

    class LenkeListeIterator implements Iterator<E>{
        private Node<E> denne;

        public LenkeListeIterator(){
            denne = forste;
        }

        @Override
        public boolean hasNext(){
            return denne != null;
        }

        @Override
        public E next(){
            E midl = denne.hentData();
            denne = denne.hentNeste();
            return midl;
        }
    }

    class Node<E> {
        public Node<E> neste;
        public E data;
    
        public Node(E verdi){
            this.data = verdi;
        }
        
        public void settNeste(Node<E> n){
            neste = n;
        }
    
        public Node<E> hentNeste(){
            return neste;
        }
    
        public E hentData(){
            return data;
        }
    
        public void slett(){ //Fjerner neste-pekeren
            neste = null;
        }
    }
}