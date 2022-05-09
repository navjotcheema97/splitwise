package model;

import java.util.ArrayList;
import java.util.List;

public class EqualSplitStrategy implements SplitStrategy {

    @Override
    public List<Split> getUserSplit(List<String> userIds, List<Double> paidAmount, double totalAmount, List<Double> splitDetails) {

        List<Split> splits = new ArrayList<>();

        for( int i=0; i< userIds.size(); i++ ){
            double paidAmountForUser = (paidAmount.size() - 1 >= i) ? paidAmount.get(i) : 0.0;
            double owedAmount = totalAmount/userIds.size();
            Split split = new Split(userIds.get(i), paidAmountForUser, owedAmount);
            splits.add(split);
        }

        //Validate amount for point //Can have it in parent
        return splits;
    }
}
