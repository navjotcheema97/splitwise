package model;

public class Split {

    private String userID;

    private double paidAmount;

    private double ownedAmount;

    public Split(String userID, double paidAmount, double ownedAmount){
        this.ownedAmount = ownedAmount;
        this.paidAmount = paidAmount;
        this.userID = userID;
    }

    public double getOwnedAmount() {
        return ownedAmount;
    }

    public double getPaidAmount() {
        return paidAmount;
    }

    public String getUser() {
        return userID;
    }

    public void setPaidAmount(double paidAmount) {
        this.paidAmount = paidAmount;
    }

    public void setUser(String  userID) {
        this.userID = userID;
    }

    public void setOwnedAmount(double ownedAmount) {
        this.ownedAmount = ownedAmount;
    }
}
