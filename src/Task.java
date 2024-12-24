public class Task {
    private String title;
    private String description;
    private String dueDate;
    private String priority; // Low, Medium, High
    private boolean isCompleted;

    // Constructor
    public Task(String title, String description, String dueDate, String priority) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
        this.isCompleted = false; // Default state
    }

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    @Override
    public String toString() {
        StringBuilder taskDetails = new StringBuilder(title);

        // Only append Due Date and Priority if they are not default values
        if (!dueDate.equals("No Date")) {
            taskDetails.append(" (Due: " + dueDate + ")");
        }

        if (!priority.equals("Medium")) {
            taskDetails.append(", Priority: " + priority);
        }

        return taskDetails.toString();
    }
}
