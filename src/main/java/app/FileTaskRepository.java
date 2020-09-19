package app;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class FileTaskRepository implements TaskRepository {
    static final String PATH = "C:\\Users\\Эльдорадо\\IdeaProjects\\spring-progwards\\task.json";
    private Map<String, Task> taskMap = new ConcurrentHashMap();

    public FileTaskRepository() {
        try {
            Path path = Paths.get(PATH);
            if (!Files.exists(path)) {
                Files.createFile(path);
                saveAll();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public List<Task> getAll() {
        return this.taskMap.values().stream().collect(Collectors.toUnmodifiableList());
    }

    public void readAll() throws IOException {
        synchronized(this) {
            this.taskMap.clear();
            String json = Files.readString(Path.of(PATH));
            ArrayList<Task> list = new Gson().fromJson(json, (new TypeToken<ArrayList<Task>>() {}).getType());
            list.forEach((e) -> {
                this.taskMap.put(e.getId(), e);
            });
        }
    }

    public void saveAll() throws IOException {
        synchronized(this) {
            String json = new Gson().toJson(this.getAll());
            Files.writeString(Path.of(PATH), json);
        }
    }

    @Override
    public void save(Task task) {
        try {
            readAll();
            Task value = this.taskMap.put(task.getId(), task);
            if (value == null)
                saveAll();
            else
                System.out.println("Задача с ID=" + task.getId() + " уже существует в списке задач. " +
                        "Попробуйте воспользоваться операцией UPDATE");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(Task task) {
        try {
            readAll();
            Task value = this.taskMap.remove(task.getId());
            saveAll();
            if (value != null)
                save(task);
            else
                System.out.println("Задача с ID=" + task.getId() + " не существует в списке задач. " +
                        "Воспользуйтесь операцией SAVE");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(String id) {
        try {
            readAll();
            Task value = this.taskMap.remove(id);
            if (value != null)
                saveAll();
            else
                System.out.println("Задача с ID=" + id + " не существует в списке задач");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Task> get() {
        try {
            readAll();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return getAll();
    }

    @Override
    public Task get(String id) {
        try {
            readAll();
            Task value = this.taskMap.get(id);
            if (value != null)
                return value;
            else
                System.out.println("Задача с ID=" + id + " не существует в списке задач");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
