package model;

import java.util.Arrays;
import java.util.List;

public class IPayPaymentStrategy implements PaymentStrategy {
    @Override
    public List<PaidAmountDetails> getPaidAmountDetails(String[] inputArray, String[] userIds) {
        double amount = Double.parseDouble(inputArray[3]);
        PaidAmountDetails[] amountDetailsArray = new PaidAmountDetails[] {new PaidAmountDetails(amount, userIds[0])};
        return Arrays.asList(amountDetailsArray);
    }
}
