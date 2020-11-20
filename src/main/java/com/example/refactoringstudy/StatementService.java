package com.example.refactoringstudy;

import com.example.refactoringstudy.domain.Invoice;
import com.example.refactoringstudy.domain.Performance;
import com.example.refactoringstudy.domain.Play;
import com.example.refactoringstudy.domain.Statement;
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
        double totalAmount = 0;
        int volumeCredits = 0;
        List<Play> performedPlays = new ArrayList<>();

        for (Performance performance : invoice.getPerformances()) {
            Play play = plays.get(performance.getPlayID());
            performedPlays.add(play);

            totalAmount += billingFactory.billing(play.getType(), performance.getAudience());

            volumeCredits += Math.max(performance.getAudience() - 30, 0);

            if ("comedy".equals(play.getType())) {
                volumeCredits += Math.floor(performance.getAudience() / 5);
            }
        }

        Statement result = new Statement();
        result.setCustomer(invoice.getCustomer());
        result.setPlays(performedPlays);
        result.setTotalAmount(totalAmount / 100);
        result.setVolumeCredits(volumeCredits);
        return result;
    }
}
