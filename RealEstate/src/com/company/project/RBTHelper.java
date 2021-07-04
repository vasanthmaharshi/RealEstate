package com.company.project;
import com.company.Colour;
import com.company.HeapNode;
import com.company.RBTNode;

import javax.sound.midi.SysexMessage;
import java.awt.*;

public class RBTHelper {
    private RBTNode root;
    public void RBTInsert(RBTNode node){
        //RBTNode node = new RBTNode(buildingNum);
        root = insertIntoTree(root, node);
        fixViolations(node);
    }

    private RBTNode insertIntoTree(RBTNode root, RBTNode node){
        if(root==null){
            return node;
        }
        if(node.getBuildingNum()<root.getBuildingNum()){
            root.setLeftNode(insertIntoTree(root.getLeftNode(), node));
            root.getLeftNode().setParentNode(root);
        }else if(node.getBuildingNum()>root.getBuildingNum()){
            root.setRightNode(insertIntoTree(root.getRightNode(), node));
            root.getRightNode().setParentNode(root);
        }

        return root;
    }

    public void fixViolations(RBTNode node){
        RBTNode parentNode = null;
        RBTNode grandParentNode = null;
        while(node!= root && getColour(node) != Colour.BLACK && getColour(node.getParentNode())==Colour.RED){
            parentNode = node.getParentNode();
            grandParentNode = node.getParentNode().getParentNode();

            if(parentNode==grandParentNode.getLeftNode()){
                RBTNode uncleNode = grandParentNode.getRightNode();
                if(uncleNode!=null && getColour(uncleNode)==Colour.RED){
                    grandParentNode.setNodeColour(Colour.RED);
                    parentNode.setNodeColour(Colour.BLACK);
                    uncleNode.setNodeColour(Colour.BLACK);
                    node = grandParentNode;
                }else {
                    if(node == parentNode.getRightNode() ){
                        leftRotation(parentNode);
                        node = parentNode;
                        parentNode = node.getParentNode();

                    }
                    rightRotation(grandParentNode);
                    Colour tempColour = getColour(parentNode);
                    parentNode.setNodeColour(getColour(grandParentNode));
                    grandParentNode.setNodeColour(tempColour);
                    node = parentNode;
                }
            }else {
                RBTNode uncleNode = grandParentNode.getLeftNode();
                if(uncleNode!=null && getColour(uncleNode) == Colour.RED){
                    grandParentNode.setNodeColour(Colour.RED);
                    parentNode.setNodeColour(Colour.BLACK);
                    uncleNode.setNodeColour(Colour.BLACK);
                    node = grandParentNode;
                }else {
                    if(node == parentNode.getLeftNode()){
                        rightRotation(parentNode);
                        node = parentNode;
                        parentNode = node.getParentNode();
                    }
                    leftRotation(grandParentNode);
                    Colour tempColour = getColour(parentNode);
                    parentNode.setNodeColour(getColour(grandParentNode));
                    grandParentNode.setNodeColour(tempColour);
                    node = parentNode;
                }
            }
        }
        if(getColour(root)==Colour.RED){
            root.setNodeColour(Colour.BLACK);
        }
    }

    private void rightRotation(RBTNode node){
        //System.out.println("Rotating to the right on node "+node.getBuildingNum());
        //printNode(root, 17);
        RBTNode tempLeftNode = node.getLeftNode();
        node.setLeftNode(tempLeftNode.getRightNode());
        if (tempLeftNode.getRightNode() != null) {
            tempLeftNode.getRightNode().setParentNode(node);
        }
        tempLeftNode.setParentNode(node.getParentNode());
        if (node.getParentNode() == null) {
            root = tempLeftNode;
        } else if (node == node.getParentNode().getLeftNode()) {
            node.getParentNode().setLeftNode(tempLeftNode);
        } else {
            node.getParentNode().setRightNode(tempLeftNode);
        }
        tempLeftNode.setRightNode(node);
        node.setParentNode(tempLeftNode);
        //printNode(root, 13);
    }

