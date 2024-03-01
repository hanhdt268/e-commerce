package com.example.graduationbe.entities.commerce;

import com.paypal.api.payments.Transaction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDetails {

    private String id;
    private String state;
    private List<Transaction> transactions;
}
