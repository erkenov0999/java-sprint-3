import manager.Manager;
import tasks.Epic;
import tasks.Subtask;
import tasks.Task;

public class Main {
    public static void main(String[] args) {
        Manager manager = new Manager();

        Task task = new Task("Task1", "Taska1", "NEW");
        int task1Id = manager.addNewTask(task);

        Task task2 = new Task("Task2", "Taska2", "NEW");
        int task2Id = manager.addNewTask(task2);

        Task task3 = new Task("Task3", "Taska3", "NEW");
        int task3Id = manager.addNewTask(task3);


        Epic epic = new Epic("Epic1", "Epics1");
        int epic1Id = manager.addNewEpic(epic);


        Subtask subtask = new Subtask("Subtask1", "Subtaska1", "NEW", epic.getId());
        int subtask1id = manager.addNewSubtask(subtask);

        Subtask subtask2 = new Subtask("Subtask2", "Subtaska2", "NEW", epic.getId());
        int subtask2id = manager.addNewSubtask(subtask2);
    }
}