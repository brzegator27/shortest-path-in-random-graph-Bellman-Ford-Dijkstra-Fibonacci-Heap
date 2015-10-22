import java.util.LinkedList;

/**
 * Created by Jakub on 2015-10-22.
 */
public class Graph {
    private LinkedList<Vertex> vertexes;

    public Graph(Vertex vertex) {
        this.addVertex(vertex);
    }

    public void addVertex(Vertex vertex) {
        this.vertexes.add(vertex);
    }
}
