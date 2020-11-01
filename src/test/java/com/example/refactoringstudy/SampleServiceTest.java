package com.example.refactoringstudy;

import com.example.refactoringstudy.domain.Invoice;
import com.example.refactoringstudy.domain.Performance;
import com.example.refactoringstudy.domain.Play;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
class SampleServiceTest {
    @InjectMocks
    private SampleService sampleService;

    @Test
    public void statementTest() {
        Invoice invoice = new Invoice();
        invoice.setCustomer("wonsangl");

        //allArgConstructor for test?
        List<Performance> performances = Collections.singletonList(new Performance("hamlet", 50));
        invoice.setPerformances(performances);

        Map<String, Play> plays = Collections.singletonMap("hamlet", new Play("Hamlet", "tragedy"));

        String result = sampleService.statement(invoice, plays);
        String expectedResult = "청구 내역 (고객명: wonsangl)\n" +
                "Hamlet: $600.00 (50석)\n" +
                "총액: $600.00\n" +
                "적립 포인트: 20점\n";

        Assertions.assertEquals(expectedResult, result);
    }
}