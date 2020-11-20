package com.example.refactoringstudy;

import com.example.refactoringstudy.domain.Invoice;
import com.example.refactoringstudy.domain.Performance;
import com.example.refactoringstudy.domain.Play;
import com.example.refactoringstudy.domain.Statement;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class StatementService {

    public Statement statement(Invoice invoice, Map<String, Play> plays) {
        double totalAmount = 0;
        int volumeCredits = 0;
        List<Play> performedPlays = new ArrayList<>();

        for (Performance performance : invoice.getPerformances()) {
            Play play = plays.get(performance.getPlayID());
            performedPlays.add(play);

            int thisAmount = 0;

            switch (play.getType()) {
                case "tragedy":
                    thisAmount = 40000;
                    if (performance.getAudience() > 30) {
                        thisAmount += 1000 * (performance.getAudience() - 30);
                    }
                    break;
                case "comedy":
                    thisAmount = 30000;
                    if (performance.getAudience() > 20) {
                        thisAmount += 10000 + 500 * (performance.getAudience() - 20);
                    }
                    thisAmount += 300 * performance.getAudience();
                    break;
                default:
                    throw new RuntimeException("알 수 없는 장르:" + play.getType());
            }

            volumeCredits += Math.max(performance.getAudience() - 30, 0);

            if ("comedy".equals(play.getType())) {
                volumeCredits += Math.floor(performance.getAudience() / 5);
            }
            totalAmount += thisAmount;
        }

        Statement result = new Statement();
        result.setCustomer(invoice.getCustomer());
        result.setPlays(performedPlays);
        result.setTotalAmount(totalAmount / 100);
        result.setVolumeCredits(volumeCredits);
        return result;
    }
}
