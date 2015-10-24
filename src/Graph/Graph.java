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
    String prefixInEdgeNotation = "[";
    String postfixInEdgeNotation = "]";
    String splitterInEdgeNotation = ",";

    public abstract void generateRandomGraph(int verticesCount, int edgesCount);
    public abstract void addVertex(Vertex vertex);
    public abstract void addEdge(Edge edge);
//    public abstract void addEdgeToVertex(Vertex startVertex, Vertex endVertex, Integer connectingEdgeWeight);
//    public abstract void addEdgeToVertex(Integer startVertexLabel, Integer endVertexLabel, Integer connectingEdgeWeight);
    public abstract Vertex getVertexByLabel(Integer label);
    public abstract void initialiseSingleSource(Integer startVertexLabel, Integer startVertexDistance);
    public abstract Iterable<Vertex> getIterableList();
    public abstract Integer getVertexCount();


    public void setFilenames(String filenameInput, String filenameOutput) {
        this.filenameInput = filenameInput;
        this.filenameOutput = filenameOutput;
    }

    public void readGraph() {
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
            graphAsString += singleVertex.getLabel() + ":";
            for(Edge singleEdge : singleVertex.getEdges()) {
                graphAsString += " " + singleEdge + " ";
            }
            graphAsString += "\n";
        }

        return graphAsString;
    }
}


//    public Graph() {
//        this.vertices = new LinkedList<>();
//    }

//    public Graph(Vertex vertex) {
//        this.vertices = new LinkedList<>();
//        this.addVertex(vertex);
//    }
//
//    public void addVertex(Vertex vertex) {
//        this.vertices.add(vertex);
//    }
