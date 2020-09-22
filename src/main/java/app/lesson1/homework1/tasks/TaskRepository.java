package app.lesson1.homework1.tasks;

import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class TaskRepository extends ObjectWithIdRepositoryImpl<String, Task> {

    public TaskRepository() {
        super((new TypeToken<ArrayList<Task>>(){}).getType(), "task.json");
    }
}
