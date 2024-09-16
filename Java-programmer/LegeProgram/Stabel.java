public class Stabel<E> extends LenkeListe<E> {
    @Override
    public void leggTil(E x){
        Node<E> node = new Node<>(x);

        if(forste == null){
            forste = node;
            bakerst = node;
        }
        else{
            node.settNeste(forste);
            forste = node;
        }
    }
}
