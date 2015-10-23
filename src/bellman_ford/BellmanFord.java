package bellman_ford;

import Graph.Graph;
import Graph.Vertex;
import Graph.Edge;

/**
 * Created by Jakub on 2015-10-22.
 */
public class BellmanFord {
    Graph graph;
    Integer startVertexLabel, endVertexLabel;
    boolean minusCycle = false;

    public BellmanFord(Graph graph, Integer startVertexLabel, Integer endVertexLabel) {
        this.startVertexLabel = startVertexLabel;
        this.endVertexLabel = endVertexLabel;
        this.graph = graph;

        this.mainAlgorithm();
    }

    protected void relax(Vertex from, Vertex to, Integer connectingEdgeWeight) {
        Integer fromDistance = from.getDistance();
        Integer toDistance = to.getDistance();

        if(fromDistance > toDistance + connectingEdgeWeight) {
            to.setDistance(fromDistance + connectingEdgeWeight);
            to.setParentVertex(from);
        }
    }

    protected void mainAlgorithm() {
        this.graph.inicializeSingleSource(startVertexLabel, 0);

        for(int i = 0; i < this.graph.getVertexCount() - 1; i++) {
            for(Vertex singleVertex : this.graph.getIterableList()) {
                for(Edge singleEdge : singleVertex.getEdges()) {
                    this.relax(singleEdge.getStartVertex(), singleEdge.getEndVertex(), singleEdge.getWeight());
                }
            }
        }

        for(Vertex singleVertex : this.graph.getIterableList()) {
            for(Edge singleEdge : singleVertex.getEdges()) {
                if(singleEdge.getStartVertex().getDistance()
                     >  singleEdge.getEndVertex().getDistance() + singleEdge.getWeight()) {
                    this.minusCycle = true;
                    return;
                }
            }
        }
    }
}
