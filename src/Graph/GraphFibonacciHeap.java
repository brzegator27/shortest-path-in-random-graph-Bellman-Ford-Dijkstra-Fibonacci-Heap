package Graph;

import fibonacci_heap.FibonacciHeap;
import fibonacci_heap.Node;

import java.util.ArrayList;

/**
 * Created by Jakub on 2015-10-25.
 */
public class GraphFibonacciHeap extends Graph {
    private ArrayList<Vertex> vertices;
    private FibonacciHeap verticesOperational;

    public GraphFibonacciHeap() {
        this.vertices = new ArrayList<>();
        this.verticesOperational = new FibonacciHeap();
    }

    public void addVertex(Vertex vertex) {
        vertices.add(vertex);
        verticesOperational.insert(vertex);
    }

    public Vertex getVertexByLabel(Integer label) {
        for(Vertex singleVertex : this.getIterableList()) {
            if (singleVertex.getLabel().equals(label)) {
                return singleVertex;
            }
        }
        return null;
    }

    public void initialiseSingleSource(Integer startVertexLabel, Integer startVertexDistance) {
        Vertex startVertex = this.getVertexByLabel(startVertexLabel);

        for(Vertex singleVertex : this.vertices) {
            singleVertex.setDistance(Integer.MAX_VALUE);
            singleVertex.setParentVertex(null);
        }
        startVertex.setDistance(startVertexDistance);
    }

    public Vertex extractMin() {
        Node nodeWithMin = this.verticesOperational.extractMin();

        if(nodeWithMin == null) {
            System.out.println(nodeWithMin);
        }

        Vertex minVertex = nodeWithMin.getKey();

//        System.out.println(this.verticesOperational.extractMin().getKey());
//        System.out.println();
//        System.out.println();
        return minVertex;
    }

    public Iterable<Vertex> getIterableList() {
        return this.vertices;
    }

    public Integer getVerticesCount() {
        return this.vertices.size();
    }

    public Integer getVerticesOperationalCount() {
        return this.verticesOperational.getN();
    }

    public FibonacciHeap getFibHeap() {
        return this.verticesOperational;
    }

    public void afterDistanceChange(Vertex vertexWithDecreasedDist, Integer newDistance) {
        Node correlatedNode = vertexWithDecreasedDist.getCorrelatedNode();
        this.verticesOperational.decreaseKey(correlatedNode, newDistance);
    }
}
