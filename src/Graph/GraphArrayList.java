package Graph;

import java.util.ArrayList;

/**
 * Created by Jakub on 2015-10-22.
 */
public class GraphArrayList extends Graph {
    private ArrayList<Vertex> vertices,
            verticesOperational;

    public GraphArrayList() {
        vertices = new ArrayList<>();
        verticesOperational = new ArrayList<>();
    }

    public void addVertex(Vertex vertex) {
        this.vertices.add(vertex);
        this.verticesOperational.add(vertex);
    }

    //This method is probably needless
//    public void addEdge(Edge edge){
//        Vertex startVertex = edge.getStartVertex();
//        startVertex.addEdge(edge);
//    }

    public Vertex getVertexByLabel(Integer label){
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
        Vertex minVertex = this.verticesOperational.get(0);

        for(Vertex singleVertex : this.verticesOperational) {
            if(singleVertex.compareTo(minVertex) == -1) {
                minVertex = singleVertex;
            }
        }
        this.verticesOperational.remove(minVertex);
        return minVertex;
    }

    public Iterable<Vertex> getIterableList() {
        return this.vertices;
    }

    public Integer getVerticesCount() {
        return this.vertices.size();
    }

    public Integer getVerticesOperationalCount() {
        return  this.verticesOperational.size();
    }

    public void afterDistanceChange(Vertex vertexWithDecreasedDist, Integer newDistance) {
        return;
    }
}
