package model;

import java.util.List;

public interface SplitStrategy {

    List<Split> getUserSplit(List<String> userIds, List<Double> paidAmount, double totalAmount, List<Double> splitDetails);

}
