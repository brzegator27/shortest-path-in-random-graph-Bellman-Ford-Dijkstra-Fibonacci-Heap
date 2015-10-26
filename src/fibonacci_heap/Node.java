package fibonacci_heap;

import Graph.Vertex;

/**
 * Created by Jakub on 2015-10-24.
 */
public class Node implements Comparable<Node> {
    private int degree;
    private Node parent,
            child,
            left,
            right;
    private Vertex key;
    private boolean mark;

    public Node(Vertex vertex) {
        this.key = vertex;
        this.degree = 0;
        this.parent = null;
        this.child = null;
        this.left = null;
        this.right = null;
//        Is this ok -> mark = false (???)
        this.mark = false;
    }

    public int getDegree() {
        return degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node getChild() {
        return child;
    }

    public void setChild(Node child) {
        this.child = child;
    }

    public Vertex getKey() {
        return key;
    }

    public void setKey(Vertex key) {
        this.key = key;
    }

    public boolean getMark() {
        return mark;
    }

    public void setMark(boolean mark) {
        this.mark = mark;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public int compareTo(Node other) {
        return this.getKey().compareTo(other.getKey());
    }

//    Bad architecture if I have to write something like this!
    public void setKeyValue(int keyValue) {
        this.key.setDistance(keyValue);
    }

    public int getKeyValue() {
        return this.key.getDistance();
    }
}
