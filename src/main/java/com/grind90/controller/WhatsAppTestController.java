package com.grind90.controller;

import com.grind90.service.WhatsAppService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/whatsapp")
public class WhatsAppTestController {

    private final WhatsAppService whatsAppService;

    public WhatsAppTestController(WhatsAppService whatsAppService) {
        this.whatsAppService = whatsAppService;
    }

    @GetMapping("/test")
    public String sendWhatsApp(){

        whatsAppService.sendWhatsAppMessage(
                "+918080304223",
                "🔥 Grind90 Test Message\nYour WhatsApp notification system is working!"
        );

        return "WhatsApp message sent!";
    }
}