    public void leftRotation(RBTNode node){
        //System.out.println("Rotating to the left on node "+node.getBuildingNum());
        RBTNode tempRightNode = node.getRightNode();
        node.setRightNode(tempRightNode.getLeftNode());
        if(tempRightNode.getLeftNode()!=null){
            tempRightNode.getLeftNode().setParentNode(node);
        }
        tempRightNode.setParentNode(node.getParentNode());
        if(node.getParentNode()==null){
            root = tempRightNode;
        }else if(node==node.getParentNode().getLeftNode()){
            node.getParentNode().setLeftNode(tempRightNode);
        }else {
            node.getParentNode().setRightNode(tempRightNode);
        }
        tempRightNode.setLeftNode(node);
        node.setParentNode(tempRightNode);
    }
    /*public void leftRotation(RBTNode x){
        System.out.println("Rotating to the left on node "+x.getBuildingNum());
        printNode(root, 13);
        printNode(root, 14);
        RBTNode y = x.getRightNode();
        x.setRightNode(y.getLeftNode());
        if(y.getLeftNode()!=null){
            y.getLeftNode().setParentNode(x);
        }
        y.setParentNode(x.getParentNode());
        if(x.getParentNode()==null){
            root = y;
        }else if(x==x.getParentNode().getLeftNode()){
            x.getParentNode().setLeftNode(y);
        }else {
            x.getParentNode().setRightNode(y);
        }
        y.setLeftNode(x);
        x.setParentNode(y);
        printNode(root, 13);
        printNode(root, 14);
        printNode(root, 17);
    }*/

    /*public RBTNode deleteNode(RBTNode root, RBTNode node){
        if(root==null){
            return root;
        }
        if(node.getBuildingNum()<root.getBuildingNum()){
            root.setLeftNode(deleteNode(root.getLeftNode(), node));
        }else if(node.getBuildingNum()>root.getBuildingNum()){
            root.setLeftNode(deleteNode(root.getLeftNode(), node));
        }else {
            RBTNode parentNode = node.getParentNode();
            if(root.getLeftNode()==null && root.getRightNode()==null){
                System.out.println("Removing leaf node.....");
                return null;
            }else if(root.getLeftNode()==null){
                System.out.println("Removing the right child.....");
                RBTNode originalNode = root;
                RBTNode tempNode = root.getRightNode();
                tempNode.setParentNode(parentNode);
                root = null;
                return tempNode;
            }else if(root.getRightNode()==null){
                System.out.println("Removing the right child.....");
                RBTNode tempNode = root.getLeftNode();
                tempNode.setParentNode(parentNode);
                root = null;
                return tempNode;
            }
            System.out.println("Removing node with two children.....");
            RBTNode tempNode = getSuccessor(root.getRightNode());

            switchData(tempNode, node);
            root.setRightNode(deleteNode(root.getRightNode(), tempNode));
        }
        return root;
    }*/

    public void deleteNode(int buildingNum){
        //System.out.println("Deleting: "+buildingNum);
        deleteNodeHelper(root, buildingNum);
    }

    private void deleteNodeHelper(RBTNode node, int buildingNum){
        RBTNode v = null;
        RBTNode u = null;
        while(node!=null){
            if(node.getBuildingNum()==buildingNum){
                v = node;
                break;
            }else if(buildingNum < node.getBuildingNum()){
                node = node.getLeftNode();
            }else {
                node = node.getRightNode();
            }
        }
        RBTNode successor = null;
        if(v == null){
            System.out.println("Couldn't find the building ");
            return;
        }
        Colour vOriginalColour = getColour(v);
        if(v.getLeftNode()!=null && v.getRightNode()==null){
            u = v.getLeftNode();
        }else if(v.getLeftNode()==null && v.getRightNode()!=null){
            u = v.getRightNode();
        }else if(v.getLeftNode()!=null && v.getRightNode()!=null){
            successor = getSuccessor(v.getRightNode());
            //printNode(root, v.getBuildingNum());
            swapData(v, successor);
            v = successor;
            //System.out.println(v.getBuildingNum());
            vOriginalColour = getColour(v);
            if(v.getRightNode()!=null){
                u = v.getRightNode();
            }else {
                u = null;
            }
        }else {
            u = null;
        }
        fixDelete(v,u);

    }

