package com.company;

import com.company.project.*;
import com.company.project.RBTHelper;
public class Main {
    public static void main(String[] args) throws java.lang.Exception {
        Project group_1 = new Project(2000);
        /*group_1.RBTInsert(12);
        group_1.printRBT();
        group_1.RBTInsert(5);
        group_1.printRBT();
        group_1.RBTInsert(15);
        group_1.printRBT();
        group_1.RBTInsert(3);
        group_1.printRBT();
        group_1.RBTInsert(10);
        group_1.printRBT();
        group_1.RBTInsert(13);
        group_1.printRBT();
        group_1.RBTInsert(17);
        group_1.printRBT();
        group_1.RBTInsert(4);
        group_1.printRBT();
        group_1.RBTInsert(7);
        group_1.printRBT();
        group_1.RBTInsert(11);
        group_1.printRBT();
        group_1.RBTInsert(14);
        group_1.printRBT();
        group_1.RBTInsert(6);
        group_1.printRBT();
        group_1.RBTInsert(8);
        group_1.printRBT();
        group_1.deleteNode(15);
        group_1.printRBT();*/


        group_1.RBTInsert(20);
        group_1.printRBT();
        group_1.RBTInsert(10);
        group_1.printRBT();
        group_1.RBTInsert(30);
        group_1.printRBT();
        group_1.RBTInsert(25);
        group_1.printRBT();
        group_1.RBTInsert(35);
        group_1.printRBT();
        group_1.deleteNode(10);
        group_1.printRBT();

    }
}

//1 2 12 2 5 19 3 8 9 4 15 19 5 7 12 6 9 13 7 11 15 8 3 11 9 31 35 10 22 22 11 12 15 12 10 20 13 29 31 14 18 18 15 22 25 16 6 10 17 1 25 18 23 28 19 25 42
//8 13 15 7 13 15 1 8 9 5 9 11 15 13 25 11 8 11 delete , delete, 4 9 19

