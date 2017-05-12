package Lab5;

import javafx.util.Pair;

import java.io.*;
import java.util.*;

/**
 * Created by Николай on 12.05.2017.
 **/

public class TaskC {

    static class Edge {
        int end, reverse, capacity, number, start;
        long flow;

        Edge(int start, int end, int reverse, int capacity, int number) {
            this.start = start;
            this.end = end;
            this.reverse = reverse;
            this.capacity = capacity;
            this.number = number;
        }
    }

    private static int n;
    private static ArrayList<ArrayList<Edge>> graph;
    private static int[] dist;

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("src/Lab5/decomposition.in"));
        PrintWriter out = new PrintWriter("src/Lab5/decomposition.out");

        String[] str = in.readLine().split("[ ]");
        n = Integer.parseInt(str[0]);
        int m = Integer.parseInt(str[1]);

        graph = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        int from, to, weight;
        for (int i = 0; i < m; i++) {
            str     = in.readLine().split("[ ]");
            from    = Integer.parseInt(str[0]) - 1;
            to      = Integer.parseInt(str[1]) - 1;
            weight  = Integer.parseInt(str[2]);

            graph.get(from).add(new Edge(from, to, graph.get(to).size(), weight, i));
            graph.get(to).add(new Edge(to, from, graph.get(from).size() - 1, 0, i));
        }


        maxFlow(0, n - 1);

        Pair<Long, StringBuilder> res = fullDecomposition(0);

        out.println(res.getKey());
        out.print(res.getValue());

        in.close();
        out.close();
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
                if (dist[edge.end] < 0 && edge.flow < edge.capacity) {
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
            if (dist[edge.end] == dist[from] + 1 && edge.flow < edge.capacity) {
                long dinicFlow = dinicDFS(path, edge.end, to, Math.min(flow, edge.capacity - edge.flow));
                if (dinicFlow > 0) {
                    edge.flow += dinicFlow;
                    graph.get(edge.end).get(edge.reverse).flow -= dinicFlow;
                    return dinicFlow;
                }
            }
        }
        return 0;
    }


    private static Pair<Long, StringBuilder> fullDecomposition(int from) {
        long count = 0;
        StringBuilder result = new StringBuilder();

        Pair<Long, LinkedList<Edge>> pathWithFlow = simpleDecomposition(from);

        while (pathWithFlow != null) {
            result.append(pathWithFlow.getKey()).append(" ").append(pathWithFlow.getValue().size()).append(" ");
            for (Edge edge : pathWithFlow.getValue()) {
                result.append(edge.number + 1).append(" ");
            }
            result.append("\n");
            count++;
            pathWithFlow = simpleDecomposition(from);
        }
        return new Pair<>(count, result);
    }


    private static Pair<Long, LinkedList<Edge>> simpleDecomposition(int from) {
        LinkedList<Edge> queue = new LinkedList<>();
        int[] visited = new int[n];
        int v = from;
        while (visited[v] == 0) {
            if (v == n - 1) {
                break;
            }
            Edge edge = null;
            for (int i = 0; i < graph.get(v).size(); i++) {
                if (graph.get(v).get(i).flow > 0) {
                    edge = graph.get(v).get(i);
                    break;
                }
            }
            if (edge == null) {
                return null;
            }
            queue.add(edge);
            visited[v] = 1;
            v = edge.end;
        }
        if (visited[v] == 1) {
            System.out.println(1);
            while (queue.getFirst().start != v) {
                queue.removeFirst();
            }
        }
        long minFlow = Long.MAX_VALUE;
        for (Edge edge : queue) {
            if (edge.flow < minFlow) {
                minFlow = edge.flow;
            }
        }
        for (Edge edge : queue) {
            edge.flow = edge.flow - minFlow;
        }
        return new Pair<>(minFlow, queue);
    }

}