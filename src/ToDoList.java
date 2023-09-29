import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

class ToDoList {
    private ArrayList<Task> tasks;
    private String filename;

    public ToDoList(String filename) {
        this.tasks = new ArrayList<>();
        this.filename = filename;
        loadTasksFromFile();
    }
    public void addTask(String description) {
        Task task = new Task(description);
        tasks.add(task);
    }
    public void removeTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            Task removedTask = tasks.remove(index);
            System.out.println("Задача " + removedTask.getDescription() + " была успешно удалена");
        } else {
            System.out.println("Был введен неверный номер задачи.");
        }
    }
    public void markTaskAsCompleted(int index) {
        if (index >= 0 && index < tasks.size()) {
            Task task = tasks.get(index);
            if (task.isCompleted()) {
                task.markAsUncompleted();
                System.out.println("Задача " + task.getDescription() + " теперь помечена как не выполненная");
            } else {
                task.markAsCompleted();
                System.out.println("Задача " + task.getDescription() + " теперь помечена как выполненная");
            }
        } else {
            System.out.println("Был введен неверный номер задачи.");
        }
    }
    public void displayTasks() {
        System.out.println("----------------------------------------------------");
        System.out.println("Список задач:");
        System.out.println("----------------------------------------------------");
        if(tasks.size() == 0)
            System.out.println("Пуст");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("Задача №"+ (i + 1) + ". " + tasks.get(i));
        }
        System.out.println("----------------------------------------------------");
    }
    public void saveTasksToFile() {
        try (PrintWriter writer = new PrintWriter(filename)) {
            for (Task task : tasks) {
                writer.println(task.getDescription() + "," + task.isCompleted());
            }
            System.out.println("Данные были успешно сохранены в файл " + filename);
        } catch (IOException e) {
            System.out.println("Возникла ошибка при сохранении данных из файла");
        }
    }
    public void loadTasksFromFile() {
        tasks.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String description = parts[0];
                    boolean isCompleted = Boolean.parseBoolean(parts[1]);
                    Task task = new Task(description);
                    if (isCompleted) {
                        task.markAsCompleted();
                    }
                    tasks.add(task);
                }
            }
            System.out.println("Данные были успешно загружены из файла " + filename);
        } catch (IOException e) {
            System.out.println("Возникла ошибка при загрузке данных из файла");
        }
    }
}