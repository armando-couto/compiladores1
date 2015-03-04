package util.ed;

import java.util.Deque;

public final class PilhaAdv<T> extends AbstractPilha<T> {

    public PilhaAdv(Deque<T> vetor) {
        super(vetor);
    }

    public PilhaAdv() {
        super();
    }

    public void push(T t) {
        vetor.addFirst(t);
    }

    public T pop() {
        return vetor.removeFirst();
    }

    public T peek() {
        return vetor.peekFirst();
    }

    public boolean isEmpty() {
        return vetor.isEmpty();
    }

    @Override
    public String toString() {
        return vetor.toString();
    }

}