package Graph;

import fibonacci_heap.FibonacciHeap;
import java.util.ArrayList;

/**
 * Created by Jakub on 2015-10-25.
 */
public class GraphFibonacciHeap  {
    private ArrayList<Vertex> vertices;
    private FibonacciHeap verticesOperational;

    public GraphFibonacciHeap() {
        this.vertices = new ArrayList<>();
        this.verticesOperational = new FibonacciHeap();
    }

    public void generateRandomGraph(int verticesCount, int edgesCount) {

    }

    public void addVertex(Vertex vertex) {
        vertices.add(vertex);
        verticesOperational.insert(vertex);
    }

//    public void addEdge(Edge edge) {
//
//    }

//    public Vertex getVertexByLabel(Integer label) {
//        for(Vertex singleVertex : this.getIterableList()) {
//            if (singleVertex.getLabel().equals(label)) {
//                return singleVertex;
//            }
//        }
//        return null;
//    }

    public void initialiseSingleSource(Integer startVertexLabel, Integer startVertexDistance) {

    }

//    public Iterable<Vertex> getIterableList() {
//
//    }
//
//    public Integer getVertexCount() {
//
//    }
}
