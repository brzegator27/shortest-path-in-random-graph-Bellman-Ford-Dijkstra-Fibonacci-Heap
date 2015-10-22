package Graph;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Jakub on 2015-10-22.
 */
public abstract class Graph {
    private LinkedList<Vertex> vertices;

    public Graph() {
        this.vertices = new LinkedList<>();
    }

    public Graph(Vertex vertex) {
        this.vertices = new LinkedList<>();
        this.addVertex(vertex);
    }

    public void addVertex(Vertex vertex) {
        this.vertices.add(vertex);
    }
//  "C:\\Users\\Jakub\\Documents\\Java - SBobek\\class 2\\home\\exercise 1\\crossword\\dictionary\\cwdb.txt"
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

//  "C:\\Users\\Jakub\\Documents\\Java - SBobek\\class 2\\home\\exercise 1\\crossword\\dictionary\\cwdb.txt"
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
