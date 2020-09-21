package app.lesson1.homework1.tasks;

import java.util.List;

public interface ObjectWithIdRepository<K, V extends ObjectWithId<K>> {
    void save(V item);
    void update(V item);
    void delete(K id);
    List<V> get();
    V get(K id);
}
