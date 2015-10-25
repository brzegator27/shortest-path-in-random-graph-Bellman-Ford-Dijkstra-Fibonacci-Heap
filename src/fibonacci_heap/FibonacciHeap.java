package fibonacci_heap;

/**
 * Created by Jakub on 2015-10-22.
 */
public class FibonacciHeap {
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
        Node otherFibHeapMin = otherFibHeap.min,
                otherFibHeapMinLeft = otherFibHeapMin.getLeft(),
                thisFibHeapMin = this.min,
                thisFibHeapMinRight = thisFibHeapMin.getRight();

//      null not considered!!!!
        otherFibHeapMin.setLeft(thisFibHeapMinRight);
        otherFibHeapMinLeft.setRight(thisFibHeapMin);
        thisFibHeapMin.setRight(otherFibHeapMinLeft);
        thisFibHeapMinRight.setLeft(otherFibHeapMin);

        if(this.min == null || (otherFibHeapMin != null && this.min.compareTo(otherFibHeapMin) == 1)) {
            this.min = otherFibHeapMin;
        }

        this.n += otherFibHeap.n;
    }

    /**
     * Method joining two nodes lists
     * @param nodeFromFirstList node from baseList
     * @param nodeFromSecondList node from list to be add
     * @return First if it is not null, in other case returns second if it is not null, otherwise null.
     */
    private Node joinTwoNodeLists(Node nodeFromFirstList, Node nodeFromSecondList) {
        Node nodeFromFirstListRight = nodeFromFirstList.getRight(),
                nodeFromSecondListRight = nodeFromSecondList.getRight();

        if(nodeFromFirstList == null) {
            return nodeFromSecondList;
        }
        if(nodeFromSecondList == null) {
            return nodeFromFirstList;
        }

        nodeFromSecondList.setRight(nodeFromFirstListRight);
        nodeFromSecondListRight.setLeft(nodeFromFirstList);
        nodeFromFirstList.setRight(nodeFromSecondListRight);
        nodeFromFirstListRight.setLeft(nodeFromSecondList);

        return new Node();
    }

    private Node addNodeToNodeList(Node nodeFromList, Node newNode) {
        this.createNodeListFromNode(newNode);
        return this.joinTwoNodeLists(nodeFromList, newNode);
    }

    /**
     *
     * @param nodeToRemove Node which we want to be removed from list in which it is
     * @return If lists has other nodes one of them will be returned, otherwise null.
     */
    private Node removeNodeFromNodeList(Node nodeToRemove) {
        Node removedNodeRightNeighbour = nodeToRemove.getRight(),
                removedNodeLeftNeighbour = nodeToRemove.getLeft();

        if(removedNodeRightNeighbour == removedNodeLeftNeighbour) {
            return null;
        }

        removedNodeLeftNeighbour.setRight(removedNodeRightNeighbour);
        removedNodeRightNeighbour.setLeft(removedNodeLeftNeighbour);

        return removedNodeLeftNeighbour;
    }

    private Node createNodeListFromNode(Node node) {
        if(node == null) {
            return null;
        }

        node.setLeft(node);
        node.setRight(node);

        return node;
    }
}
