package com.company.project;


import com.company.HeapNode;

public class heapHelper {
    public int heapInsert(HeapNode[] Heap, HeapNode newNode, int size){
        Heap[size] = newNode;
        //System.out.println(size);
        heapifyUp(Heap, size);
        size++;
        return size;
    }

    public int heapDelete(HeapNode[] Heap, int size){
        size--;
        Heap[0] = Heap[size];
        heapifyDown(Heap, 0,size-1);
        return size;
    }

    public void heapifyUp(HeapNode[] Heap, int size){
        int curr = size;
        int parent = (curr-1)/2;
        //System.out.println(Heap[curr].getExecutedTime()+" "+Heap[parent].getExecutedTime());
        if(size==0 || Heap[curr].getExecutedTime()>Heap[parent].getExecutedTime()){
            return;
        }
        if(Heap[curr].getExecutedTime()<Heap[parent].getExecutedTime()){
            swapNodes(Heap, curr, parent);
            heapifyUp(Heap, parent);
        }else if(Heap[curr].getBuildingNum()<Heap[parent].getBuildingNum()){
            swapNodes(Heap, curr, parent);
            heapifyUp(Heap, parent);
        }
    }

    public void heapifyDown(HeapNode[] Heap, int curr, int size){
        if(curr>size){
            return;
        }
        int minNode;
        int leftChild = 2*curr+1;
        int rightChild = 2*curr+2;
        if(leftChild>size){
            return;
        }
        if(rightChild>size){
            minNode = leftChild;
        }else if(Heap[leftChild].getExecutedTime()<Heap[rightChild].getExecutedTime()){
            minNode = leftChild;
        }else if(Heap[leftChild].getExecutedTime()>Heap[rightChild].getExecutedTime()){
            minNode = rightChild;
        }else {
            if(Heap[leftChild].getBuildingNum()<Heap[rightChild].getBuildingNum()){
                minNode = leftChild;
            }else {
                minNode = rightChild;
            }
        }
        //System.out.println(curr);
        if(Heap[curr].getExecutedTime()>Heap[minNode].getExecutedTime()){
            swapNodes(Heap, curr, minNode);
        }else if(Heap[curr].getExecutedTime()==Heap[minNode].getExecutedTime()){
            if(Heap[curr].getBuildingNum()>Heap[minNode].getBuildingNum()){
                swapNodes(Heap, curr, minNode);
            }
        }
        heapifyDown(Heap, minNode, size);
    }

    public void swapNodes(HeapNode[] Heap, int a, int b){
        HeapNode temp = Heap[a];
        Heap[a] = Heap[b];
        Heap[b] = temp;
    }
    public void printHeap(HeapNode[] Heap, int size){
        for(int j=0;j<size;j++){
            System.out.print(Heap[j].getBuildingNum()+" ");
        }
        System.out.println();
    }
}
