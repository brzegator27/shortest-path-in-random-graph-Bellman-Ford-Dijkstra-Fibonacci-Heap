package fibonacci_heap;

/**
 * Created by Jakub on 2015-10-22.
 */
public class FibonacciHeap extends CircularList {
    private Node min;
    private int n;

    public Node getMin() {
        return min;
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
        Node min = this.min;

        if(min != null) {
            for(Node childNodeFromMin : this.getAllNodesFromNodeList(min.getChild())) {
                this.removeNodeFromNodeList(childNodeFromMin);
                this.addNodeToNodeList(this.min, childNodeFromMin);
            }
            min.setChild(null);
            this.removeNodeFromNodeList(min);
            if(min == min.getRight()) {
                this.min = null;
            } else {
                this.min = min.getRight();
                this.consolidate();
            }
            this.n--;
        }
        return min;
    }

    private void consolidate() {
//        This must be changed, because this.n -> log(this.n), or sth like this.
        int nodeDegree,
                nodesArraySize = this.n;
        Node[] nodesOfCertainDegree = new Node[nodesArraySize];
        Node x,
                y,
                tempNodeToSwitch;

        for(Node nodeFromHeap : this.getAllNodesFromNodeList(this.min)) {
            x = nodeFromHeap;
            nodeDegree = x.getDegree();

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
                    if(this.min.compareTo(node) == 1) {
                        this.min = node;
                    }
                }
            }
        }
    }

//    Variables' names x and y are not too intuitive.
    private void link(Node y, Node x) {
        int xDegree = x.getDegree();

        this.removeNodeFromNodeList(y);
        this.addNodeToNodeList(x, y);
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

        this.removeNodeFromNodeList(x);
        yDegree--;
        y.setDegree(yDegree);
        this.addNodeToNodeList(this.min, x);
        x.setParent(null);
        x.setMark(false);
    }

    private void cascadingCut(Node y) {
        Node yParent = y.getParent();

        if(yParent != null) {
            if(y.getMark() == false) {
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

}