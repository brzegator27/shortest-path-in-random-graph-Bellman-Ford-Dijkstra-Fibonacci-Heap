package Graph;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Jakub on 2015-10-22.
 */
public abstract class Graph {
    String filenameInput,
            filenameOutput;
//    String prefixInEdgeNotation = "[";
//    String postfixInEdgeNotation = "]";
    String prefixInEdgeNotation = "";
    String postfixInEdgeNotation = "";
    String splitterInEdgeNotation = ",";

    public abstract void addVertex(Vertex vertex);
//    public abstract void addEdge(Edge edge);
//    public abstract void addEdgeToVertex(Vertex startVertex, Vertex endVertex, Integer connectingEdgeWeight);
//    public abstract void addEdgeToVertex(Integer startVertexLabel, Integer endVertexLabel, Integer connectingEdgeWeight);
    public abstract Vertex getVertexByLabel(Integer label);
    public abstract void initialiseSingleSource(Integer startVertexLabel, Integer startVertexDistance);
    public abstract Iterable<Vertex> getIterableList();
    public abstract Integer getVerticesCount();
    public abstract Integer getVerticesOperationalCount();
    public abstract Vertex extractMin();

    public void setFilenames(String filenameInput, String filenameOutput) {
        this.filenameInput = filenameInput;
        this.filenameOutput = filenameOutput;
    }

    public void showBestRoute(Integer startVertexLabel, Integer endVertexLabel) {
        Vertex vertex = this.getVertexByLabel(endVertexLabel),
                parentVertex;
        Integer distance = vertex.getDistance();
        String endVertexLabelAsString = endVertexLabel.toString(),
                routeAsString = new String("dist " + distance + ": " + endVertexLabelAsString);

        parentVertex = vertex.getParentVertex();
        while(parentVertex != null) {
            routeAsString +=  " -> " + parentVertex.getLabel().toString();
            parentVertex = parentVertex.getParentVertex();
        }

        System.out.println(routeAsString);
    }

    /**
     * Method generating random graph. Premise is that the graph is empty.
     * @param verticesCount Number of vertices in created graph.
     * @param additionalEdgesCount Number of edges in generated graph MINUS number of vertices.
     */
    public void generateRandomGraph(int verticesCount, int additionalEdgesCount) {
        Vertex startVertex, endVertex;
        Edge newEdge;
        HashMap<Integer, Vertex> randomVertices = new HashMap<>();
        Random generator = new Random();
        Integer edgeWeight,
                edgeLabel;

        /**
         * The circular consistent graph is created.
         */
        startVertex = this.manageVertexInHashMap(1, randomVertices);
        for(int i = 2; i <= verticesCount; i++) {
            endVertex = this.manageVertexInHashMap(i, randomVertices);

            edgeWeight = generator.nextInt(10) + 1;
            newEdge = new Edge(startVertex, endVertex, edgeWeight);
            startVertex.addEdge(newEdge);

            newEdge = new Edge(endVertex, startVertex, edgeWeight);
            endVertex.addEdge(newEdge);

            startVertex = endVertex;
        }

        endVertex = this.manageVertexInHashMap(1, randomVertices);

        edgeWeight = generator.nextInt(10) + 1;
        newEdge = new Edge(startVertex, endVertex, edgeWeight);
        startVertex.addEdge(newEdge);

        newEdge = new Edge(endVertex, startVertex, edgeWeight);
        endVertex.addEdge(newEdge);


        /**
         * The additional edges are added.
         */
        for(int i = 0; i < additionalEdgesCount; i++) {
            edgeLabel = generator.nextInt(verticesCount) + 1;
            startVertex = this.manageVertexInHashMap(edgeLabel, randomVertices);
            edgeLabel = generator.nextInt(verticesCount) + 1;
            endVertex = this.manageVertexInHashMap(edgeLabel, randomVertices);

            edgeWeight = generator.nextInt(10) + 1;
            newEdge = new Edge(startVertex, endVertex, edgeWeight);
            startVertex.addEdge(newEdge);
        }


        randomVertices.values().forEach(this::addVertex);
    }

    public void readGraph() {
        if(this.filenameInput == null) {
            return;
        }

        Path path = Paths.get(this.filenameInput);
        String edgeAsString;
        String edgeAsStringTrimmed;
        String[] edgeProperties;
        int edgeAsStringLength;
        int prefixLength = this.prefixInEdgeNotation.length();
        int postfixLength = this.postfixInEdgeNotation.length();
        HashMap<Integer, Vertex> verticesFromFile = new HashMap<>();

        try {
            Stream<String> lines = Files.lines(path);
            List<String> linesInList;
            ListIterator<String> iterator;

            linesInList = lines.collect(Collectors.toList());
            iterator = linesInList.listIterator();

            while(iterator.hasNext()) {
                edgeAsString = iterator.next();
                edgeAsStringLength = edgeAsString.length();
                edgeAsStringTrimmed = edgeAsString.substring(prefixLength, edgeAsStringLength - postfixLength);
                edgeProperties = edgeAsStringTrimmed.split(this.splitterInEdgeNotation, 3);

                this.manageStringToEdge(edgeProperties, verticesFromFile);
            }
        } catch (IOException e) {
            System.out.print("Exception in Graph::readGraph");
        }

        verticesFromFile.values().forEach(this::addVertex);
    }

    protected void manageStringToEdge(String[] edgeProperties, HashMap<Integer, Vertex> vertices) {
        Integer startVertexLabel = Integer.parseInt(edgeProperties[0]),
                endVertexLabel = Integer.parseInt(edgeProperties[2]),
                edgeWeight = Integer.parseInt(edgeProperties[1]);

        Vertex startVertex = this.manageVertexInHashMap(startVertexLabel, vertices),
                endVertex = this.manageVertexInHashMap(endVertexLabel, vertices);

        Edge newEdge = new Edge(startVertex, endVertex, edgeWeight);
        startVertex.addEdge(newEdge);
    }

    protected Vertex manageVertexInHashMap(Integer vertexLabel, HashMap<Integer, Vertex> vertices) {
        Vertex newVertex;

        if(vertices.containsKey(vertexLabel)) {
            return vertices.get(vertexLabel);
        } else {
            newVertex = new Vertex(vertexLabel);
            vertices.put(vertexLabel, newVertex);
            return newVertex;
        }
    }

    public void saveGraph() {
        if(this.filenameOutput == null) {
            return;
        }

        try{
            PrintWriter writer = new PrintWriter(this.filenameOutput, "UTF-8");

            String edgeAsString;
            for(Vertex singleVertex : this.getIterableList()) {
                for(Edge singleEdge : singleVertex.getEdges()) {
                    edgeAsString = this.prefixInEdgeNotation
                            + singleEdge.getStartVertex().getLabel()
                            + this.splitterInEdgeNotation
                            + singleEdge.getWeight()
                            + this.splitterInEdgeNotation
                            + singleEdge.getEndVertex().getLabel()
                            + this.postfixInEdgeNotation;

                    writer.println(edgeAsString);
                }
            }

            writer.close();
        } catch (IOException e) {
            System.out.print("Exception in Graph::saveGraph");
        }
    }

    @Override
    public String toString() {
        String graphAsString = new String();

        for(Vertex singleVertex : this.getIterableList()) {
            graphAsString += " " + singleVertex.getLabel() + "(dist " + singleVertex.getDistance() + "):\t";
            for(Edge singleEdge : singleVertex.getEdges()) {
                graphAsString += " " + singleEdge + " ";
            }
            graphAsString += "\n";
        }

        return graphAsString;
    }
}