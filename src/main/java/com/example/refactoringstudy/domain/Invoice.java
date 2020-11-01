package com.example.refactoringstudy.domain;

import lombok.Data;

import java.util.List;

@Data
public class Invoice {
    private String customer;
    private List<Performance> performances;
}
