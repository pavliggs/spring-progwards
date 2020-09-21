package app.lesson1.homework1.tasks;

import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class ObjectWithIdRepositoryImpl<K,V extends ObjectWithId<K>> implements ObjectWithIdRepository<K,V> {
    static final String PATH = "C:\\Users\\Эльдорадо\\IdeaProjects\\spring-progwards\\";
    private final Map<K, V> map = new ConcurrentHashMap();

    private Type type;
    private String fileRepository;

    public ObjectWithIdRepositoryImpl(Type type, String fileRepository) {
        this.type = type;
        this.fileRepository = fileRepository;
        try {
            Path path = Paths.get(PATH + fileRepository);
            if (!Files.exists(path)) {
                Files.createFile(path);
                saveAll();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public List<V> getAll() {
        return this.map.values().stream().collect(Collectors.toUnmodifiableList());
    }

    public void readAll() throws IOException {
        synchronized(this) {
            this.map.clear();
            String json = Files.readString(Path.of(PATH + fileRepository));
            ArrayList<V> list = new Gson().fromJson(json, type);
            list.forEach((e) -> {
                this.map.put(e.getId(), e);
            });
        }
    }

    public void saveAll() throws IOException {
        synchronized(this) {
            String json = new Gson().toJson(this.getAll());
            Files.writeString(Path.of(PATH + fileRepository), json);
        }
    }

    @Override
    public void save(V item) {
        try {
            readAll();
            V value = this.map.put(item.getId(), item);
            if (value == null)
                saveAll();
            else
                System.out.println("Объект с ID=" + item.getId() + " уже существует в списке объектов. " +
                        "Попробуйте воспользоваться операцией UPDATE");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(V item) {
        try {
            readAll();
            V value = this.map.remove(item.getId());
            saveAll();
            if (value != null)
                save(item);
            else
                System.out.println("Объект с ID=" + item.getId() + " не существует в списке объектов. " +
                        "Воспользуйтесь операцией SAVE");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(K id) {
        try {
            readAll();
            V value = this.map.remove(id);
            if (value != null)
                saveAll();
            else
                System.out.println("Объект с ID=" + id + " не существует в списке объектов");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    @Override
    public List<V> get() {
        try {
            readAll();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return getAll();
    }

    @Override
    public V get(K id) {
        try {
            readAll();
            V value = this.map.get(id);
            if (value != null)
                return value;
            else
                System.out.println("Объект с ID=" + id + " не существует в списке объектов");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
