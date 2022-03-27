package port;

public interface Merger<E> {
    E merge(E a, E b);
}
