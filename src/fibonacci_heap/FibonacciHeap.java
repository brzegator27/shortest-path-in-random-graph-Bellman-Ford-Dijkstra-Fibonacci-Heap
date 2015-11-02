package fibonacci_heap;

import Graph.Vertex;

import java.util.ArrayList;

/**
 * Created by Jakub on 2015-10-22.
 */
public class FibonacciHeap extends CircularList {
    private Node min;
    private int n;

    public Node getMin() {
        return min;
    }

    public void insert(Vertex vertex) {
        Node newNode = new Node(vertex);
        vertex.setCorrelatedNode(newNode);
        this.insert(newNode);
//        System.out.println(this.min + " insert ver");
    }

    public void insert(Node newNode) {
        newNode.setDegree(0);
        newNode.setParent(null);
        newNode.setChild(null);
        newNode.setMark(false);

        if(this.min == null) {
            newNode.setLeft(newNode);
            newNode.setRight(newNode);
            this.min = newNode;
        } else {
            this.addNodeToNodeList(this.min, newNode);
            if(this.min.compareTo(newNode) == 1) {
                this.min = newNode;
            }
        }

        this.n++;
    }

    public void union(FibonacciHeap otherFibHeap) {
        Node otherFibHeapMin = otherFibHeap.min;

        this.joinTwoNodeLists(this.min, otherFibHeapMin);

        if(this.min == null || (otherFibHeapMin != null && this.min.compareTo(otherFibHeapMin) == 1)) {
            this.min = otherFibHeapMin;
        }

        this.n += otherFibHeap.n;
    }

    public Node extractMin() {
//        for(Node singleNode : this.getAllNodesFromNodeList(this.min)) {
//            System.out.print(singleNode + " nodeList ->");
//        }
//        System.out.println(this.min + " extractMin()");
        Node min = this.min;

        if(min != null) {
            for(Node childNodeFromMin : this.getAllNodesFromNodeList(min.getChild())) {
                this.removeNodeFromNodeList(childNodeFromMin);
                this.addNodeToNodeList(this.min, childNodeFromMin);
                childNodeFromMin.setParent(null);
            }
            min.setChild(null);
//            System.out.println("In extractMin :");
//            System.out.println(this.toString());
            this.removeNodeFromNodeList(min);
//            System.out.println("In extractMin :");
//            System.out.println(this.toString());
            if(min == min.getRight()) {
//                System.out.println("this.min = null");
                this.min = null;
            } else {
                this.min = min.getRight();
//                System.out.println(this.min + " this.min = min.getRight();");
//                for(Node singleNode : this.getAllNodesFromNodeList(this.min)) {
//                    System.out.print(singleNode + " nodeList ->");
//                }
//                for(Node singleNode : this.getAllNodesFromNodeList(this.min.getChild())) {
//                    System.out.print(singleNode + " nodeList2 ->");
//                }
//                System.out.println("In extractMin :");
//                System.out.println(min.getRight().toString());
//                System.out.println(min.toString());
//                System.out.println(this.min.getRight().toString());
//                System.out.println(this.toString());

                this.consolidate();
//                for(Node singleNode : this.getAllNodesFromNodeList(this.min)) {
//                    System.out.print(singleNode + " nodeList ->");
//                }
            }
            this.n--;
        }
//        System.out.println(this.min + " this.min end");
        return min;
    }

