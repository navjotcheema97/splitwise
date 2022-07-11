package com.splitwise.services.paymentstrategy;

import com.splitwise.models.Expense;

public interface PaymentStrategy {
    void calculateAmountsPaid(Expense expense);
}

