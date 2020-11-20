package com.example.refactoringstudy.domain.billing;

public interface BillingFactory {
    int billing(String playType, int audience);
    Billing createBilling(String playType);
}
