package com.account.aggregator.aggregatorservice.service;

import com.account.aggregator.aggregatorservice.model.AccountData;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountAggregatorService {

    public List<AccountData> aggregateAccounts(String customerId) {
        return List.of(
                new AccountData("BankOne", "SBIN12345", "Savings", 56000.75),
                new AccountData("Axis", "AXIS45678", "Current", 125000.00)
        );
    }
}
