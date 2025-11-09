package com.account.aggregator.common.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsentEvent {
    private String consentId;
    private String customerId;
    private String status;
}
