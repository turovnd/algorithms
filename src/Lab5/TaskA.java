package Lab5;

import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created by Николай on 09.05.2017.
 **/

public class TaskA {

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("src/Lab5/maxflow.in"));
        PrintWriter out = new PrintWriter("src/Lab5/maxflow.out");

        String[] str = in.readLine().split("[ ]");
        int n = Integer.parseInt(str[0]);
        int m = Integer.parseInt(str[1]);

        boolean[] visited = new boolean[n];
        int[] graph = new int[n];
        int[][] array = new int[n][n];


        int source, sink;
        long capacity;

        for (int i = 0; i < m; i++) {
            str = in.readLine().split(" ");
            source   = Integer.parseInt(str[0]) - 1;
            sink     = Integer.parseInt(str[1]) - 1;
            capacity = Long.parseLong(str[2]);

            graph.addVertex(source, new Pair<>(sink, capacity));
        }

//        out.print(max);
         System.out.print(maxFlow(graph));

        out.close();
        in.close();
    }

}
