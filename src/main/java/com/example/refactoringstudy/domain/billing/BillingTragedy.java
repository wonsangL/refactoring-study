package com.example.refactoringstudy.domain.billing;

import org.springframework.stereotype.Component;

@Component
public class BillingTragedy implements Billing {
    @Override
    public int billing(int audience) {
        int result = 40000;
        if (audience > 30) {
            result += 1000 * audience - 30;
        }
        return result;
    }
}
