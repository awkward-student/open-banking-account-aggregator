package com.account.aggregator.aggregatorservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountData {
    private String bankName;
    private String accountNumber;
    private String accountType;
    private double balance;
}
