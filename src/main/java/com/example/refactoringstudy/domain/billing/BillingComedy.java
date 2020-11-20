package com.example.refactoringstudy.domain.billing;

import org.springframework.stereotype.Component;

@Component
public class BillingComedy implements Billing {
    @Override
    public int billing(int audience) {
        int result = 30000;
        if (audience > 20) {
            result += 10000 + 500 * (audience - 20);
        }
        result += 300 * audience;
        return result;
    }
}
