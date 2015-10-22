package Graph;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Created by Jakub on 2015-10-22.
 */
public class Vertex {
    private Integer label;
    private Integer distance;
    private Vertex pi;
    private LinkedList<Edge> edges;

    public Vertex(Integer label, Collection<Edge> edgesFromThisVertex) {
        this.label = label;
        this.edges = new LinkedList<>(edgesFromThisVertex);
        this.pi = null;
        this.distance = Integer.MAX_VALUE;
    }

    public void addEdge(Edge newEdge) {
        this.edges.add(newEdge);
    }

    public void setLabel(Integer label) {
        this.label = label;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public void setPi(Vertex pi) {
        this.pi = pi;
    }

    public Integer getLabel() {
        return this.label;
    }

    public LinkedList<Edge> getEdges() {
        return this.edges;
    }
}
