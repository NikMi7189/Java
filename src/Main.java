import java.util.ArrayList;
import java.util.Scanner;
class Task {
    private String description;
    private boolean isCompleted;

    public Task(String description) {
        this.description = description;
        this.isCompleted = false;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void markAsCompleted() {
        isCompleted = true;
    }

    @Override
    public String toString() {
        return "[" + (isCompleted ? "X" : " ") + "] " + description;
    }
}

class ToDoList {
    private ArrayList<Task> tasks;

    public ToDoList() {
        tasks = new ArrayList<>();
    }

    public void addTask(String description) {
        Task task = new Task(description);
        tasks.add(task);
    }

    public void markTaskAsCompleted(int index) {
        if (index >= 0 && index < tasks.size()) {
            Task task = tasks.get(index);
            task.markAsCompleted();
        } else {
            System.out.println("Неверный индекс задачи.");
        }
    }

    public void displayTasks() {
        System.out.println("Список задач:");
        if(tasks.size() == 0)
            System.out.println("Пуст");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("Задача №"+ (i + 1) + ". " + tasks.get(i));
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ToDoList toDoList = new ToDoList();
        int choise = 0;

        while (choise != 3)
        {
            toDoList.displayTasks();
            System.out.println("-------------------------------------------------");
            System.out.println("1 - Добавить задачу");
            System.out.println("2 - Отметить выполнение задачи");
            System.out.println("3 - Закончить работу");
            System.out.println("Введите номер действия которое хотите совершить:");

            if (input.hasNextInt())
            {
                choise = input.nextInt();
                switch (choise) {
                    case 1: {
                        String task = null;
                        System.out.println("Введите задачу:");
                        task = input.next();
                        toDoList.addTask(task);
                        break;
                    }
                    case 2: {
                        int index = 0;
                        System.out.println("Введите номер задачи который хотите убрать:");
                        index = input.nextInt();
                        toDoList.markTaskAsCompleted(index - 1);
                        break;
                    }
                    case 3: {
                        System.out.println("Всего доброго!");
                        break;
                    }
                    default: {
                        System.out.println("Был введён неверный номер действия!");
                        break;
                    }
                }
            }
            else
            {
                System.out.println("Было введено не число!");
            }
            System.out.println("-------------------------------------------------");
        }
    }
}