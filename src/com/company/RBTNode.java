package com.company;

public class RBTNode {
    private final int buildingNum;
    private boolean redNode;
    private RBTNode leftNode;
    private RBTNode rightNode;
    private RBTNode parentNode;
    private HeapNode heapNode;

    RBTNode(int buildingNum){
        this.buildingNum = buildingNum;
        this.redNode = true;
    }

    public int getBuildingNum() {
        return buildingNum;
    }

    public boolean getRedNode() {
        return this.redNode;
    }

    public void setRedNode(boolean redNode) {
        this.redNode = redNode;
    }

    public HeapNode getHeapNode(){
        return this.heapNode;
    }

    public RBTNode getLeftNode(){
        return this.leftNode;
    }

    public RBTNode getRightNode() {
        return rightNode;
    }

    public RBTNode getParentNode() {
        return parentNode;
    }

}
