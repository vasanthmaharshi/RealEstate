package com.company.project;

import com.company.HeapNode;
import com.company.RBTNode;

public class Project {

    private HeapNode[] heap;
    private int currSize;
    private RBTHelper helper;
    public Project(int size){
        heap = new HeapNode[size];
        helper = new RBTHelper();
        currSize = 0;
    }

    public HeapNode[] getHeap() {
        return heap;
    }

    public void setHeap(HeapNode[] heap) {
        this.heap = heap;
    }

    public int getCurrSize() {
        return currSize;
    }

    public void setCurrSize(int currSize) {
        this.currSize = currSize;
    }

    public RBTHelper getHelper() {
        return helper;
    }

    public void RBTInsert(int buildingNum){
        helper.RBTInsert(buildingNum);
    }

    public void deleteNode(int buildingNum){
        helper.deleteNode(buildingNum);
    }

    public void printRBT(){
        helper.printRBT();
    }

}
