package com.example.refactoringstudy;

import com.example.refactoringstudy.domain.*;
import com.example.refactoringstudy.domain.billing.BillingFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class StatementService {
    private final BillingFactory billingFactory;

    public StatementService(@Autowired BillingFactory billingFactory){
        this.billingFactory = billingFactory;
    }

    public Statement statement(Invoice invoice, Map<String, Play> plays) {
        Bills totalBills = new Bills();
        List<Play> performedPlays = new ArrayList<>();

        invoice.getPerformances().stream()
                .filter(performance -> performance.getAudience() > 0)
                .forEach(performance -> {
                    totalBills.addAmountAndCredit(billingFactory.billing(performance, plays));
                    performedPlays.add(plays.get(performance.getPlayID()));
                });

        Statement result = new Statement();
        result.setCustomer(invoice.getCustomer());
        result.setPlays(performedPlays);
        result.setTotalAmount(totalBills.getPayAmount() / 100);
        result.setVolumeCredits(totalBills.getVolumeCredit());
        return result;
    }
}
