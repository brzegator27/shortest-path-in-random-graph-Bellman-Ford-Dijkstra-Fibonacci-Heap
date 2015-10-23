package Graph;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Jakub on 2015-10-22.
 */
public class GraphArrayList extends Graph {
    private ArrayList<Vertex> vertices;

    public GraphArrayList() {
        vertices = new ArrayList<>();
    }

    public void generateRandomGraph(int verticesCount, int edgesCount) {

    }

    public void addVertex(Vertex vertex) {

    }

    public void addEdge(Edge edge){

    }

    public Vertex extractMinVertex() {
        return new Vertex(12);
    }

    public void inicializeSingleSource() {

    }

    public Iterable<Vertex> getIterableList() {
        return this.vertices;
    }

    public Integer getVertexCount() {
        return this.vertices.size();
    }
}
