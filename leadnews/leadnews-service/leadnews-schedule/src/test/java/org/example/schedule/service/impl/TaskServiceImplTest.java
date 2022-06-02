package org.example.schedule.service.impl;

import org.example.model.schedule.dtos.Task;
import org.example.schedule.ScheduleApplication;
import org.example.schedule.service.TaskService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.*;

@SpringBootTest(classes = ScheduleApplication.class)
@RunWith(SpringRunner.class)
public class TaskServiceImplTest {

    @Autowired
    private TaskService taskService;

    @Test
    public void addTask() {

        long taskId = 0;
        for (int i = 0;i<5;++i) {
            Task task = new Task();
            task.setTaskType(100+i);
            task.setPriority(50);
            task.setParameters("task test".getBytes());
            task.setExecuteTime(new Date().getTime()+500*i);

            taskId = taskService.addTask(task);
        }

        System.out.println(taskId);
    }

    @Test
    public void cancelTask(){
        taskService.cancelTask(1528244198568939521L);
    }

    @Test
    public void testPoll(){
        Task task = taskService.poll(100, 50);
        System.out.println(task);
    }


}