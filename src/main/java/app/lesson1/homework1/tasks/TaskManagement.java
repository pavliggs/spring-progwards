package app.lesson1.homework1.tasks;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.Scanner;

public class TaskManagement {
    final static private List<String> operations = List.of("1. Добавить задачу", "2. Изменить задачу", "3. Удалить задачу",
            "4. Вывести список задач на экран");
    private static TaskRepository taskRepository;

    public static void init() {
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        taskRepository = context.getBean(TaskRepository.class);
    }

    public static void main() {
        init();
        System.out.println("Выберите действие (введите номер операции):");
        operations.forEach(System.out::println);
        Scanner scanner = new Scanner(System.in);
        String string = "";
        while (scanner.hasNextLine()) {
            string = scanner.nextLine();
            if (string.trim().equals("1"))
                createAndAddTask(scanner);
            else if (string.trim().equals("2"))
                updateTask(scanner);
            else if (string.trim().equals("3"))
                deleteTask(scanner);
            else if (string.trim().equals("4")) {
                snowListTask(scanner);
            } else
                System.out.println("Операции " + string + " не существует");
        }
    }

    private static void snowListTask(Scanner scanner) {
        List<Task> taskList = taskRepository.get();
        taskList.forEach(e -> {
            System.out.println(e + "\n");
        });
    }

    private static void deleteTask(Scanner scanner) {
        System.out.println("Введите ID задачи, которую хотите удалить:");
        String id = scanner.nextLine();
        Task task = taskRepository.get(id);
        if (task != null) {
            taskRepository.delete(id);
            System.out.println("Задача найдена и успешно удалена");
        }
    }

    private static void updateTask(Scanner scanner) {
        System.out.println("Введите ID задачи, которую хотите изменить:");
        String id = scanner.nextLine();
        Task task = taskRepository.get(id);
        if (task != null) {
            System.out.println("Задача найдена. Если не хотите менять какие-то данные, то просто нажмите ENTER");
        } else return;
        System.out.println("Введите новое описание задачи:");
        String description = scanner.nextLine();
        if (!description.trim().equals(""))
            task.setDescription(description);
        System.out.println("Введите нового автора задачи:");
        String author = scanner.nextLine();
        if (!author.trim().equals(""))
            task.setAuthor(author);
        System.out.println("Введите новое имя задачи:");
        String name = scanner.nextLine();
        if (!name.trim().equals(""))
            task.setName(name);
        System.out.println("Введите новый story point задачи:");
        String storyPointString = scanner.nextLine();
        if (!storyPointString.trim().equals("")) {
            task.setStoryPoint(Integer.parseInt(storyPointString));
        }
        taskRepository.update(task);
    }

    private static void createAndAddTask(Scanner scanner) {
        System.out.println("Введите уникальный ID (идентификатор) задачи:");
        String id = scanner.nextLine();
        System.out.println("Введите описание задачи:");
        String description = scanner.nextLine();
        System.out.println("Введите имя и фамилию автора задачи:");
        String author = scanner.nextLine();
        System.out.println("Введите имя задачи:");
        String name = scanner.nextLine();
        System.out.println("Введите story point задачи (должно быть число):");
        int storyPoint = Integer.parseInt(scanner.nextLine());
        Task task = new Task(id, description, author, name, storyPoint);
        taskRepository.save(task);
    }

    public static void main(String[] args) {
        main();
    }
}
