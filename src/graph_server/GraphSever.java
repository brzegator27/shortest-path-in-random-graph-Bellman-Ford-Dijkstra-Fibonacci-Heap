package graph_server;

import Graph.GraphArrayList;
import Graph.Graph;
import com.thoughtworks.xstream.XStream;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Jakub on 2015-10-29.
 */
public class GraphSever {
    public static void main(String[] args) throws IOException {
        if(args.length != 2) {
            System.err.println("Usage: java GraphServer <port number> <sending information format>");
            System.exit(1);
        }

        int portNumber = Integer.parseInt(args[0]);
        SendingFormat sendingFormat = SendingFormat.valueOf(args[1].toUpperCase());

        Graph graph = getGraph();
        String graphAsXML = graphToXML(graph);
        manageConnection(portNumber, sendingFormat, graphAsXML);
    }

    private static void manageConnection(int portNumber, SendingFormat sendingFormat, String message) {
        System.out.println("Port number: " + portNumber + ", sendingFormat: " + sendingFormat.toString());

        try (
                ServerSocket serverSocket = new ServerSocket(portNumber);
                Socket clientSocket = serverSocket.accept();
                PrintWriter out =  new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
        ) {
//            out.print(graphAsXML);
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println(inputLine);
                out.println(message + " sendingFormat: " + sendingFormat.toString());
            }
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port " + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }

    private static Graph getGraph() {
        String fileNameIn = "C:\\Users\\Jakub\\Documents\\Java projects\\shortestPathInRandomGraphBFDFH\\resources\\graph.txt";

        GraphArrayList graph = new GraphArrayList();
        graph.setFilenames(fileNameIn, null);
        graph.readGraph();
//        Integer verticesNumber = 10000,
//                additionalEdgesNumber = 500000;
//        graph.generateRandomGraph(10000, additionalEdgesNumber);
        return graph;
    }

    private static String graphToXML(Graph graph) {
        XStream xStream = new XStream();
        return xStream.toXML(graph);
//        return "grapahdghg";
    }
}
