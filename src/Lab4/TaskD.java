package Lab4;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Николай on 10.04.2017
 */
public class TaskD {

    private static class Edge {
        int from;
        int to;
        long weight;

        Edge (int from, int to, long weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    private static class Graph {
        int vertex;
        int edges;
        ArrayList<Edge> adj;

        Graph(int vertex, int edges) {
            this.vertex = vertex;
            this.edges = edges;
            adj = new ArrayList<Edge>(vertex);
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("src/Lab4/path.in"));
        BufferedWriter out = new BufferedWriter(new FileWriter("src/Lab4/path.out"));

        StreamTokenizer tokenizer = new StreamTokenizer(in);
        tokenizer.nextToken();
        int n = (int) tokenizer.nval;
        tokenizer.nextToken();
        int m = (int) tokenizer.nval;
        tokenizer.nextToken();
        int start = (int) tokenizer.nval;

        ArrayList<Edge> edges = new ArrayList<>();

        int s, e, w;
        for (int i = 0; i < m; i++) {
            tokenizer.nextToken();
            s = (int) tokenizer.nval - 1;
            tokenizer.nextToken();
            e = (int) tokenizer.nval - 1;
            tokenizer.nextToken();
            w = (int) tokenizer.nval;

            edges.add(new Edge(s, e, w));
        }

        int [] d = new int[n];
        Arrays.fill(d, Integer.MAX_VALUE);
        d[start] = 0;

//        for (Edge edge : edges) {
//            if (edge.start == start) {
//                d[edge.end] = edge.weight;
//            } else if (d[edge.end] > d[edge.start] + edge.weight) {
//                d[edge.end] = d[edge.start] + edge.weight;
//            }
//        }


        for(int i = 0; i < n; i++) {
            out.write(Integer.toString(d[i]) + " ");
        }

        in.close();
        out.close();

    }
}
