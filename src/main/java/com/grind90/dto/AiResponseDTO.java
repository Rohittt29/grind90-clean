package com.grind90.dto;

public class AiResponseDTO {

    private String advice;

    public AiResponseDTO(String advice) {
        this.advice = advice;
    }

    public String getAdvice() {
        return advice;
    }
}