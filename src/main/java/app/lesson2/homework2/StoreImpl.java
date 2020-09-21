package app.lesson2.homework2;

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

public class StoreImpl<K,V extends AccountInterface<K>> implements Store<V> {
    private final static String DB_PATH = "C:\\Users\\Эльдорадо\\IdeaProjects\\spring-progwards\\accounts.json";
    private final Map<K,V> mapValues = new ConcurrentHashMap<>();
    private final Type type;

    public StoreImpl(Type type) {
        this.type = type;
        try {
            Path path = Paths.get(DB_PATH);
            if (!Files.exists(path))
                Files.createFile(path);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public Map<K, V> getMapValues() {
        return mapValues;
    }

    @Override
    public void write(V item) {
        synchronized (this) {
            read();
            mapValues.put(item.getId(), item);
            List<V> listValues = new ArrayList<>(mapValues.values());
            String json = new Gson().toJson(listValues);
            try {
                Files.writeString(Paths.get(DB_PATH), json);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<V> read() {
        synchronized (this) {
            try {
                String json = Files.readString(Paths.get(DB_PATH));
                List<V> listValues = new ArrayList<>();
                if (json.equals(""))
                    return listValues;
                listValues = new Gson().fromJson(json, type);
                listValues.forEach(e -> mapValues.put(e.getId(), e));
                return listValues;
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            return null;
        }
    }
}