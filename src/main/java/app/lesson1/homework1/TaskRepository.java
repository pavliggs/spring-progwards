package app.lesson1.homework1;

import java.util.List;

public interface TaskRepository {
    void save(Task task);
    void update(Task task);
    void delete(String id);
    List<Task> get();
    Task get(String id);
}
