package dijkstra;

import Graph.Graph;
import Graph.Vertex;
import Graph.Edge;

import java.math.BigInteger;

/**
 * Created by Jakub on 2015-10-22.
 */
public class Dijkstra {
    Graph graph;
    Integer startVertexLabel,
            endVertexLabel;

    public Dijkstra(Graph graph, Integer startVertexLabel, Integer endVertexLabel) {
        this.startVertexLabel = startVertexLabel;
        this.endVertexLabel = endVertexLabel;
        this.graph = graph;

        this.mainAlgorithm();
    }

    protected void relax(Vertex from, Vertex to, Integer connectingEdgeWeight) {
        Integer fromDistance = from.getDistance();
        Integer toDistance = to.getDistance();
        BigInteger optionalDistance = new BigInteger("0"),
                currentDistance = BigInteger.valueOf(toDistance);

        optionalDistance = optionalDistance.add(BigInteger.valueOf(fromDistance));
        optionalDistance = optionalDistance.add(BigInteger.valueOf(connectingEdgeWeight));

        if(currentDistance.compareTo(optionalDistance) == 1) {
            to.setDistance(fromDistance + connectingEdgeWeight);
            to.setParentVertex(from);
        }
    }

    protected void mainAlgorithm() {
        Vertex currentVertex;
        this.graph.initialiseSingleSource(startVertexLabel, 0);

        while(this.graph.getOperationalVertexCount() > 0) {
            currentVertex = this.graph.extractMin();
            for(Edge singleEdge : currentVertex.getEdges()) {
                this.relax(singleEdge.getStartVertex(), singleEdge.getEndVertex(), singleEdge.getWeight());
            }
        }
    }
}
