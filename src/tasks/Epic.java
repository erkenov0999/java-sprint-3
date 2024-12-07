package tasks;

import java.util.ArrayList;
import java.util.HashMap;

public class Epic extends Task {
    private HashMap<Integer, Subtask> subtasksId;

    public Epic(String title, String description) {
        super(title, description, "NEW");
        this.subtasksId = new HashMap<>();
    }

    public HashMap<Integer, Subtask> getSubtasksId() {
        return subtasksId;
    }

    public void setSubtasksId(HashMap<Integer, Subtask> subtasksId) {
        this.subtasksId = subtasksId;
    }

    public void addSubtaskId(int subtaskId, Subtask subtask) {
        subtasksId.put(subtaskId, subtask);
    }
}