    private void fixDelete(RBTNode v, RBTNode u){
        RBTNode y = u;
        RBTNode x = v;
        if(u!=null){
            if(getColour(v)==Colour.RED || getColour(u)==Colour.RED){
                rbTransplant(v, u);
                if(u!=null && getColour(u)==Colour.RED){
                    u.setNodeColour(Colour.BLACK);
                }else {
                    v.setNodeColour(Colour.BLACK);
                }
            }else {
                rbTransplant(v, u);
                RBTNode p, s, r;
                //printNode(root, u.getBuildingNum());
                while(getColour(u)==Colour.BLACK && u!=root){
                    p = u.getParentNode();
                    if(u==u.getParentNode().getRightNode()){
                        s = u.getParentNode().getLeftNode();
                        //printNode(root, s.getBuildingNum());
                        if(getColour(s)==Colour.BLACK){
                            if(getColour(s.getLeftNode())==Colour.RED){
                                r = s.getLeftNode();
                                rightRotation(p);
                                r.setNodeColour(Colour.BLACK);
                                u.setNodeColour(Colour.BLACK);
                                u = root;
                            }else if(getColour(s.getRightNode())==Colour.RED){
                                r = s.getRightNode();
                                printNode(root, r.getBuildingNum());
                                leftRotation(s);
                                rightRotation(p);
                                r.setNodeColour(Colour.BLACK);
                                u.setNodeColour(Colour.BLACK);
                                u = root;
                            }else if(getColour(s.getLeftNode())==Colour.BLACK && getColour(s.getRightNode())==Colour.BLACK){
                                reColour(s);
                                if(getColour(p)==Colour.BLACK){
                                    u = p;
                                }else {
                                    reColour(p);
                                    u = root;
                                }
                            }
                        }else if(getColour(s)==Colour.RED){
                            rightRotation(p);
                            reColour(s);
                            reColour(p);
                            s = u.getParentNode().getLeftNode();
                        }
                    }else {
                        s = u.getParentNode().getRightNode();
                        if(getColour(s)==Colour.BLACK){
                            if(getColour(s.getRightNode())==Colour.RED){
                                r = s.getRightNode();
                                leftRotation(p);
                                r.setNodeColour(Colour.BLACK);
                                u.setNodeColour(Colour.BLACK);
                                u = root;
                            }else if(getColour(s.getLeftNode())==Colour.RED){
                                r = s.getLeftNode();
                                rightRotation(s);
                                leftRotation(p);
                                r.setNodeColour(Colour.BLACK);
                                u.setNodeColour(Colour.BLACK);
                                u = root;
                            }else if(getColour(s.getLeftNode())==Colour.BLACK && getColour(s.getRightNode())==Colour.BLACK){
                                reColour(s);
                                if(getColour(p)==Colour.BLACK){
                                    u = p;
                                }else {
                                    reColour(p);
                                    u = root;
                                }
                            }
                        }else if(getColour(s)==Colour.RED){
                            leftRotation(p);
                            reColour(s);
                            reColour(p);
                            s = u.getParentNode().getRightNode();
                        }
                    }
                }
            }
        }else {
            RBTNode ov = v;
            if(getColour(v)==Colour.RED){
                rbTransplant(v, u);
                v.setNodeColour(Colour.BLACK);
            }else {
                RBTNode p, s, r;
                //System.out.println("case 2");
                //printNode(root, v.getBuildingNum());
                while(getColour(v)==Colour.BLACK && v!=root){
                    p = v.getParentNode();
                    if(v==v.getParentNode().getRightNode()){
                        s = v.getParentNode().getLeftNode();
                        //printNode(root, s.getBuildingNum());
                        if(getColour(s)==Colour.BLACK){
                            if(getColour(s.getLeftNode())==Colour.RED){
                                r = s.getLeftNode();
                                rightRotation(p);
                                r.setNodeColour(Colour.BLACK);
                                v.setNodeColour(Colour.BLACK);
                                v = root;
                            }else if(getColour(s.getRightNode())==Colour.RED){
                                r = s.getRightNode();
                                //printNode(root, r.getBuildingNum());
                                leftRotation(s);
                                rightRotation(p);
                                r.setNodeColour(Colour.BLACK);
                                v.setNodeColour(Colour.BLACK);
                                v = root;
                            }else if(getColour(s.getLeftNode())==Colour.BLACK && getColour(s.getRightNode())==Colour.BLACK){
                                reColour(s);
                                if(getColour(p)==Colour.BLACK){
                                    v = p;
                                }else {
                                    reColour(p);
                                    v = root;
                                }
                            }
                        }else if(getColour(s)==Colour.RED){
                            rightRotation(p);
                            reColour(s);
                            reColour(p);
                            s = v.getParentNode().getLeftNode();
                        }
                    }else {
                        s = v.getParentNode().getRightNode();
                        if(getColour(s)==Colour.BLACK){
                            if(getColour(s.getRightNode())==Colour.RED){
                                r = s.getRightNode();
                                leftRotation(p);
                                r.setNodeColour(Colour.BLACK);
                                v.setNodeColour(Colour.BLACK);
                                v = root;
                            }else if(getColour(s.getLeftNode())==Colour.RED){
                                r = s.getLeftNode();
                                rightRotation(s);
                                leftRotation(p);
                                r.setNodeColour(Colour.BLACK);
                                v.setNodeColour(Colour.BLACK);
                                v = root;
                            }else if(getColour(s.getLeftNode())==Colour.BLACK && getColour(s.getRightNode())==Colour.BLACK){
                                reColour(s);
                                if(getColour(p)==Colour.BLACK){
                                    v = p;
                                }else {
                                    reColour(p);
                                    v = root;
                                }
                            }
                        }else if(getColour(s)==Colour.RED){
                            leftRotation(p);
                            reColour(s);
                            reColour(p);
                            s = v.getParentNode().getRightNode();
                        }
                    }
                }
                rbTransplant(ov, null);
            }
        }
    }

