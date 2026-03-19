package com.grind90.controller;

import com.grind90.service.NotificationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/email")
public class EmailTestController {

    private final NotificationService notificationService;

    public EmailTestController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/test")
    public String sendTestEmail() {

        notificationService.sendEmail(
                "rohitkumbhar1229@gmail.com",
                "Grind90 Test Email",
                "Your notification system is working!"
        );

        return "Email sent!";
    }
}