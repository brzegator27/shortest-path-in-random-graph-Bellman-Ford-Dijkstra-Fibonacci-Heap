import Graph.GraphArrayList;
import Graph.GraphFibonacciHeap;
import bellman_ford.BellmanFord;
import dijkstra.Dijkstra;

import java.util.Random;

/**
 * Created by Jakub on 2015-10-22.
 */
public class Main {
    public static void main(String[] args) {
//        String fileNameIn = "C:\\Users\\Jakub\\Documents\\Java projects\\shortestPathInRandomGraphBFDFH\\resources\\graph.txt";
        String fileNameIn = "C:\\Users\\Jakub\\Documents\\Java projects\\shortestPathInRandomGraphBFDFH\\resources\\simple_test_out.txt";
        String fileNameOut = "C:\\Users\\Jakub\\Documents\\Java projects\\shortestPathInRandomGraphBFDFH\\resources\\simple_test_out.txt";

        //  "C:\\Users\\Jakub\\Documents\\Java - SBobek\\class 2\\home\\exercise 1\\crossword\\dictionary\\cwdb.txt"
        GraphArrayList graph_1 = new GraphArrayList(),
                graph_2 = new GraphArrayList();
//        GraphFibonacciHeap graph_1 = new GraphFibonacciHeap(),
//                graph_2 = new GraphFibonacciHeap();

        graph_1.setFilenames(fileNameIn, fileNameOut);
        Random generator = new Random();
        Integer verticesNumber = 10000,
                additionalEdgesNumber = 30000,
                sourceVertexLabel = generator.nextInt(verticesNumber) + 1,
                endVertexLabel = generator.nextInt(verticesNumber) + 1;
        graph_1.generateRandomGraph(verticesNumber, additionalEdgesNumber);
        graph_1.saveGraph();

        graph_2.setFilenames(fileNameIn, fileNameOut);
        graph_2.readGraph();

//        new BellmanFord(graph_2, sourceVertexLabel, endVertexLabel);
        new Dijkstra(graph_2, sourceVertexLabel, endVertexLabel);
        graph_2.showBestRoute(sourceVertexLabel, endVertexLabel);
//        new BellmanFord(graph_1, 1708, 774);
//        graph_1.showBestRoute(1708, 774);
//        System.out.print(graph_1.toString());
    }
}
