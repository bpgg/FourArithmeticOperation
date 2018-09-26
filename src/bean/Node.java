package bean;

public class Node {
    private String mData;
    private boolean mIsOperate;
    private String mOperate;

    private Node mLeftChild;
    private Node mRightChild;


     public Node(String data) {
        mData = data;
        mIsOperate = false;
        mOperate = "";

    }

     public Node(String operate,boolean isOperate) {
        mIsOperate = isOperate;
        mData = "";
        mOperate = operate;
    }

    public String getData() {
        return mData;
    }

    public void setData(String data) {
        mData = data;
    }

    public boolean isOperate() {
        return mIsOperate;
    }


    public String getOperate() {
        return mIsOperate?mOperate:"";
    }

    public void setLeftChild(Node leftChild) {
        mLeftChild = leftChild;
    }

    public void setRightChild(Node rightChild) {
        mRightChild = rightChild;
    }

    public Node getLeftChild() {
        return mLeftChild;
    }

    public Node getRightChild() {
        return mRightChild;
    }
}
