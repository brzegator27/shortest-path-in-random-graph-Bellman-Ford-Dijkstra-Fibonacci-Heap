package Graph;

/**
 * Created by Jakub on 2015-10-22.
 */
public class Edge implements Comparable<Edge> {
    private Vertex startVertex, endVertex;
    private Integer edgeWeight;

    public Edge(Vertex startVertex, Vertex endVertex, Integer edgeWeight) {
        this.startVertex = startVertex;
        this.endVertex = endVertex;
        this.edgeWeight = edgeWeight;
    }

    public Vertex getStartVertex() {
        return this.startVertex;
    }

    public Vertex getEndVertex() {
        return this.endVertex;
    }

    public Integer getWeight() {
        return this.edgeWeight;
    }

    @Override
    public String toString() {
        return "[" + startVertex.toString() + " -(" + this.edgeWeight + ")-> " + endVertex.toString() + "]";
    }

    @Override
    public int compareTo(Edge other) {
        return this.edgeWeight < other.edgeWeight ? -1 : this.edgeWeight == other.edgeWeight ? 0 : 1;
    }
}