    private void rbTransplant(RBTNode x, RBTNode y) {
        if(x.getParentNode()==null){
            root = y;
        }else if(x == x.getParentNode().getLeftNode()){
            x.getParentNode().setLeftNode(y);
        }else {
            x.getParentNode().setRightNode(y);
        }
        if(y!=null){
            y.setParentNode(x.getParentNode());
        }
    }

    private void switchNodes(RBTNode x, RBTNode y){
        System.out.println("Switching node "+x.getBuildingNum()+" "+y.getBuildingNum());
        RBTNode xParent;
        RBTNode xLeft = x.getLeftNode();
        RBTNode xRight = x.getRightNode();
        Colour xColour = getColour(x);
        Boolean xPos = false;
        if(x==root){
            xParent = null;
        }else{
            xParent = x.getParentNode();
            if(x == x.getParentNode().getRightNode()){
                xPos = true;
            }
        }
        RBTNode yParent = y.getParentNode();
        RBTNode yLeft = y.getLeftNode();
        RBTNode yRight = y.getRightNode();
        Colour yColour = getColour(y);
        Boolean yPos = false;
        if(y == y.getParentNode().getRightNode()){
            yPos = true;
        }

        if(x==root){
            root = y;
            y.setParentNode(xParent);
        }else {
            y.setParentNode(xParent);
            if(xPos==false){
                xParent.setLeftNode(y);
            }else {
                xParent.setRightNode(y);
            }
        }
        if(yParent==x){
            x.setParentNode(y);
            y.setRightNode(x);
            y.setLeftNode(xLeft);
            x.setLeftNode(yLeft);
            x.setRightNode(yRight);
        }else {
            x.setParentNode(yParent);
            if(yPos==false){
                yParent.setLeftNode(x);
            }else {
                yParent.setRightNode(x);
            }
            y.setRightNode(xRight);
            y.setLeftNode(xLeft);
            x.setLeftNode(yLeft);
            x.setRightNode(yRight);
        }
        x.setNodeColour(yColour);
        y.setNodeColour(xColour);
    }
    /*private void switchNodes(RBTNode x, RBTNode y){
        System.out.println("4 "+"["+x.getBuildingNum()+" "+x.getColour()+"]"+"["+y.getBuildingNum()+" "+y.getColour()+"]");
        RBTNode xCopy = new RBTNode(x);
        RBTNode x_parentNode = x.getParentNode();
        RBTNode y_parentNode = y.getParentNode();
        x.setLeftNode(y.getLeftNode());
        x.setRightNode((y.getRightNode()));
        y.setLeftNode(xCopy.getLeftNode());
        y.setRightNode(xCopy.getRightNode());
        if(x==root){
            root = y;
        }else {
            if(x==x.getParentNode().getLeftNode()){
                y.setParentNode(x_parentNode);
                x.getParentNode().setLeftNode(y);
            }else {
                y.setParentNode(x_parentNode);
                x.getParentNode().setRightNode(y);
            }
        }

        if(y==y_parentNode.getLeftNode()){
            x.setParentNode(y_parentNode);
            y_parentNode.setLeftNode(x);
        }else {
            x.setParentNode((y_parentNode));
            y_parentNode.setRightNode(x);
        }
        x.setNodeColour(y.getColour());
        y.setNodeColour(xCopy.getColour());
        System.out.println("5 "+"["+x.getBuildingNum()+" "+x.getColour()+"]"+"["+y.getBuildingNum()+" "+y.getColour()+"]");
        System.out.println("6 "+"["+y.getLeftNode().getBuildingNum()+" "+y.getLeftNode().getColour()+"]"+"["+y.getRightNode().getBuildingNum()+" "+y.getRightNode().getColour()+"]");
        //System.out.println("7 "+"["+x.getLeftNode().getBuildingNum()+" "+x.getLeftNode().getColour()+"]"+"["+x.getRightNode().getBuildingNum()+" "+x.getRightNode().getColour()+"]");
        xCopy = null;
    }*/

