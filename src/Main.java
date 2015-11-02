import Graph.GraphArrayList;
import Graph.GraphFibonacciHeap;
import bellman_ford.BellmanFord;
import dijkstra.Dijkstra;
import fibonacci_heap.FibonacciHeap;

import java.util.Random;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;


/**
 * Created by Jakub on 2015-10-22.
 */
public class Main {
    public static void main(String[] args) throws IOException {
//        Testing connection with server
//        String hostName = "localhost";
//        int portNumber = 444;
//
//        try (
//            Socket echoSocket = new Socket(hostName, portNumber);
//            PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
//            BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
//            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))
//        ) {
//            String userInput;
//            while ((userInput = stdIn.readLine()) != null) {
//                out.println(userInput);
//                System.out.println("echo: " + in.readLine());
//            }
//        } catch (UnknownHostException e) {
//            System.err.println("Don't know about host " + hostName);
//            System.exit(1);
//        } catch (IOException e) {
//            System.err.println("Couldn't get I/O for the connection to " + hostName);
//            System.exit(1);
//        }


//        String fileNameIn = "C:\\Users\\Jakub\\Documents\\Java projects\\shortestPathInRandomGraphBFDFH\\resources\\graph.txt";
        String fileNameIn = "C:\\Users\\Jakub\\Documents\\Java projects\\shortestPathInRandomGraphBFDFH\\resources\\simple_test_in.txt";
        String fileNameOut = "C:\\Users\\Jakub\\Documents\\Java projects\\shortestPathInRandomGraphBFDFH\\resources\\simple_test_out.txt";

//        GraphArrayList graph_1 = new GraphArrayList(),
//                graph_2 = new GraphArrayList();
//        GraphFibonacciHeap graph_1 = new GraphFibonacciHeap(),
//                graph_2 = new GraphFibonacciHeap();

        GraphFibonacciHeap graph_1 = new GraphFibonacciHeap();
        GraphArrayList graph_2 = new GraphArrayList();

//        graph_1.setFilenames(fileNameIn, fileNameOut);
        graph_1.setFilenames(fileNameOut, null);
        graph_2.setFilenames(fileNameOut, null);
        Random generator = new Random();
        Integer verticesNumber = 10000,
                additionalEdgesNumber = 500000,
                sourceVertexLabel = generator.nextInt(verticesNumber) + 1,
                endVertexLabel = generator.nextInt(verticesNumber) + 1;
//        graph_1.generateRandomGraph(10000, additionalEdgesNumber);
//        graph_2.generateRandomGraph(10000, additionalEdgesNumber);
//        graph_1.saveGraph();

//        graph_2.setFilenames(fileNameIn, fileNameOut);
        graph_1.readGraph();
        graph_2.readGraph();
//        graph_1.readGraph();
//        new BellmanFord(graph_1, 1, 4);
//        new Dijkstra(graph_1, 1, 4);

        long startTime;
        long endTime;
        long totalTime;

        startTime = System.currentTimeMillis();
        new Dijkstra(graph_2, sourceVertexLabel, endVertexLabel);
        endTime   = System.currentTimeMillis();
        totalTime = endTime - startTime;
        System.out.println(totalTime);
        graph_2.showBestRoute(sourceVertexLabel, endVertexLabel);

        startTime = System.currentTimeMillis();
        new Dijkstra(graph_1, sourceVertexLabel, endVertexLabel);
        endTime   = System.currentTimeMillis();
        totalTime = endTime - startTime;
        System.out.println(totalTime);
        graph_1.showBestRoute(sourceVertexLabel, endVertexLabel);

//        new BellmanFord(graph_1, 1708, 774);
//        graph_1.showBestRoute(1708, 774);
//        graph_1.showBestRoute(1, 4);
//        System.out.println(graph_1.toString());
    }

    public void fibHeapTest() {
        String fileNameIn = "C:\\Users\\Jakub\\Documents\\Java projects\\shortestPathInRandomGraphBFDFH\\resources\\simple_test_in.txt";

        GraphFibonacciHeap graph_1 = new GraphFibonacciHeap();

        graph_1.setFilenames(fileNameIn, null);
        graph_1.readGraph();

        System.out.print(graph_1.toString());
        FibonacciHeap fibHeap = graph_1.getFibHeap();
        System.out.println(fibHeap.toString());
        System.out.println("start extracting");
        fibHeap.extractMin();
        System.out.println(fibHeap.toString());

        System.out.println("start decreasing");
        fibHeap.decreaseKey(fibHeap.getMin().getChild(), 123);
        fibHeap.decreaseKey(fibHeap.getMin().getRight(), 125);
        System.out.println("decreasing continuing");
        System.out.println(fibHeap.toString());
        fibHeap.decreaseKey(fibHeap.getMin().getRight().getChild(), 12);
        System.out.println(fibHeap.toString());

        System.out.println("start extracting");
        fibHeap.extractMin();
        System.out.println(fibHeap.toString());
        fibHeap.extractMin();
        System.out.println(fibHeap.toString());
        fibHeap.extractMin();
        System.out.println(fibHeap.toString());
        fibHeap.extractMin();
        System.out.println(fibHeap.toString());
    }
}
