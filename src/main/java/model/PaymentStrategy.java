package model;

import java.util.List;

public interface PaymentStrategy {

    List<PaidAmountDetails> getPaidAmountDetails(String[] inputArray, String[] userIds);

}
