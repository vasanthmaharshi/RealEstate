package com.company;

import com.company.project.*;
import com.company.project.RBTHelper;

import java.io.*;
import  java.util.*;

public class Main {
    public static void main(String[] args) throws java.lang.Exception {
        try{
            Project group_1 = new Project(2000);
            FileInputStream fis = new FileInputStream("C:\\Users\\Admin\\Desktop\\Git Projects\\RealEstate\\src\\com\\company\\input.txt");
            Scanner sc = new Scanner(fis);
            int globalTime = 0;
            int workDone = 0;
            HeapNode presentConstruction = null;
            int commandTime = 0;
            String input = "";
            String command = "";
            /*if(sc.hasNextLine()){
                input = sc.nextLine();
                commandTime = Integer.parseInt(input.substring(0, input.indexOf(":")));
                command = input.substring(input.indexOf(" ")+1,input.indexOf("("));
                System.out.println(command);

            }*/
            while(sc.hasNextLine() || group_1.getSize()!=0){
                //System.out.println("GT: "+globalTime+" "+"CT "+commandTime);
                while(commandTime==globalTime){
                    //System.out.println(globalTime+" "+command);
                    if(command.equals("Insert")){
                        int buildingNum = Integer.parseInt(input.substring(input.indexOf('(')+1, input.indexOf(',')));
                        int totalTime = Integer.parseInt(input.substring(input.indexOf(',')+1, input.length()-1));
                        //System.out.println("Inserting "+buildingNum);
                        group_1.insert(buildingNum, 0, totalTime);
                        //System.out.println(group_1.getSize());
                        //group_1.printHeap();
                    }else if(command.equals("PrintBuilding")){
                        String[] buildings = input.substring(input.indexOf('(')+1, input.length()-1).split(",");
                        if(buildings.length==1){
                            int buildingNum = Integer.parseInt(buildings[0]);
                            group_1.printBuilding(buildingNum);
                        }else {
                            int building1 = Integer.parseInt(buildings[0]);
                            int building2 = Integer.parseInt(buildings[1]);
                            group_1.printBuildings(building1, building2);
                        }
                    }
                    if(sc.hasNextLine()){
                        input = sc.nextLine();
                        commandTime = Integer.parseInt(input.substring(0, input.indexOf(":")));
                        command = input.substring(input.indexOf(" ")+1,input.indexOf("("));
                        //System.out.println(command);
                    }else {
                        commandTime = 0;
                    }
                }
                if(workDone==0 && presentConstruction==null && group_1.getSize()!=0){
                    //group_1.printHeap();
                    presentConstruction = group_1.heapExtract();
                    //System.out.println("presentConstruction "+presentConstruction.getBuildingNum());
                }else {
                    workDone++;
                    presentConstruction.setExecutedTime(presentConstruction.getExecutedTime()+1);
                    //System.out.println("presentConstruction "+presentConstruction.getBuildingNum());
                    if(presentConstruction.getExecutedTime() == presentConstruction.getTotalTime()-1) {
                        System.out.println(presentConstruction.getBuildingNum()+" "+globalTime);
                        workDone = 0;
                        group_1.deleteNode(presentConstruction.getBuildingNum());
                        presentConstruction = null;
                    }else if(workDone==4){
                        //System.out.println("5 days work");
                        group_1.heapInsert(presentConstruction);
                        presentConstruction = null;
                        workDone = 0;
                    }

                }
                globalTime++;
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}

//1 2 12 2 5 19 3 8 9 4 15 19 5 7 12 6 9 13 7 11 15 8 3 11 9 31 35 10 22 22 11 12 15 12 10 20 13 29 31 14 18 18 15 22 25 16 6 10 17 1 25 18 23 28 19 25 42
//8 13 15 7 13 15 1 8 9 5 9 11 15 13 25 11 8 11 delete , delete, 4 9 19


/*int count = 0;
        while(count<6){
            int bN = input.nextInt();
            int eT = input.nextInt();
            int tT = input.nextInt();
            group_1.insert(bN, eT, tT);
            count++;
            System.out.println(group_1.getSize());
        }
        group_1.printHeap();
        group_1.printRBT();
        group_1.heapExtract();
        System.out.println(group_1.getSize());
        group_1.heapExtract();
        System.out.println(group_1.getSize());
        group_1.printHeap();
        group_1.insert(4,9, 19);
        System.out.println(group_1.getSize());
        group_1.printHeap();*/
