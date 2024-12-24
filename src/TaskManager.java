import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class TaskManager {
    private ArrayList<Task> tasks;

    public TaskManager() {
        tasks = new ArrayList<>();
    }

    // Add a new task
    public void addTask(Task task) {
        tasks.add(task);
    }

    // Remove a task
    public void removeTask(Task task) {
        tasks.remove(task);
    }

    // Get all tasks
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    // Sort tasks by priority (Low, Medium, High)
    public void sortTasksByPriority() {
        Collections.sort(tasks, new Comparator<Task>() {
            @Override
            public int compare(Task t1, Task t2) {
                // Priorities: Low < Medium < High
                return t1.getPriority().compareTo(t2.getPriority());
            }
        });
    }

    // Sort tasks by due date
    public void sortTasksByDueDate() {
        Collections.sort(tasks, new Comparator<Task>() {
            @Override
            public int compare(Task t1, Task t2) {
                return t1.getDueDate().compareTo(t2.getDueDate());
            }
        });
    }
}
