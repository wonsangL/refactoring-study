package com.example.refactoringstudy.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Performance {
    private String playID;
    private int audience;
}
