package com.example.refactoringstudy.domain;

import lombok.Data;

import java.util.List;

@Data
public class Statement {
    private String customer;
    private List<Play> plays;
    private double totalAmount;
    private int volumeCredits;
}
