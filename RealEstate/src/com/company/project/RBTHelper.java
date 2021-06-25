package com.company.project;
import com.company.Colour;
import com.company.RBTNode;

public class RBTHelper {
    private RBTNode root;
    public void RBTInsert(int buildingNum){
        RBTNode node = new RBTNode(buildingNum);
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
        System.out.println("Rotating to the right on node "+node.getBuildingNum());

        RBTNode tempLeftNode = node.getLeftNode();
        node.setLeftNode(tempLeftNode.getRightNode());
        if(node.getLeftNode()!=null){
            node.getLeftNode().setParentNode(node);
        }
        tempLeftNode.setParentNode(node.getParentNode());
        if(tempLeftNode.getParentNode()==null){
            root = tempLeftNode;
        }else if(node == node.getParentNode().getLeftNode()){
            node.getParentNode().setLeftNode(tempLeftNode);
        }else{
            node.getParentNode().setRightNode(tempLeftNode);
        }
        tempLeftNode.setRightNode(node);
        node.setParentNode(tempLeftNode);
    }

    public void leftRotation(RBTNode node){
        System.out.println("Rotating to the left on node "+node.getBuildingNum());

        RBTNode tempRightNode = node.getRightNode();
        node.setRightNode(tempRightNode.getLeftNode());
        if(node.getRightNode()!=null){
            node.getRightNode().setParentNode(node);
        }
        tempRightNode.setParentNode(node.getParentNode());
        if(tempRightNode.getParentNode()==null){
            root = tempRightNode;
        }else if(node==node.getParentNode().getLeftNode()){
            node.getParentNode().setLeftNode(tempRightNode);
        }else {
            node.getParentNode().setRightNode(tempRightNode);
        }
        tempRightNode.setLeftNode(node);
        node.setParentNode(tempRightNode);
    }

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
        deleteNodeHelper(root, buildingNum);
    }

    private void deleteNodeHelper(RBTNode node, int buildingNum){
        RBTNode v = null;
        RBTNode u;
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
        if(v == null){
            System.out.println("Couldn't find the building ");
            return;
        }

        if(v.getLeftNode()!=null && v.getRightNode()==null){
            u = v.getLeftNode();
        }else if(v.getLeftNode()==null && v.getRightNode()!=null){
            u = v.getRightNode();
        }else if(v.getLeftNode()!=null && v.getRightNode()!=null){
            RBTNode successor = getSuccessor(v.getRightNode());
            switchNodes(v, successor);
            System.out.println("["+v.getBuildingNum()+" "+v.getColour()+"]"+"["+successor.getBuildingNum()+" "+successor.getColour()+"]");
            if(v.getRightNode()!=null){
                u = v.getRightNode();
            }else {
                u = null;
            }
        }else {
            u = null;
        }
        fixDelete(v, u);
    }

    private void fixDelete(RBTNode v, RBTNode u){
        RBTNode y = v;
        RBTNode x = u;
        if(u!=null){
            if(getColour(v)==Colour.RED || getColour(u)==Colour.RED){
                rbTransplant(v, u);
                u.setNodeColour(Colour.BLACK);
            }else {
                rbTransplant(v, u);
                RBTNode p, s, r;
                while(getColour(u)==Colour.BLACK && u!=root){
                    p = u.getParentNode();
                    if(u==u.getParentNode().getRightNode()){
                        s = u.getParentNode().getLeftNode();
                        if(getColour(s)==Colour.BLACK){
                            if(getColour(s.getLeftNode())==Colour.RED){
                               r = s.getLeftNode();
                               rightRotation(p);
                               r.setNodeColour(Colour.BLACK);
                               u.setNodeColour(Colour.BLACK);
                            }else if(getColour(s.getRightNode())==Colour.RED){
                                r = s.getRightNode();
                                leftRotation(s);
                                rightRotation(p);
                                r.setNodeColour(Colour.BLACK);
                                u.setNodeColour(Colour.BLACK);
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
                            }else if(getColour(s.getLeftNode())==Colour.RED){
                                r = s.getLeftNode();
                                rightRotation(s);
                                leftRotation(p);
                                r.setNodeColour(Colour.BLACK);
                                u.setNodeColour(Colour.BLACK);
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
                u = root;
            }
        }else {

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
        System.out.println("["+x.getBuildingNum()+" "+x.getColour()+"]"+"["+y.getBuildingNum()+" "+y.getColour()+"]");
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
        xCopy = null;
    }
    /*private void deleteNodeHelper(RBTNode node, int buildingNum) {
        RBTNode z = null;
        RBTNode x, y;
        while(node!=null){
            //System.out.println(node.getBuildingNum()+" ");
            if(node.getBuildingNum()==buildingNum){
                z = node;
            }
            if(buildingNum<node.getBuildingNum()){
                node = node.getLeftNode();
            }else {
                node = node.getRightNode();
            }
        }
        if(z==null){
            System.out.println("Couldn't find the building ");
            return;
        }
        y = z;
        Colour yOrignalColour = getColour(y);
        if(z.getLeftNode()==null){
            x = z.getRightNode();
            rbTransplant(z, z.getRightNode());
        }else if(z.getRightNode()==null){
            x = z.getLeftNode();
            rbTransplant(z, z.getLeftNode());
        }else {
            y = getSuccessor(z.getRightNode());
            yOrignalColour = getColour(y);
            x = y.getRightNode();
            if(y.getParentNode() == z){
                x.setParentNode(y);
            }else {
                rbTransplant(y, y.getRightNode());
                y.setRightNode(z.getRightNode());
                y.getRightNode().setParentNode(y);
            }
            rbTransplant(z, y);
            y.setLeftNode(z.getLeftNode());
            y.getLeftNode().setParentNode(y);
            y.setNodeColour(getColour(z));
        }
        if(yOrignalColour == Colour.BLACK){
            fixDelete(x);
        }
    }

    private void fixDelete(RBTNode x) {
        RBTNode sibling = null;
        while(x != root && getColour(x)==Colour.BLACK){
            if(x==x.getParentNode().getLeftNode()){
                sibling = x.getParentNode().getRightNode();
                if(getColour(sibling)==Colour.RED){
                    sibling.setNodeColour(Colour.BLACK);
                    x.getParentNode().setNodeColour(Colour.RED);
                    leftRotation(x.getParentNode());
                    sibling = x.getParentNode().getRightNode();
                }
                if(getColour(sibling.getLeftNode())==Colour.BLACK && getColour(sibling.getRightNode())==Colour.BLACK){
                    sibling.setNodeColour(Colour.RED);
                    x = x.getParentNode();
                }else {
                    if(getColour(sibling.getRightNode()) == Colour.BLACK){
                        sibling.getLeftNode().setNodeColour(Colour.BLACK);
                        sibling.setNodeColour(Colour.RED);
                        rightRotation(sibling);
                        sibling = x.getParentNode().getRightNode();
                    }
                    sibling.setNodeColour(getColour(x.getParentNode()));
                    x.getParentNode().setNodeColour(Colour.BLACK);
                    sibling.getRightNode().setNodeColour(Colour.BLACK);
                    leftRotation(x.getParentNode());
                    x = root;
                }
            }else {
                sibling = x.getParentNode().getLeftNode();
                if(getColour(sibling)==Colour.RED){
                    sibling.setNodeColour(Colour.BLACK);
                    x.getParentNode().setNodeColour(Colour.RED);
                    rightRotation(x.getParentNode());
                    sibling = x.getParentNode().getLeftNode();
                }

                if(getColour(sibling.getRightNode()) == Colour.BLACK && getColour(sibling.getRightNode()) == Colour.BLACK){
                    sibling.setNodeColour(Colour.RED);
                    x = x.getParentNode();
                }else {
                    if(getColour(sibling.getLeftNode()) == Colour.BLACK){
                        sibling.getRightNode().setNodeColour(Colour.BLACK);
                        sibling.setNodeColour(Colour.RED);
                        leftRotation(sibling);
                        sibling = x.getParentNode().getLeftNode();
                    }
                    sibling.setNodeColour(getColour(x.getParentNode()));
                    x.getParentNode().setNodeColour(Colour.BLACK);
                    sibling.getLeftNode().setNodeColour(Colour.BLACK);
                    rightRotation(x.getParentNode());
                    x = root;
                }
            }
        }
        x.setNodeColour(Colour.BLACK);
    }*/



    private void switchData(RBTNode node, RBTNode tempNode) {
        node.setBuildingNum(tempNode.getBuildingNum());
        node.setHeapNode(tempNode.getHeapNode());
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
        /*if(node.getLeftNode()!=null){
            RBTPreOrder(node.getLeftNode());
        }
        System.out.print(node.getBuildingNum()+" ");
        if(node.getRightNode()!=null){
            RBTPreOrder(node.getRightNode());
        }*/
    }

    public void RBTPreOrder(RBTNode node){
        if(node!=null){
            System.out.print("["+node.getBuildingNum() +" "+ getColour(node)+"] ");
            RBTPreOrder(node.getLeftNode());
            RBTPreOrder(node.getRightNode());
        }
        /*System.out.print(node.getBuildingNum()+" ");
        if(node.getLeftNode()!=null){
            RBTPreOrder(node.getLeftNode());
        }
        if(node.getRightNode()!=null){
            RBTPreOrder(node.getRightNode());
        }*/
    }

    public void RBTPostOrder(RBTNode node){
        if(node!=null){
            RBTPostOrder(node.getLeftNode());
            RBTPostOrder(node.getRightNode());
            System.out.print("["+node.getBuildingNum() +" "+ getColour(node)+"] ");
        }
        /*if(node.getLeftNode()!=null){
            RBTPreOrder(node.getLeftNode());
        }
        if(node.getRightNode()!=null){
            RBTPreOrder(node.getRightNode());
        }
        System.out.print(node.getBuildingNum()+" ");*/
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

    public RBTNode getRoot(){
        return this.root;
    }
}