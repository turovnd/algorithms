package Lab4;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Created by Николай on 25.04.2017.
 */

public class TaskD {

    private static int m;
    private static Edge[] edges;
    private static boolean[] used;

    static class Edge {
        int from;
        int to;
        long weight;

        Edge(int from, int to, long weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("src/Lab4/path.in"));
        PrintWriter out = new PrintWriter("src/Lab4/path.out");

        String graphSize = in.readLine();
        StringTokenizer st = new StringTokenizer(graphSize);

        int n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(st.nextToken());

        edges = new Edge[m];

        String value;
        int s, e;
        long weight;

        for (int i = 0; i < m; i++) {
            value = in.readLine();
            st = new StringTokenizer(value);
            s = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());
            weight = Long.parseLong(st.nextToken());
            edges[i] = new Edge(s - 1, e - 1, weight);
        }

        start -= 1;
        double[] dist = new double[n];
        int[] parent = new int[n];
        Arrays.fill(dist, Double.MAX_VALUE);
        Arrays.fill(parent, -1);
        dist[start] = 0.0;

        used = new boolean[n];

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < m; j++) {
                if (dist[edges[j].to] > dist[edges[j].from] + edges[j].weight) {
                    dist[edges[j].to] = dist[edges[j].from] + edges[j].weight;
                    parent[edges[j].to] = edges[j].from;
                }
            }
        }
        for (int j = 0; j < m; j++) {
            if (dist[edges[j].to] > dist[edges[j].from] + edges[j].weight) {
                used[edges[j].to] = true;
            }
        }


        for (int i = 0; i < n; i++) {
            if(used[i]) {
                DFS(i);
            }
        }
        for (int i = 0; i < n; i++) {
            if (dist[i] == Double.MAX_VALUE) {
                out.println("*");
                continue;
            }
            if (used[i] || dist[i] == Double.MIN_VALUE) {
                out.println("-");
                continue;
            }
            out.println((long) dist[i]);
        }

        in.close();
        out.close();

    }

    private static void DFS(int vertex) {
        used[vertex] = true;
        for (int i = 0; i < m; i++) {
            if (edges[i].from == vertex && !used[edges[i].to]) {
                DFS(edges[i].to);
            }
        }
    }

}
