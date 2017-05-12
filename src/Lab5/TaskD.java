package Lab5;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Николай on 12.05.2017.
 **/

public class TaskD {
    
    static class Edge {
        int end, reverse, minCapacity, maxCapacity, flow;

        Edge(int end, int reverse, int minCapacity, int maxCapacity) {
            this.end = end;
            this.reverse = reverse;
            this.minCapacity = minCapacity;
            this.maxCapacity = maxCapacity;
        }
    }

    static class MinEdge {
        int start, end, minCapacity;

        MinEdge(int start, int end, int minCapacity) {
            this.start = start;
            this.end = end;
            this.minCapacity = minCapacity;
        }
    }
    

    private static int n;
    private static ArrayList<ArrayList<Edge>> graph;
    private static int[] dist;

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("src/Lab5/circulation.in"));
        PrintWriter out = new PrintWriter("src/Lab5/circulation.out");

        String[] str = in.readLine().split("[ ]");
        n = Integer.parseInt(str[0]) + 2;
        int m = Integer.parseInt(str[1]);

        ArrayList<MinEdge> matrixEdges = new ArrayList<>();

        graph = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        int from, to, minCapacity, maxCapacity;
        for (int i = 0; i < m; i++) {
            str          = in.readLine().split("[ ]");
            from         = Integer.parseInt(str[0]);
            to           = Integer.parseInt(str[1]);
            minCapacity  = Integer.parseInt(str[2]);
            maxCapacity  = Integer.parseInt(str[3]);

            addEdge(0, to, minCapacity);
            addEdge(from, n - 1, minCapacity);
            matrixEdges.add(new MinEdge(from, to, minCapacity));
            addEdge(from, to, maxCapacity - minCapacity);

        }


        maxFlow(0, n - 1);

        if (checkCirculation()) {
            out.println("YES");
            for (int i = 0; i < m; i++) {
                int start = matrixEdges.get(i).start;
                int end = matrixEdges.get(i).end;
                minCapacity = matrixEdges.get(i).minCapacity;
                for (int j = 0; j < graph.get(start).size(); j++) {
                    if (graph.get(start).get(j).end == end) {
                        out.println(graph.get(start).get(j).flow + minCapacity);
                        break;
                    }
                }
            }
        } else {
            out.print("NO");
        }

        in.close();
        out.close();

    }

    private static void addEdge(int start, int end, int maxCapacity) {
        graph.get(start).add(new Edge(end, graph.get(end).size(), 0, maxCapacity));
        graph.get(end).add(new Edge(start, graph.get(start).size() - 1, 0, 0));
    }


    private static boolean checkCirculation() {
        for (int i = 0; i < graph.get(0).size(); i++) {
            if (graph.get(0).get(i).flow < graph.get(0).get(i).maxCapacity) {
                return false;
            }
        }
        return true;
    }


    private static void maxFlow(int from, int to) {
        dist = new int[n];

        while (dinicBFS()) {
            int[] path = new int[n];
            while (true) {
                long dinicFlow = dinicDFS(path, from, to, Long.MAX_VALUE);
                if (dinicFlow == 0)
                    break;
            }
        }
    }

    private static boolean dinicBFS() {
        Arrays.fill(dist, -1);
        dist[0] = 0;
        int[] Q = new int[graph.size()];
        int size = 0;
        Q[size++] = 0;
        for (int i = 0; i < size; i++) {
            int v = Q[i];
            for (Edge edge : graph.get(v)) {
                if (dist[edge.end] < 0 && edge.flow < edge.maxCapacity) {
                    dist[edge.end] = dist[v] + 1;
                    Q[size++] = edge.end;
                }
            }
        }
        return dist[n - 1] >= 0;
    }

    private static long dinicDFS(int[] path, int from, int to, long flow) {
        if (from == to) return flow;

        for (; path[from] < graph.get(from).size(); ++path[from]) {
            Edge edge = graph.get(from).get(path[from]);
            if (dist[edge.end] == dist[from] + 1 && edge.flow < edge.maxCapacity) {
                long dinicFlow = dinicDFS(path, edge.end, to, Math.min(flow, edge.maxCapacity - edge.flow));
                if (dinicFlow > 0) {
                    edge.flow += dinicFlow;
                    graph.get(edge.end).get(edge.reverse).flow -= dinicFlow;
                    return dinicFlow;
                }
            }
        }
        return 0;
    }

}