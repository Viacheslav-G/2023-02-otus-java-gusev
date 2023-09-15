package ru.otus;

import java.time.LocalDate;
import java.util.Map;

public class OrganiserTest {

    private Organiser organiser;

    @Before
    public void testSetup() {
        organiser = new Organiser();

    }


    @After
    public void printAllTasks(){

        for (Map.Entry<Task, String> entry : organiser.getTasks().entrySet())
            System.out.println(entry.getKey() + ": " + entry.getValue());
    }

    @After
    public void cleanup() {
        organiser.clear();
    }

    @Test
    public void testTaskConsructor(){
        organiser.addTask(new Task(true, true, "Create your own test framework"), "OTUS Homework 3");
    }

    @Test
    public void testAddTaskAndPrintAll(){
        organiser.addTask(new Task(true, true, "Write a short essay in English"), "IELTS preparation");
        organiser.printAllTasks();
    }

    @Test
    public void testSetDeadlineNow() {
        if (organiser.getTasks().size() > 0) {
            Task anyTask = organiser.getTasks().keySet().iterator().next();
            anyTask.setDeadline(LocalDate.now());
        }
        else{
            organiser.addTask(new Task(true, true, "Write a short essay in English"), "IELTS preparation");
            Task anyTask = organiser.getTasks().keySet().iterator().next();
            anyTask.setDeadline(LocalDate.now());
        }

    }
    @Test
    public void testSetDeadlineBeforeNow(){
        if (organiser.getTasks().size() > 0){
            Task anyTask = organiser.getTasks().keySet().iterator().next();
            anyTask.setDeadline(LocalDate.now().minusDays(1));
        }
        else{
            organiser.addTask(new Task(true, true, "Write a short essay in English"), "IELTS preparation");
            Task anyTask = organiser.getTasks().keySet().iterator().next();
            anyTask.setDeadline(LocalDate.now().minusDays(1));
        }

    }


}
