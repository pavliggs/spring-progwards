package app.homework2;

import java.util.List;

public interface Store<E> {
    void write(E item);
    List<E> read();
}