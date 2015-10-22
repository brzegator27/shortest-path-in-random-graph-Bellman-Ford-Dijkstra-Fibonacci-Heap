package Graph;

/**
 * Created by Jakub on 2015-10-22.
 */
public class Edge implements Comparable<Edge> {
    private Vertex start, end;
    private Integer edgeWeight;

    public Edge(Vertex start, Vertex end, Integer edgeWeight) {
        this.start = start;
        this.end = end;
        this.edgeWeight = edgeWeight;
    }

    @Override
    public String toString() {
        return "[" + start.toString() + " -> " + end.toString() + "]";
    }

    @Override
    public int compareTo(Edge other) {
        return this.edgeWeight < other.edgeWeight ? -1 : this.edgeWeight == other.edgeWeight ? 0 : 1;
    }
}
