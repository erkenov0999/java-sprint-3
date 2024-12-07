package manager;

import tasks.Epic;
import tasks.Subtask;
import tasks.Task;

import java.util.HashMap;

public class Manager { // Данный класс отвечает за управление всеми задачами
    private HashMap<Integer, Task> tasks = new HashMap<>(); // Храним задачи
    private HashMap<Integer, Epic> epics = new HashMap<>(); // Храним эпики
    private HashMap<Integer, Subtask> subtasks = new HashMap<>(); // Храним подзадачи эпиков
    private int generateId = 1;


    private  Integer generateId() { //Генерирует новый id
        return generateId++;
    }

    public Integer addNewTask(Task task) { // Добавление и сохранение новой ЗАДАЧИ
        task.setId(generateId());
        tasks.put(task.getId(), task);

        return task.getId();
    }

    public Integer addNewEpic(Epic epic) { // Добавление и сохранение нового ЭПИКА
        epic.setId(generateId());
        epics.put(epic.getId(), epic);

        return epic.getId();
    }

    public Integer addNewSubtask(Subtask subtask) { // Добавление и сохранение новой ПОДЗАДАЧИ
        Epic epic = epics.get(subtask.getEpicId());
        if(epic == null) {
            System.out.println("Нельзя добавить подзадачу к несуществующему Эпику");
            return null;
        } else {
            subtask.setId(generateId());
            epic.addSubtaskId(subtask.getId(), subtask);
            subtasks.put(subtask.getId(), subtask);
            return subtask.getId();
        }
    }

    /*
    На данном этапе мы выполняем манипуляции
    со всеми видами задач
     */
    public String getAllTasks(Object object) { // Понимает какой вид задачи надо полностью вывести и выводит
        if (object.equals(tasks)) {
            return tasks.entrySet().toString();
        } else if (object.equals(epics)) {
            return epics.entrySet().toString();
        } else {
            return subtasks.entrySet().toString();
        }
    }

    public void removeAllTasks(Object object) { // Понимает какой вид задачи надо полностью удалить и удаляет
        if(object.equals(tasks)) {
            tasks.clear();
        } else if (object.equals(epics)) {
            epics.clear();
        } else {
            subtasks.clear();
        }
    }

    public String getById(Object object, int id) { // Понимает какой вид задачи надо получить по ID и выдает его
        if (object.equals(tasks)) {                       // при условии что ID введен правильно
            if(tasks.containsKey(id)) {
                return tasks.get(id).toString();
            } else {
                return "Задачи с ID " + id + " не существует, проверьте правильность идентификатора!";
            }
        } else if (object.equals(epics)) {
            if (epics.containsKey(id)) {
                return epics.get(id).toString();
            } else {
                return "Эпика с ID " + id + " не существует, проверьте правильность идентификатора!";
            }
        } else if (object.equals(subtasks)){
            if (subtasks.containsKey(id)) {
                return subtasks.get(id).toString();
            } else {
                return "Сабтаски с ID " + id + " не существует, проверьте правильность идентификатора!";
            }
        }
        return "Проверьте корректность введенных данных";
    }

    public void removeById(Object object, int id) { // Понимает какой вид задачи получен и по ID удаляет его,
        if (object.equals(tasks)) {                            // при условии что пройдет проверку
            if(tasks.containsKey(id)) {
                tasks.remove(id);
                System.out.println("Задача с ID " + id + " удалена!" );
            } else {
                System.out.println("Задачи с ID " + id + " не существует, проверьте правильность идентификатора!");
            }
        } else if (object.equals(epics)) {
            if (epics.containsKey(id)) {
                epics.remove(id);
                System.out.println("Эпик с ID " + id + " удален!" );
            } else {
                System.out.println("Эпика с ID " + id + " не существует, проверьте правильность идентификатора!");
            }
        } else if (object.equals(subtasks)){
            if (subtasks.containsKey(id)) {
                subtasks.remove(id);
                System.out.println("Сабтаска с ID " + id + " удалена!" );
            } else {
                System.out.println("Сабтаски с ID " + id + " не существует, проверьте правильность идентификатора!");
            }
        }
    }

    public HashMap<Integer, Subtask> subtasksEpic (int epicId) { // Выводим все САБТАСКИ определенного ЭПИКА
        if (epics.containsKey(epicId)) {
            return epics.get(epicId).getSubtasksId();
        } else {
            System.out.println("Эпика с идентифкатором " + epicId + " не существует!");
            return null;
        }
    }

    public void updateTaskById(Object object, int id, Object newObject) { // Обновление объекта по ID
        if (object.equals(tasks)) {
            if (tasks.containsKey(id)) {
                Task updatedTask = (Task) newObject; // Приводим newObject к типу Task
                if (updatedTask.getId() == id) {
                    tasks.put(id, updatedTask); // Обновляем задачу в Map
                    System.out.println("Задача с ID " + id + " обновлена!");
                } else {
                    System.out.println("ID новой задачи не совпадает с ID в параметре!");
                }
            } else {
                System.out.println("Задачи с ID " + id + " не существует!");
            }
        } else if (object.equals(epics)) {
            if (epics.containsKey(id)) {
                Epic updatedEpic = (Epic) newObject; // Приводим newObject к типу Epic
                if (updatedEpic.getId() == id) {
                    epics.put(id, updatedEpic); // Обновляем эпик в Map
                    System.out.println("Эпик с ID " + id + " обновлён!");
                } else {
                    System.out.println("ID нового эпика не совпадает с ID в параметре!");
                }
            } else {
                System.out.println("Эпика с ID " + id + " не существует!");
            }
        } else if (object.equals(subtasks)) {
            if (subtasks.containsKey(id)) {
                Subtask updatedSubtask = (Subtask) newObject; // Приводим newObject к типу Subtask
                if (updatedSubtask.getId() == id) {
                    subtasks.put(id, updatedSubtask); // Обновляем подзадачу в Map
                    System.out.println("Подзадача с ID " + id + " обновлена!");
                } else {
                    System.out.println("ID новой подзадачи не совпадает с ID в параметре!");
                }
            } else {
                System.out.println("Подзадачи с ID " + id + " не существует!");
            }
        } else {
            System.out.println("Неверный объект для обновления!");
        }
    }

    public void updateEpic(Epic epic, int id) {
        int statusNew = 0;
        int statusInProgress = 0;
        int statusDone = 0;

        // Перебор всех подзадач по ключу
        for (Integer key : epic.getSubtasksId().keySet()) {
            Subtask subtask = epic.getSubtasksId().get(key); // Получаем подзадачу по ключу
            if (subtask.getStatus().equals("NEW")) {
                statusNew++;
            }
            if (subtask.getStatus().equals("IN_PROGRESS")) {
                statusInProgress++;
            }
            if (subtask.getStatus().equals("DONE")) {
                statusDone++;
            }
        }

        // Логика для обновления статуса эпика
        if (statusNew == 0 && statusInProgress == 0) {
            epic.setStatus("DONE");
        } else if (statusInProgress == 0 && statusDone == 0 && !epic.getSubtasksId().isEmpty()) {
            epic.setStatus("NEW");
        } else {
            epic.setStatus("IN_PROGRESS");
        }
    }


    public HashMap<Integer, Task> getTasks() {
        return tasks;
    }

    public HashMap<Integer, Epic> getEpics() {
        return epics;
    }

    public HashMap<Integer, Subtask> getSubtasks() {
        return subtasks;
    }


}