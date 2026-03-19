package com.grind90.service;

import com.grind90.entity.Task;
import com.grind90.repository.TaskRepository;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReminderScheduler {

    private final TaskRepository taskRepository;
    private final NotificationService notificationService;
    private final WhatsAppService whatsAppService;

    public ReminderScheduler(TaskRepository taskRepository,
                             NotificationService notificationService,
                             WhatsAppService whatsAppService) {

        this.taskRepository = taskRepository;
        this.notificationService = notificationService;
        this.whatsAppService = whatsAppService;
    }

  @Scheduled(cron = "0 0 9 * * *")
//@Scheduled(fixedRate = 60000)
    public void sendDailyReminder(){

        List<Task> pendingTasks = taskRepository.findByCompletedFalse();

        for(Task task : pendingTasks){

            String message =
                    "🔥 Grind90 Reminder\n\n" +
                            "Day " + task.getDayNumber() + " task pending:\n" +
                            task.getTaskText() +
                            "\n\nDon't break your streak!";

            notificationService.sendEmail(
                    "youremail@gmail.com",
                    "Grind90 Reminder 🔥",
                    message
            );

            whatsAppService.sendWhatsAppMessage(
                    "+918080304223",
                    message
            );
        }

        System.out.println("Daily reminders sent!");
    }
}