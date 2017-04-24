package Lab4;

import javafx.util.Pair;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * Created by Николай on 10.04.2017
 */
public class TaskC1 {

//    private static class Edge {
//        int from;
//        int to;
//        long weight;
//
//        Edge (int from, int to, long weight) {
//            this.from = from;
//            this.to = to;
//            this.weight = weight;
//        }
//    }

    private static class Graph {
        int vertex;
        int edges;
        ArrayList<LinkedList<pair>> adj;

        Graph(int vertex, int edges) {
            this.vertex = vertex;
            this.edges = edges;
            adj = new ArrayList<>();
            for (int i = 0; i < vertex; i++) {
                adj.add(new LinkedList<pair>());
            }
            //adj = new ArrayList<Edge>(vertex);
        }

        void addPair(int from, pair p) {
            adj.get(from).add(p);
        }
    }

    private static class pair {
        int to;
        int weight;
        pair (int t, int w) {
            this.to = t;
            this.weight = w;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("src/Lab4/pathbgep.in"));
        BufferedWriter out = new BufferedWriter(new FileWriter("src/Lab4/pathbgep.out"));

        StreamTokenizer tokenizer = new StreamTokenizer(in);
        tokenizer.nextToken();
        int n = (int) tokenizer.nval;
        tokenizer.nextToken();
        int m = (int) tokenizer.nval;

        Graph graph = new Graph(n, m);

        int s,e,w;
        for (int i = 0; i < m; i++) {
            tokenizer.nextToken();
            s = (int) tokenizer.nval - 1;
            tokenizer.nextToken();
            e = (int) tokenizer.nval - 1;
            tokenizer.nextToken();
            w = (int) tokenizer.nval;

            graph.addPair(s, new pair(e,w));
            graph.addPair(e, new pair(s,w));
        }


        for(int i = 0; i < n; i++) {
            graph.adj.get(i);

            //out.write(Integer.toString(d[i]) + " ");
        }

        in.close();
        out.close();

    }
}
