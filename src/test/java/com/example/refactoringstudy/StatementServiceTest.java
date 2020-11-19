package com.example.refactoringstudy;

import com.example.refactoringstudy.domain.Invoice;
import com.example.refactoringstudy.domain.Performance;
import com.example.refactoringstudy.domain.Play;
import com.example.refactoringstudy.domain.Statement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
class StatementServiceTest {
    @InjectMocks
    private StatementService statementService;

    private Invoice invoice;

    @BeforeEach
    void setup() {
        invoice = new Invoice();
        invoice.setCustomer("wonsangl");
    }

    @Test
    void statementForTragedyLargeAudienceTest() {
        List<Performance> performances = Collections.singletonList(new Performance("hamlet", 50));
        invoice.setPerformances(performances);

        Map<String, Play> plays = Collections.singletonMap("hamlet", new Play("Hamlet", "tragedy"));
        List<Play> expectPlays = Collections.singletonList(new Play("Hamlet", "tragedy"));

        Statement result = statementService.statement(invoice, plays);
        Assertions.assertAll(
                () -> Assertions.assertEquals("wonsangl", result.getCustomer()),
                () -> Assertions.assertEquals(expectPlays, result.getPlays()),
                () -> Assertions.assertEquals(600, result.getTotalAmount()),
                () -> Assertions.assertEquals(20, result.getVolumeCredits())
        );
    }
}