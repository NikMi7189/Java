import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        File file = new File("Tasks.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
                System.out.println("Файл 'Tasks.txt' был создан.");
            } catch (IOException e) {
                System.out.println("Не удалось создать файл 'Tasks.txt'.");
            }
        }

        Scanner input = new Scanner(System.in);
        ToDoList toDoList = new ToDoList("Tasks.txt");
        int choise = 0;

        while (choise != 6)
        {
            toDoList.displayTasks();
            System.out.println("1 - Добавить задачу");
            System.out.println("2 - Удалить задачу");
            System.out.println("3 - Изменить состояние задачи");
            System.out.println("4 - Сохранить задачи в файл");
            System.out.println("5 - Загрузить задачи из файла");
            System.out.println("6 - Закончить работу");
            System.out.println("----------------------------------------------------");
            System.out.print("Введите номер действия которое хотите совершить:");
            if (input.hasNextInt())
            {
                choise = input.nextInt();
                switch (choise) {
                    case 1: {
                        String task = null;
                        System.out.print("Введите описание задачи которую нужно добавить:");
                        task = input.next();
                        toDoList.addTask(task);
                        break;
                    }
                    case 2: {
                        System.out.print("Введите номер задачи которую хотите удалить:");
                        if (input.hasNextInt()) {
                            int index = input.nextInt();
                            toDoList.removeTask(index - 1);
                        } else {
                            System.out.println("Был введен неверный номер задачи.");
                        }
                        break;
                    }
                    case 3: {
                        int index = 0;
                        System.out.print("Введите номер задачи состояние которой вы хотите изменить:");
                        index = input.nextInt();
                        toDoList.markTaskAsCompleted(index - 1);
                        break;
                    }
                    case 4: {
                        toDoList.saveTasksToFile();
                        break;
                    }
                    case 5: {
                        toDoList.loadTasksFromFile();
                        break;
                    }
                    case 6: {
                        toDoList.saveTasksToFile();
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
        }
    }
}