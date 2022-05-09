package model;

import java.util.List;

public class ExactSplitStrategy implements SplitStrategy {

    @Override
    public List<Split> getUserSplit(List<String> userIds, List<Double> paidAmount, double totalAmount, List<Double> splitDetails) {
        return null;
    }
}
