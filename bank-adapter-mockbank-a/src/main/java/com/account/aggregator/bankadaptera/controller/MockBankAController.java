package com.account.aggregator.bankadaptera.controller;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class MockBankAController {

    @GetMapping("/{customerId}")
    public List<Map<String, Object>> getAccounts(@PathVariable String customerId) {
        return List.of(
                Map.of("bankName", "MockBank A", "accountNumber", "A12345", "accountType", "Savings", "balance", 50234.75)
        );
    }
}

