package com.company;

public class RBTNode {
    private int buildingNum;
    private Colour nodeColour = Colour.RED;
    private RBTNode leftNode;
    private RBTNode rightNode;
    private RBTNode parentNode;
    private HeapNode heapNode;
    private boolean pos = false;

    public RBTNode(int buildingNum){
        this.buildingNum = buildingNum;
        this.nodeColour = Colour.RED;
        this.heapNode = heapNode;
    }

    @Override
    public String toString() {
        return ""+this.buildingNum;
    }

    public int getBuildingNum() {
        return buildingNum;
    }

    public void setBuildingNum(int buildingNum) {
        this.buildingNum = buildingNum;
    }

    public RBTNode getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(RBTNode leftNode) {
        this.leftNode = leftNode;
    }

    public RBTNode getRightNode() {
        return rightNode;
    }

    public void setRightNode(RBTNode rightNode) {
        this.rightNode = rightNode;
    }

    public RBTNode getParentNode() {
        return parentNode;
    }

    public void setParentNode(RBTNode parentNode) {
        this.parentNode = parentNode;
    }

    public HeapNode getHeapNode() {
        return heapNode;
    }

    public void setHeapNode(HeapNode heapNode) {
        this.heapNode = heapNode;
    }

    public Colour getColour() {
        return nodeColour;
    }

    public void setNodeColour(Colour nodeColour) {
        this.nodeColour = nodeColour;
    }

    public boolean getPos() {
        return pos;
    }

    public void setPos(boolean pos) {
        this.pos = pos;
    }

}
