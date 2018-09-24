package bean;

public class Result {
    private boolean isIllegal;
    private String mResult;
    private Node mTree;
    private String mExpression;

    public Result(boolean isIllegal, String result, Node tree) {
        this.isIllegal = isIllegal;
        this.mResult = result;
        this.mTree = tree;
    }

    public boolean isIllegal() {
        return isIllegal;
    }

    public String getResult() {
        return mResult;
    }

    public Node getTree() {
        return mTree;
    }

    public void setExpression(String expression) {
        mExpression = expression;
    }

    public String getExpression() {
        return mExpression;
    }
}
