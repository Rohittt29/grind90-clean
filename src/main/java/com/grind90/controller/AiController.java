package com.grind90.controller;

import com.grind90.dto.AiRequestDTO;
import com.grind90.dto.AiResponseDTO;
import com.grind90.service.AiService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai")
public class AiController {

    private final AiService aiService;

    public AiController(AiService aiService) {
        this.aiService = aiService;
    }

    @PostMapping("/advice")
    public AiResponseDTO getAdvice(@RequestBody AiRequestDTO request) {

        String advice = aiService.generateAdvice(request);

        return new AiResponseDTO(advice);
    }
}