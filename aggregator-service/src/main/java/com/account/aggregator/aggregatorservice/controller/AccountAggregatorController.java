package com.account.aggregator.aggregatorservice.controller;

import com.account.aggregator.aggregatorservice.model.AccountData;
import com.account.aggregator.aggregatorservice.service.AccountAggregatorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/aggregate")
public class AccountAggregatorController {

    private RestTemplate restTemplate;

    private final AccountAggregatorService accountAggregatorService;

    public AccountAggregatorController(AccountAggregatorService accountAggregatorService) {
        this.accountAggregatorService = accountAggregatorService;
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<List<AccountData>> aggregateAccounts(
            @PathVariable("customerId") String customerId) {
        return ResponseEntity.ok(accountAggregatorService.aggregateAccounts(customerId));
    }

    @GetMapping("/aggregate/{customerId}")
    public ResponseEntity<List<Map<String, Object>>> aggregateCustomerData(@PathVariable String customerId) {
        List<Map<String, Object>> bankAData = restTemplate.getForObject("http://mockbank-a:8091/api/accounts/" + customerId, List.class);
        List<Map<String, Object>> bankBData = restTemplate.getForObject("http://mockbank-b:8092/api/accounts/" + customerId, List.class);

        List<Map<String, Object>> combined = new ArrayList<>();
        if (bankAData != null) combined.addAll(bankAData);
        if (bankBData != null) combined.addAll(bankBData);

        return ResponseEntity.ok(combined);
    }
}
