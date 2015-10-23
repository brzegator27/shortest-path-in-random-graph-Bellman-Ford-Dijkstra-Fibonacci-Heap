package Graph;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Jakub on 2015-10-22.
 */
public class Vertex {
    private Integer label;
    private Integer distance;
    private Vertex parentVertex;
    private ArrayList<Edge> edges;

    public Vertex(Integer label) {
        this.inicializeVertex();
        this.label = label;
    }

    public Vertex(Integer label, Collection<Edge> edgesFromThisVertex) {
        this.inicializeVertex();
        this.label = label;
        this.edges.addAll(edgesFromThisVertex);
    }

    public void inicializeVertex() {
        this.edges = new ArrayList<>();
        this.parentVertex = null;
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

    public Integer getDistance() {
        return this.distance;
    }

    public void setParentVertex(Vertex parentVertex) {
        this.parentVertex = parentVertex;
    }

    public Integer getLabel() {
        return this.label;
    }

    public ArrayList<Edge> getEdges() {
        return this.edges;
    }

    public Iterable<Edge> getEdgesIterableList() {
        return this.edges;
    }
}