    /*private void switchNodes(RBTNode x, RBTNode y){
        System.out.println("Switching node "+x.getBuildingNum()+" "+y.getBuildingNum());
        RBTNode xParent;
        RBTNode xLeft = x.getLeftNode();
        RBTNode xRight = x.getRightNode();
        Boolean xPos = false;
        Colour xColour = getColour(x);
        if(x==root){
            xParent = null;
        }else {
            xParent = x.getParentNode();
            if(x==x.getParentNode().getRightNode()){
                xPos = true;
            }
        }
        RBTNode yParent = y.getParentNode();
        RBTNode yLeft = y.getLeftNode();
        RBTNode yRight = y.getRightNode();
        Boolean yPos = false;
        Colour yColour = getColour(y);
        if(y==y.getParentNode().getRightNode()){
            yPos = true;
        }
        if(x==root){
            root = y;
            y.setLeftNode(xLeft);
            if(yParent==x){
                y.setRightNode(x);
            }else {
                y.setRightNode(xRight);
            }

            y.setParentNode(null);
        }else {
            if(xPos==false){
                xParent.setLeftNode(y);
            }else {
                xParent.setRightNode(y);
            }
            if(yParent==x){
                y.setRightNode(x);
            }else {
                y.setRightNode(xRight);
            }
            y.setParentNode(xParent);
            y.setLeftNode(xLeft);
            y.setNodeColour(xColour);
        }
        if(yPos==false){
            yParent.setLeftNode(x);
        }else {
            yParent.setRightNode(x);
        }
        if(yParent==x){
            x.setParentNode(y);
        }else {
            x.setParentNode(yParent);
        }
        x.setLeftNode(yLeft);
        x.setRightNode(yRight);
        x.setNodeColour(yColour);
        //printNode(root, 13);
    }*/

