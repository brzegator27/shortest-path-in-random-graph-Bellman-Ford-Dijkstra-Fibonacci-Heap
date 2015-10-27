import Graph.GraphArrayList;
import Graph.GraphFibonacciHeap;
import bellman_ford.BellmanFord;
import dijkstra.Dijkstra;

/**
 * Created by Jakub on 2015-10-22.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
//        String fileNameIn = "C:\\Users\\Jakub\\Documents\\Java projects\\shortestPathInRandomGraphBFDFH\\resources\\graph.txt";
        String fileNameIn = "C:\\Users\\Jakub\\Documents\\Java projects\\shortestPathInRandomGraphBFDFH\\resources\\simple_test_in.txt";
        String fileNameOut = "C:\\Users\\Jakub\\Documents\\Java projects\\shortestPathInRandomGraphBFDFH\\resources\\simple_test_out.txt";

        //  "C:\\Users\\Jakub\\Documents\\Java - SBobek\\class 2\\home\\exercise 1\\crossword\\dictionary\\cwdb.txt"
//        GraphArrayList graph_1 = new GraphArrayList();
        GraphFibonacciHeap graph_1 = new GraphFibonacciHeap();

        graph_1.setFilenames(fileNameIn, fileNameOut);
//        graph_1.generateRandomGraph(10000, 100);
        graph_1.readGraph();
//        graph_1.saveGraph();


//        new BellmanFord(graph_1, 1, 4);
        new Dijkstra(graph_1, 1, 4);
        graph_1.showBestRoute(1, 4);
//        new BellmanFord(graph_1, 1708, 774);
//        graph_1.showBestRoute(1708, 774);
//        System.out.print(graph_1.toString());


//        graph_1.toString();
//        System.out.println(graph_1.getVertexByLabel(1).getParentVertex().getLabel());
//        System.out.println("parent of 2 is: " + graph_1.getVertexByLabel(2).getParentVertex().getLabel());
//        System.out.println("parent of 3 is: " + graph_1.getVertexByLabel(3).getParentVertex().getLabel());
//        System.out.println("parent of 4 is: " + graph_1.getVertexByLabel(4).getParentVertex().getLabel());
//        System.out.println("parent of 5 is: " + graph_1.getVertexByLabel(5).getParentVertex().getLabel());
    }
}
