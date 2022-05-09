package model;

public class RatioSplitStrategy implements SplitStrategy {

    @Override
    public Split getUserSplit(User user, double paidAmount, double totalAmount, double ratio) {
        return new Split(user, paidAmount, totalAmount*ratio);
    }

}
