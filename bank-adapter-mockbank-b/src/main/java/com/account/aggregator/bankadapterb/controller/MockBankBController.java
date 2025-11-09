package com.account.aggregator.bankadapterb.controller;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class MockBankBController {

    @GetMapping("/{customerId}")
    public List<Map<String, Object>> getAccounts(@PathVariable String customerId) {
        return List.of(
                Map.of("bankName", "MockBank B", "accountNumber", "B98765", "accountType", "Current", "balance", 128900.50)
        );
    }
}

