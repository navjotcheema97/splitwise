import model.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Driver {

    private String paymentString;
    private String splitString;

    private void setStrings(String[] inputArray){
        List<String> inputList = Arrays.asList(inputArray);
        if(inputList.contains(Constants.IPay)){
            this.paymentString = Constants.IPay;
        }
        else if(inputList.contains(Constants.MultiPay)){
            this.paymentString = Constants.MultiPay;
        }

        if(inputList.contains(Constants.Equal)){
            this.splitString = Constants.Equal;
        }
        else if(inputList.contains(Constants.Exact)){
            this.splitString = Constants.Exact;
        }
        else if(inputList.contains(Constants.Ratio)){
            this.splitString = Constants.Ratio;
        }
        else if(inputList.contains(Constants.Percent)){
            this.splitString = Constants.Percent;
        }

    }

    void processInput(String inputString){

        String[] inputArray = inputString.split(" ");

        String action = inputArray[1];
        String rootUserId = inputArray[0];
        List<String> userIds = new ArrayList<>();
        List<Double> paidAmountArray = new ArrayList<>();
        List<Double> splitDetails = new ArrayList<>();
        userIds.add(rootUserId);
        switch (action) {

            case "Expense":
                this.setStrings(inputArray);
                int paymentTypeIndex = Arrays.asList(inputArray).indexOf(this.paymentString);
                int splitTypeIndex = Arrays.asList(inputArray).indexOf(this.splitString);
                int descIndex = Arrays.asList(inputArray).indexOf("Desc");
                double totalPaidAmount = 0.0;

                String descString = inputString.split("Desc")[1];

                for(int i=2; i<paymentTypeIndex; i++){
                    userIds.add(inputArray[i]);
                }

                for(int i= paymentTypeIndex +1; i< splitTypeIndex; i++){
                    double amount = Double.parseDouble(inputArray[i]);
                    totalPaidAmount+= amount;
                    paidAmountArray.add(amount);
                }

                for(int i= splitTypeIndex +1; i< descIndex; i++){
                    splitDetails.add(Double.parseDouble(inputArray[i]));
                }

                SplitStrategy splitStrategy = (new SplitStrategyFactory()).getSplitStrategy(this.splitString);

                List<Split> userWiseSplit = splitStrategy.getUserSplit(userIds,paidAmountArray, totalPaidAmount, splitDetails);




                //Find userIds
                //Create payment Strategy that will take payment string part as input and return map<userId, amountPaid>
                //Create split Strategy that will take TotalAmount and splitstring as input and return the array of oew amount

                //The Create list of splits by looping over users
                //Create the expense object and store in db
                break;

        }
    }
}
