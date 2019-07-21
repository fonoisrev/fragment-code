package com.github.fonoisrev.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    
    private String txnId;
    
    private String amount;
    
    private String status;
    
    private String ipAddress;
}
