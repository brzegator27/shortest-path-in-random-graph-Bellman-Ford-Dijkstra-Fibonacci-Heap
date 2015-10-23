package Graph;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Jakub on 2015-10-22.
 */
public abstract class Graph {
//    private LinkedList<Vertex> vertices;
    String filenameInput, filenameOutput;

    public abstract void generateRandomGraph(int verticesCount, int edgesCount);
    public abstract void addVertex(Vertex vertex);
    public abstract void addEdge(Edge edge);
    public abstract void addEdgeToVertex(Vertex startVertex, Vertex endVertex, Integer connectingEdgeWeight);
    public abstract void addEdgeToVertex(Integer startVertexLabel, Integer endVertexLabel, Integer connectingEdgeWeight);
    public abstract Vertex getVertexByLabel(Integer label);
    public abstract void inicializeSingleSource(Integer startVertexLabel, Integer startVertexDistance);
    public abstract Iterable<Vertex> getIterableList();
    public abstract Integer getVertexCount();

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

    public void setFilenames(String filenameInput, String filenameOutput) {
        this.filenameInput = filenameInput;
        this.filenameOutput = filenameOutput;
    }


    protected void readGraph(String filename) {
//        dict = new LinkedList<Entry>();
        Path path = Paths.get(filename);

        try {
            Stream<String> lines = Files.lines(path);
//			boolean isWord = true;
            String word, clue;
            List<String> linesInList;
            ListIterator<String> iterator;

            linesInList = lines.collect(Collectors.toList());
            iterator = linesInList.listIterator();

            while(iterator.hasNext()) {
                word = iterator.next();
                if( ! iterator.hasNext()) {
                    return;
                }
                clue = iterator.next();

//                add(word, clue);
            }

        } catch (IOException e) {

        }
    }

    public void saveGraph(String filename) {
        try{
            PrintWriter writer = new PrintWriter(filename, "UTF-8");

//            ListIterator<Entry> iterator = dict.listIterator();

//            while(iterator.hasNext()) {
//                Entry entry = iterator.next();
//                writer.println(entry.getWord());
//                writer.println(entry.getClue());
//            }

            writer.close();
        } catch (IOException e) {

        }
    }
}
