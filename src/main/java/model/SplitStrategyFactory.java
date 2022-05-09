package model;

public class SplitStrategyFactory {

    private SplitStrategy splitStrategy;

    public SplitStrategy getSplitStrategy(String splitString){
        switch (splitString) {
            case Constants.Equal:
                this.splitStrategy = new EqualSplitStrategy();
                break;
            case Constants.Ratio:
                this.splitStrategy = new RatioSplitStrategy();
                break;
            case Constants.Exact:
                this.splitStrategy = new ExactSplitStrategy();
                break;
            case Constants.Percent:
                this.splitStrategy = new PercentageSplitStrategy();
                break;
        }
        return this.splitStrategy;
    }

}
