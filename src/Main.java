import Graph.GraphArrayList;

/**
 * Created by Jakub on 2015-10-22.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        String fileNameIn = "C:\\Users\\Jakub\\Documents\\Java projects\\shortestPathInRandomGraphBFDFH\\resources\\simple_test_in.txt";
        String fileNameOut = "C:\\Users\\Jakub\\Documents\\Java projects\\shortestPathInRandomGraphBFDFH\\resources\\simple_test_out.txt";

        //  "C:\\Users\\Jakub\\Documents\\Java - SBobek\\class 2\\home\\exercise 1\\crossword\\dictionary\\cwdb.txt"
        GraphArrayList graph_1 = new GraphArrayList();
        graph_1.setFilenames(fileNameIn, fileNameOut);
        graph_1.readGraph();
        System.out.print(" " + graph_1.toString());
    }
}
