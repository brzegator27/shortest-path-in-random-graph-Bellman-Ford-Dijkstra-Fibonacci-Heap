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
        this.vertices.add(vertex);
    }

    //This method is probably needless
    public void addEdge(Edge edge){
        Vertex startVertex = edge.getStartVertex();
        startVertex.addEdge(edge);
    }

//    public void addEdgeToVertex(Vertex startVertex, Vertex endVertex, Integer connectingEdgeWeight) {
//
//    }
//
//    public void addEdgeToVertex(Integer startVertexLabel, Integer endVertexLabel, Integer connectingEdgeWeight) {
//
//    }

    public Vertex getVertexByLabel(Integer label){
        for(Vertex singleVertex : this.vertices) {
            if(singleVertex.getLabel() == label) {
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

    public Vertex extractMinVertex() {
        Vertex minVertex = this.vertices.get(0);

        for(Vertex singleVertex : this.vertices) {
            if(singleVertex.compareTo(minVertex) == -1) {
                minVertex = singleVertex;
            }
        }

        return minVertex;
    }

    public Iterable<Vertex> getIterableList() {
        return this.vertices;
    }

    public Integer getVertexCount() {
        return this.vertices.size();
    }
}
