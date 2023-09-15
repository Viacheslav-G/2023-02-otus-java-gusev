package ru.otus;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;

public class Task {

    private boolean isUrgent;
    private boolean isImportant;
    private String description;

    private boolean completed;

    private int priority;

    private LocalDate deadline;


    public Task(boolean isUrgent, boolean isImportant, String description){
        this.isUrgent = isUrgent;
        this.isImportant = isImportant;
        this.description = description;
    }

    public Task(boolean isUrgent, boolean isImportant, String description, LocalDate deadline){
        this.isUrgent = isUrgent;
        this.isImportant = isImportant;
        this.description = description;
        //this.deadline = deadline;
          setDeadline(deadline);
    }

    public String getDescription() {
        return description;
    }

    public void setDeadline(LocalDate deadline) {

        if (deadline != null && deadline.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Deadline cannot be set to a date before the current date.");
        }
        this.deadline = deadline;
    }


    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
public String toString() {
        return "Task:"
                + description + ";" + (this.isUrgent  ? "Urgent;" : ";")
                + (this.isImportant ? "Important;" : ";")
                + "Deadline:" + this.deadline
                ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        return description.equals(task.description);
    }

    @Override
    public int hashCode() {
        return description.hashCode();
    }
}

