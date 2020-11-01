package com.example.refactoringstudy.dto;

import com.example.refactoringstudy.domain.Invoice;
import com.example.refactoringstudy.domain.Play;
import lombok.Data;

import java.util.Map;

@Data
public class StatementDto {
    private Map<String, Play> plays;
    private Invoice invoice;
}
