package com.example.refactoringstudy;

import com.example.refactoringstudy.domain.Invoice;
import com.example.refactoringstudy.domain.Performance;
import com.example.refactoringstudy.domain.Play;
import org.springframework.stereotype.Service;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

@Service
public class SampleService {

    public String statement(Invoice invoice, Map<String, Play> plays) {
        int totalAmount = 0;
        int volumeCredits = 0;
        StringBuilder result = new StringBuilder("청구 내역 (고객명: " + invoice.getCustomer() + ")\n");

        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.US);

        for (Performance performance : invoice.getPerformances()) {
            Play play = plays.get(performance.getPlayID());
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
                    throw new RuntimeException();
            }

            volumeCredits += Math.max(performance.getAudience() - 30, 0);

            if ("comedy".equals(play.getType())) {
                volumeCredits += Math.floor(performance.getAudience() / 5);
            }

            result.append(play.getName() + ": " + format.format(thisAmount / 100)
                    + " (" + performance.getAudience() + "석)\n");
            totalAmount += thisAmount;
        }

        result.append("총액: " + format.format(totalAmount / 100) + "\n");
        result.append("적립 포인트: " + volumeCredits + "점\n");
        return result.toString();
    }
}
