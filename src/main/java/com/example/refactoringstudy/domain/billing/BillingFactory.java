package com.example.refactoringstudy.domain.billing;

import com.example.refactoringstudy.domain.Bills;

public interface BillingFactory {
    Bills billing(String playType, int audience);
    Billing createBilling(String playType);
}
