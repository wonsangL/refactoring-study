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
    private BillingFactory billingFactory;

    public StatementService(@Autowired BillingFactory billingFactory){
        this.billingFactory = billingFactory;
    }

    public Statement statement(Invoice invoice, Map<String, Play> plays) {
        Bills totalBills = new Bills();

        List<Play> performedPlays = new ArrayList<>();

        for (Performance performance : invoice.getPerformances()) {
            Play play = plays.get(performance.getPlayID());
            performedPlays.add(play);

            Bills bills = billingFactory.billing(play.getType(), performance.getAudience());
            totalBills.addAmountAndCredit(bills);
        }

        Statement result = new Statement();
        result.setCustomer(invoice.getCustomer());
        result.setPlays(performedPlays);
        result.setTotalAmount(totalBills.getPayAmount() / 100);
        result.setVolumeCredits(totalBills.getVolumeCredit());
        return result;
    }
}
