package Lab3;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by Николай on 11.04.2017
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


    private static long find_MST (Graph graph) {

        int root = 0;
        long[] key = new long[graph.vertex];
        int[] parent = new int[graph.vertex];
        long[] label = new long[graph.vertex];
        long[] сonnection_component = new long[graph.vertex];

        long result = 0;

        while (true) {

            for (int i = 0; i < graph.vertex; i++) {
                key[i] = Long.MAX_VALUE;
                label[i] = -1;
                сonnection_component[i] = -1;
            }

            for (Edge edge : graph.adj) {
                if (edge.from == edge.to) continue;

                if (edge.weight < key[edge.to]) {
                    key[edge.to] = edge.weight;
                    parent[edge.to] = edge.from;
                }
            }

            key[root] = 0;
            for (int i = 0; i < graph.vertex; i++) {
                if (key[i] == Long.MAX_VALUE) return -1;
                result += key[i];
            }

            long K = 0;

            for (int i = 0; i < graph.vertex; i++) {
                int number = i;
                while ((number != root) && (сonnection_component[number] == -1)) {
                    сonnection_component[number] = i;
                    number = parent[number];
                }
                if ((number != root) && (сonnection_component[number] == i)) {
                    while (label[number] == -1) {
                        label[number] = K;
                        number = parent[number];
                    }
                    ++K;
                }
            }

            if (K == 0) break;

            for (int i = 0; i < graph.vertex; i++) {
                if (label[i] == -1) label[i] = K++;
            }

            for (int i = 0; i < graph.edges; i++) {
                long xLabel = label[graph.adj.get(i).from];
                long yLabel = label[graph.adj.get(i).to];

                if (xLabel != yLabel)
                    graph.adj.get(i).weight -= key[graph.adj.get(i).to];

                graph.adj.get(i).from = (int) xLabel;
                graph.adj.get(i).to = (int) yLabel;
            }

            root = (int)label[root];
            graph.vertex = (int) K;
        }

        return result;

    }



    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("src/Lab3/chinese.in"));
        BufferedWriter out = new BufferedWriter(new FileWriter("src/Lab3/chinese.out"));

        String[] str = in.readLine().split(" ");

        int n = Integer.parseInt(str[0]);
        int m = Integer.parseInt(str[1]);

        Graph graph = new Graph(n, m);

        int from, to;
        long weight;

        for (int i = 0; i < m; i++) {
            str = in.readLine().split(" ");
            from = Integer.parseInt(str[0]) - 1;
            to = Integer.parseInt(str[1]) - 1;
            weight = Long.parseLong(str[2]);

            graph.adj.add(new Edge(from, to, weight));
        }

        long result = find_MST(graph);

        if (result != -1) {
            out.write("YES\n");
            out.write(Long.toString(result));
        } else {
            out.write("NO");
        }


        in.close();
        out.close();

    }


}
