package model;

public class PercentageSplitStrategy implements SplitStrategy {
    @Override
    public Split getUserSplit(User user, double paidAmount, double totalAmount, double percentage) {
        return new Split(user, paidAmount, (percentage* totalAmount)/100);
    }
}
