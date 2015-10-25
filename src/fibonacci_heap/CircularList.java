package fibonacci_heap;

import java.util.ArrayList;

/**
 * Created by Jakub on 2015-10-25.
 */
abstract class CircularList {
    /**
     * Method joining two nodes lists
     * @param nodeFromFirstList node from baseList
     * @param nodeFromSecondList node from list to be add
     * @return First if it is not null, in other case returns second if it is not null, otherwise null.
     */
    protected Node joinTwoNodeLists(Node nodeFromFirstList, Node nodeFromSecondList) {
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

    protected Node addNodeToNodeList(Node nodeFromList, Node newNode) {
        this.createNodeListFromNode(newNode);
        return this.joinTwoNodeLists(nodeFromList, newNode);
    }

    /**
     *
     * @param nodeToRemove Node which we want to be removed from list in which it is
     * @return If lists has other nodes one of them will be returned, otherwise null.
     */
    protected Node removeNodeFromNodeList(Node nodeToRemove) {
        Node removedNodeRightNeighbour = nodeToRemove.getRight(),
                removedNodeLeftNeighbour = nodeToRemove.getLeft();

        if(removedNodeRightNeighbour == removedNodeLeftNeighbour) {
            return null;
        }

        removedNodeLeftNeighbour.setRight(removedNodeRightNeighbour);
        removedNodeRightNeighbour.setLeft(removedNodeLeftNeighbour);

        return removedNodeLeftNeighbour;
    }

    protected Node createNodeListFromNode(Node node) {
        if(node == null) {
            return null;
        }

        node.setLeft(node);
        node.setRight(node);

        return node;
    }

    protected ArrayList<Node> getAllNodesFromNodeList(Node nodeFromList) {
        ArrayList<Node> nodes = new ArrayList<>();
        Node startingNode = nodeFromList,
                currentNode = nodeFromList;

        if(currentNode != null) {
            nodes.add(currentNode);
            currentNode = currentNode.getRight();
        }


        while(startingNode != currentNode) {
            nodes.add(currentNode);
            currentNode = currentNode.getRight();
        }

        return nodes;
    }
}