    private void consolidate() {
//        This must be changed, because this.n -> log(this.n), or sth like this.
        Double nodesArraySizeDouble = Math.floor(Math.log(this.n) / Math.log(2));
        int nodeDegree,
                nodesArraySize = nodesArraySizeDouble.intValue() + 2;
//        System.out.println("asd " + nodesArraySize + " asdas");
        Node[] nodesOfCertainDegree = new Node[nodesArraySize];
        Node x,
                y,
                tempNodeToSwitch;

        for(Node nodeFromHeap : this.getAllNodesFromNodeList(this.min)) {
            x = nodeFromHeap;
            nodeDegree = x.getDegree();
//            System.out.println("nodesArraySize: " + nodesArraySize);
//            System.out.println("nodeDegree: " + nodeDegree);
            while(nodesOfCertainDegree[nodeDegree] != null) {
                y = nodesOfCertainDegree[nodeDegree];
                if(x.compareTo(y) == 1) {
                    tempNodeToSwitch = x;
                    x = y;
                    y = tempNodeToSwitch;
                }
                this.link(y, x);
                nodesOfCertainDegree[nodeDegree] = null;
                nodeDegree++;
            }
            nodesOfCertainDegree[nodeDegree] = x;
        }

        this.min = null;
        for(Node node : nodesOfCertainDegree) {
            if(node != null) {
                if(this.min == null) {
                    this.min = this.createNodeListFromNode(node);
                } else {
                    this.addNodeToNodeList(this.min, node);
                    node.setParent(null);
                    if(this.min.compareTo(node) == 1) {
                        this.min = node;
                    }
                }
            }
        }
//        System.out.println(" nodeList22 ->");
//        for(Node singleNode : this.getAllNodesFromNodeList(this.min)) {
//            System.out.print(singleNode + " nodeList22 ->");
//        }
//        System.out.println("\n");
    }

//    Variables' names x and y are not too intuitive.
    private void link(Node y, Node x) {
        int xDegree = x.getDegree();

        this.removeNodeFromNodeList(y);
        if(x.getChild() == null) {
            this.createNodeListFromNode(y);
            x.setChild(y);
            y.setParent(x);
        } else {
            this.addNodeToNodeList(x.getChild(), y);
            y.setParent(x);
        }
        xDegree++;
        x.setDegree(xDegree);
        y.setMark(false);
    }

    public void decreaseKey(Node nodeToDecreaseKey, int newKeyValue) {
        Node nodeToDecreaseKeyParent = nodeToDecreaseKey.getParent();

        if(newKeyValue > nodeToDecreaseKey.getKeyValue()) {
            System.out.println("New key value is larger than current key value!");
            return;
        }

        nodeToDecreaseKey.setKeyValue(newKeyValue);
        if(nodeToDecreaseKeyParent != null && nodeToDecreaseKey.compareTo(nodeToDecreaseKeyParent) == -1) {
            this.cut(nodeToDecreaseKey, nodeToDecreaseKeyParent);
            this.cascadingCut(nodeToDecreaseKeyParent);
        }
        if(nodeToDecreaseKey.compareTo(this.min) == -1) {
            this.min = nodeToDecreaseKey;
        }
    }

//    Variables' names x and y are not too intuitive.
    private void cut(Node x, Node y) {
        int yDegree = y.getDegree();

//        System.out.println("tutaj");
//        System.out.println(this.toString());
        this.removeNodeFromNodeList(x);

//        Debugging
//        System.out.println("In cut :");
//        System.out.println(x.toString());
//        System.out.println(y.toString());
//        System.out.println(this.min.getRight().toString());

        yDegree--;
        y.setDegree(yDegree);
        this.addNodeToNodeList(this.min, x);
        x.setParent(null);
        x.setMark(false);
    }

    private void cascadingCut(Node y) {
        Node yParent = y.getParent();

        if(yParent != null) {
            if(!y.getMark()) {
                y.setMark(true);
            }
            else {
                this.cut(y, yParent);
                cascadingCut(yParent);
            }
        }
    }

//    Should we consider overflow of keyValue?
    public void delete(Node node) {
//        Minus 2 in case of overloading - I know it is irresponsible...
//        ...but I don't have time now. :)
        this.decreaseKey(node, Integer.MAX_VALUE - 2);
        this.extractMin();
    }

    public int getN() {
        return  this.n;
    }

    @Override
    public String toString() {
        if(this.min == null) {
            return "Fibonacci Heap is empty!";
        }
        return this.fibHeapToString(this.min, 0);
    }

    protected String fibHeapToString(Node nodeFromList, int depth) {
        ArrayList<Node> nodesFromList = this.getAllNodesFromNodeList(nodeFromList),
                childNodes = new ArrayList<>();
        Node parentNode = nodeFromList.getParent();
        String circularListAsString = new String();

        for(int i = 0; i < depth; i++) {
            circularListAsString += "\t";
        }

        circularListAsString += parentNode != null ? parentNode.toString() + ": " : "";

        for(Node singleNode : nodesFromList) {
            circularListAsString += singleNode.toStringWithKeyValue();
            circularListAsString += " -> ";
            if(singleNode.getChild() != null) {
                childNodes.add(singleNode.getChild());
            }
        }

        for(Node singleChildNode : childNodes) {
            circularListAsString += "\n";
            circularListAsString += this.fibHeapToString(singleChildNode, depth + 1);
        }

        return circularListAsString;
    }
}