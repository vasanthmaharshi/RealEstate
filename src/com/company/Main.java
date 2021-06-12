package com.company;
import java.util.*;

public class Main {

    public static void main(String[] args) throws java.lang.Exception {
        // write your code here
        Scanner input = new Scanner(System.in);
        HeapNode[] h = new HeapNode[20];
        heapHelper hh = new heapHelper();
        int size = 0;
        for(int j=0;j<6;j++){
            int BN = input.nextInt();
            int ET = input.nextInt();
            int TT = input.nextInt();
            HeapNode x = new HeapNode(BN, ET, TT);
            size = hh.heapInsert(h,x,size);
            hh.printHeap(h, size);
        }
        System.out.println(size);
        hh.printHeap(h, size);
        size = hh.heapDelete(h, size);
        hh.printHeap(h, size);
        size = hh.heapDelete(h, size);
        hh.printHeap(h,size);
        System.out.println(size);
        HeapNode x = new HeapNode(4, 9, 19);
        size = hh.heapInsert(h, x,size);
        hh.printHeap(h, size);
        System.out.println(size);

    }
}

//1 2 12 2 5 19 3 8 9 4 15 19 5 7 12 6 9 13 7 11 15 8 3 11 9 31 35 10 22 22 11 12 15 12 10 20 13 29 31 14 18 18 15 22 25 16 6 10 17 1 25 18 23 28 19 25 42
//8 13 15 7 13 15 1 8 9 5 9 11 15 13 25 11 8 11 delete , delete, 4 9 19
