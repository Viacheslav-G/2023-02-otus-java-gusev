package ru.otus;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class Organiser {

    private HashMap<Task, String> tasks;

    public Organiser(){tasks = new HashMap<Task, String>();}

    public void addTask(Task task, String notes) {

        if (task.isCompleted() == true) {
            throw new IllegalArgumentException("Cannot add already completed task");
        }
        else tasks.put(task, notes);
    }
    public HashMap<Task, String> getAllTasks() {
        return tasks;
    }

    public HashMap<Task, String> getTasks() {
        return tasks;
    }

    public void printAllTasks(){

        for (Map.Entry<Task, String> entry : tasks.entrySet())
            System.out.println(entry.getKey() + ": " + entry.getValue());
    }
    public void clear(){
        tasks.clear();
    }



}
