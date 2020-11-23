package com.example.refactoringstudy.domain.billing;

import org.springframework.stereotype.Component;

@Component
public class BillingComedy extends Billing {
    private final int PER_AUDIENCE = 5;

    @Override
    public double getAmount(int audience) {
        double result = 30000;
        if (audience > 20) {
            result += 10000 + 500 * (audience - 20);
        }
        result += 300 * audience;
        return result;
    }

    @Override
    int getCredit(int audience) {
        return super.getCredit(audience) + (int)Math.floor(audience / PER_AUDIENCE);
    }
}
