package com.example.refactoringstudy;

import com.example.refactoringstudy.domain.Invoice;
import com.example.refactoringstudy.domain.Performance;
import com.example.refactoringstudy.domain.Play;
import com.example.refactoringstudy.domain.Statement;
import com.example.refactoringstudy.domain.billing.BillingFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class StatementServiceTest {
    @InjectMocks
    private StatementService statementService;

    @Mock
    private BillingFactory billingFactory;

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

        when(billingFactory.billing(anyString(), anyInt())).thenReturn(60000);

        Statement result = statementService.statement(invoice, plays);
        Assertions.assertAll(
                () -> Assertions.assertEquals("wonsangl", result.getCustomer()),
                () -> Assertions.assertEquals(expectPlays, result.getPlays()),
                () -> Assertions.assertEquals(600, result.getTotalAmount()),
                () -> Assertions.assertEquals(20, result.getVolumeCredits())
        );
    }

    //small audience에 대해서도 테스트 해야하는가?
    @Test
    void statementForComedyLargeAudienceTest() {
        List<Performance> performances = Collections.singletonList(new Performance("as-like", 50));
        invoice.setPerformances(performances);

        Map<String, Play> plays = Collections.singletonMap("as-like", new Play("As You Like It", "comedy"));
        List<Play> expectPlays = Collections.singletonList(new Play("As You Like It", "comedy"));

        when(billingFactory.billing(anyString(), anyInt())).thenReturn(70000);

        Statement result = statementService.statement(invoice, plays);
        Assertions.assertAll(
                () -> Assertions.assertEquals("wonsangl", result.getCustomer()),
                () -> Assertions.assertEquals(expectPlays, result.getPlays()),
                () -> Assertions.assertEquals(700, result.getTotalAmount()),
                () -> Assertions.assertEquals(30, result.getVolumeCredits())
        );
    }

    @Test
    void statementEmptyPerformanceTest() {

    }

    @Test
    void statementEmptyPlaysTest() {

    }
}