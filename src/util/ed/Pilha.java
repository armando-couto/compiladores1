package util.ed;

public interface Pilha<T> extends Iterable<T> {
    public void push(T t);

    public T pop();

    public T peek();

    public boolean isEmpty();

    public Pilha<T> clone();
}
