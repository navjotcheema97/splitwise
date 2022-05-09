package model;

public class PaidAmountDetails {

    private double amount;

    private String userId;

    public PaidAmountDetails(double amount, String userId){
        this.amount = amount;
        this.userId = userId;
    }

    public String getUser() {
        return userId;
    }

    public double getAmount() {
        return amount;
    }

    public void setUser(String userId) {
        this.userId = userId;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
