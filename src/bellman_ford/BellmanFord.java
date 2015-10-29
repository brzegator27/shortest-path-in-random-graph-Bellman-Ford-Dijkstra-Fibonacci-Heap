package bellman_ford;

import Graph.Graph;
import Graph.Vertex;
import Graph.Edge;

import java.math.BigInteger;

/**
 * Created by Jakub on 2015-10-22.
 */
public class BellmanFord {
    Graph graph;
    Integer startVertexLabel,
            endVertexLabel;
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
        BigInteger optionalDistance = new BigInteger("0"),
                currentDistance = BigInteger.valueOf(toDistance);

        optionalDistance = optionalDistance.add(BigInteger.valueOf(fromDistance));
        optionalDistance = optionalDistance.add(BigInteger.valueOf(connectingEdgeWeight));

//        if()
//        if(currentDistance.compareTo(optionalDistance) == 1) {
//            System.out.println(currentDistance.toString());
//            System.out.println(optionalDistance.toString());
//
//            System.out.println(toDistance);
//            System.out.println(fromDistance);
//            System.out.println(connectingEdgeWeight);
//            System.out.println(fromDistance + connectingEdgeWeight);
//            System.out.println(from.toString() + " " + to.toString());
//            System.exit(0);
//        }
        if(currentDistance.compareTo(optionalDistance) == 1) {
            to.setDistance(fromDistance + connectingEdgeWeight);
            to.setParentVertex(from);
        }
    }

    protected void mainAlgorithm() {
        this.graph.initialiseSingleSource(startVertexLabel, 0);

        for(int i = 0; i < this.graph.getVerticesCount() - 1; i++) {
            for(Vertex singleVertex : this.graph.getIterableList()) {
                for(Edge singleEdge : singleVertex.getEdges()) {
//                    System.out.println(singleEdge);
//                    System.out.println(this.graph.toString());
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