    private void swapData(RBTNode node, RBTNode tempNode) {
        int nodeBuildingNum = node.getBuildingNum();
        HeapNode nodeHeapNode = node.getHeapNode();
        node.setBuildingNum(tempNode.getBuildingNum());
        tempNode.setBuildingNum(nodeBuildingNum);
        node.setHeapNode(tempNode.getHeapNode());
        tempNode.setHeapNode(nodeHeapNode);
        //tempNode.getHeapNode().setRbtNode(tempNode);
        //nodeHeapNode.setRbtNode(node);
    }

    private RBTNode getPredecessor(RBTNode node) {
        if(node.getLeftNode()==null && node.getRightNode()!=null){
            return getPredecessor(node.getRightNode());
        }
        return node;
    }

    private RBTNode getSuccessor(RBTNode node){
        if(node.getLeftNode()!=null){
            return getSuccessor(node.getLeftNode());
        }
        return node;
    }

    public void printRBT(){
        if(this.root!=null){
            RBTPreOrder(this.root);
        }
        System.out.println();
        /*if(this.root!=null){
            RBTInOrder(this.root);
        }
        System.out.println();
        if(this.root!=null){
            RBTPostOrder(this.root);
        }
        System.out.println();*/
    }
    public void RBTInOrder(RBTNode node){
        if (node != null) {
            RBTInOrder(node.getLeftNode());
            System.out.print("["+node.getBuildingNum() +" "+ getColour(node)+"] ");
            RBTInOrder(node.getRightNode());
        }
    }

    public void RBTPreOrder(RBTNode node){
        if(node!=null) {
            System.out.print("[" + node.getBuildingNum() + " " + getColour(node) + "] ");
            RBTPreOrder(node.getLeftNode());
            RBTPreOrder(node.getRightNode());
        }
    }

    public void RBTPostOrder(RBTNode node){
        if(node!=null){
            RBTPostOrder(node.getLeftNode());
            RBTPostOrder(node.getRightNode());
            System.out.print("["+node.getBuildingNum() +" "+ getColour(node)+"] ");
        }
    }

    public Colour getColour(RBTNode node){
        if(node==null){
            return Colour.BLACK;
        }
        return node.getColour();
    }

    public void reColour(RBTNode node){
        if(getColour(node)==Colour.BLACK){
            node.setNodeColour(Colour.RED);
        }else {
            node.setNodeColour(Colour.BLACK);
        }
    }

    public void printBuilding(int buildingNum){
        printNode(root, buildingNum);
        System.out.println();
    }

    public void printNode(RBTNode node, int buildingNum){
        if(node==null){
            return;
        }
        if(buildingNum == node.getBuildingNum()){
            HeapNode x = node.getHeapNode();
            System.out.printf("(%d,%d,%d)\n", x.getBuildingNum(),x.getExecutedTime(),x.getTotalTime());
            //System.out.println("Node: "+node.getBuildingNum());
        }else if(buildingNum < node.getBuildingNum()){
            printNode(node.getLeftNode(), buildingNum);
        }else if(buildingNum > node.getBuildingNum()){
            printNode(node.getRightNode(), buildingNum);
        }
    }

    public void printBuildings(int building1, int building2){
        printNodes(root, building1, building2);
        System.out.println();
    }

    public void printNodes(RBTNode node, int building1, int building2){
        if(node==null){
            return;
        }

        if(building1<node.getBuildingNum()){
            printNodes(node.getLeftNode(), building1, building2);
        }
        if(building1<=node.getBuildingNum() && building2>=node.getBuildingNum()){
            HeapNode x = node.getHeapNode();
            //System.out.print(node.getBuildingNum()+" ");
            System.out.printf("(%d,%d,%d)", x.getBuildingNum(), x.getExecutedTime(), x.getTotalTime());
        }
        if(building2>node.getBuildingNum()){
            printNodes(node.getRightNode(), building1, building2);
        }
    }


    public RBTNode getRoot(){
        return this.root;
    }
}