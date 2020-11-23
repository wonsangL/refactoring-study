package com.example.refactoringstudy.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Bills {
    private double payAmount;
    private int volumeCredit;

    public Bills() {
        this.payAmount = 0;
        this.volumeCredit = 0;
    }

    public void addAmountAndCredit(Bills bills) {
        payAmount += bills.getPayAmount();
        volumeCredit += bills.getVolumeCredit();
    }
}
