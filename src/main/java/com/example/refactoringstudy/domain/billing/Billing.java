package com.example.refactoringstudy.domain.billing;

import com.example.refactoringstudy.domain.Bills;

public abstract class Billing {
    Bills billing(int audience) {
        return new Bills(getAmount(audience), getCredit(audience));
    }

    abstract double getAmount(int audience);

    int getCredit(int audience) {
        return Math.max(audience - 30, 0);
    }
}
