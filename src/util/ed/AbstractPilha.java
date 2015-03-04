package util.ed;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

public abstract class AbstractPilha<T> implements Pilha<T> {

    protected Deque<T> vetor;

    public AbstractPilha() {
        vetor = new ArrayDeque<T>();
    }

    public AbstractPilha(Deque<T> vetor) {
        this.vetor = vetor;
    }

    //adiciona sempre atras
    public void addLast(T t) {
        vetor.addLast(t);
    }

    @Override
    public Iterator<T> iterator() {
        return vetor.iterator();
    }

    public boolean isEmpty() {
        return vetor.isEmpty();
    }


    public Pilha<T> clone() {
        PilhaAdv<T> nova = new PilhaAdv<T>();

        for (T t : vetor)
            nova.addLast(t);

        return nova;
    }

    public int size() {
        return vetor.size();
    }
}