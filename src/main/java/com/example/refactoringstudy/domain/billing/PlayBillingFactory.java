package com.example.refactoringstudy.domain.billing;

import com.example.refactoringstudy.domain.Bills;
import com.example.refactoringstudy.domain.Performance;
import com.example.refactoringstudy.domain.Play;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class PlayBillingFactory implements BillingFactory {
    private final BillingTragedy billingTragedy;
    private final BillingComedy billingComedy;

    public PlayBillingFactory(@Autowired BillingTragedy billingTragedy, @Autowired BillingComedy billingComedy) {
        this.billingTragedy = billingTragedy;
        this.billingComedy = billingComedy;
    }

    @Override
    public Bills billing(Performance performance, Map<String, Play> plays) {
        return createBilling(plays.get(performance.getPlayID()).getType()).billing(performance.getAudience());
    }

    @Override
    public Billing createBilling(String playType) {
        switch (playType) {
            case "comedy":
                return billingComedy;
            case "tragedy":
                return billingTragedy;
        }
        throw new RuntimeException("알 수 없는 장르:" + playType);
    }
}
