package com.grind90.ai;

import com.grind90.entity.Task;
import com.grind90.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import java.util.Map;

@Service
public class AIService {

    @Value("${gemini.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private TaskRepository taskRepository;

    public String generateRoadmap(String goal, int days) {

        System.out.println("Gemini API Key used: " + apiKey);

        String url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent?key=" + apiKey;

        String prompt =
                "Create a structured " + days + "-day learning plan for this goal: " + goal +
                        ". Return ONLY daily tasks in this format:\n" +
                        "Day 1: task\nDay 2: task\nDay 3: task\nDay 4: task\nDay 5: task";

        Map<String, Object> body = Map.of(
                "contents", new Object[]{
                        Map.of(
                                "parts", new Object[]{
                                        Map.of("text", prompt)
                                }
                        )
                }
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

        ResponseEntity<Map> response =
                restTemplate.postForEntity(url, request, Map.class);

        Map bodyMap = response.getBody();

        Map candidate = (Map) ((java.util.List) bodyMap.get("candidates")).get(0);
        Map content = (Map) candidate.get("content");
        Map part = (Map) ((java.util.List) content.get("parts")).get(0);

        String aiText = (String) part.get("text");

        // SPLIT RESPONSE INTO LINES
        String[] lines = aiText.split("\n");

        int day = 1;

        for (String line : lines) {

            if (line.trim().isEmpty()) continue;

            Task task = new Task(day, line, null);

            taskRepository.save(task);

            day++;
        }

        return aiText;
    }
}