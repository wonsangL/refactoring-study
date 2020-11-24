package com.example.refactoringstudy.domain.billing;

import com.example.refactoringstudy.domain.Bills;
import com.example.refactoringstudy.domain.Performance;
import com.example.refactoringstudy.domain.Play;

import java.util.Map;

public interface BillingFactory {
    Bills billing(Performance performance, Map<String, Play> plays);
    Billing createBilling(String playType);
}
