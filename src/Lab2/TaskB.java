package Lab2;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Николай on 27.03.2017.
 */
public class TaskB {

    public enum Color { WHITE, GREY, BLACK }


    private static class Graph {
        Color[] color;
        int[] parent;
        int[] open;
        int[] close;
        int ver;
        int rib;
        ArrayList<LinkedList<Integer>> adj;

        boolean isCycled;
        int cycleStart;

        public Graph(int ver, int rib) {
            this.ver = ver;
            this.rib = rib;
            color = new Color[ver];
            parent = new int[ver];
            open = new int[ver];
            close = new int[ver];
            isCycled = false;
            adj = new ArrayList<>();
            for (int i = 0; i < ver; i++) {
                adj.add(new LinkedList());
            }
        }

        public void addRib(int from, int to) {
            adj.get(from).add(to);
        }
    }


    private static LinkedList<Integer> DFS (Graph graph) {
        for (int i = 0; i < graph.ver; i++) {
            graph.color[i] = Color.WHITE;
            graph.parent[i] = -1;
        }
        int time = 0;
        LinkedList<Integer> cycle = new LinkedList();
        for (int i = 0; i < graph.ver; i++) {
            if (graph.color[i] == Color.WHITE) {
                DFS_Visit(graph, i, time, cycle);
                if (!cycle.isEmpty()) {
                    return cycle;
                }
            }
        }
        return cycle;
    }


    private static void DFS_Visit(Graph graph, int i, int time, LinkedList<Integer> cycle) {

        if (graph.isCycled) {
            return;
        }

        graph.color[i] = Color.GREY;
        time++;
        graph.open[i] = time;
        cycle.add(i);

        for (Integer ver : graph.adj.get(i)) {
            if (graph.color[ver] == Color.WHITE) {
                graph.parent[ver] = i;
                DFS_Visit(graph, ver, time, cycle);
                if (graph.isCycled) {
                    return;
                }
            } else if (graph.color[ver] == Color.GREY) {
                graph.parent[ver] = i;
                graph.isCycled = true;
                graph.cycleStart = ver;
                return;
            }
        }
        graph.color[i] = Color.BLACK;
        graph.close[i] = time;
        cycle.removeLast();
    }


    private static LinkedList<Integer> Topological_Sort(Graph graph) {
        return DFS(graph);
    }


    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("src/Lab2/cycle.in"));
        BufferedWriter out = new BufferedWriter(new FileWriter("src/Lab2/cycle.out"));

        String[] str = in.readLine().split("[ ]");
        int n = Integer.parseInt(str[0]);
        int m = Integer.parseInt(str[1]);

        Graph graph = new Graph(n, m);

        int a,b;
        while (m-- > 0) {
            str = in.readLine().split(" ");
            a = Integer.parseInt(str[0]) - 1;
            b = Integer.parseInt(str[1]) - 1;
            graph.addRib(a, b);
        }

        LinkedList<Integer> cycle = Topological_Sort(graph);

        if (graph.isCycled) {
            out.write("YES\n");
            while (cycle.getFirst() != graph.cycleStart)
                cycle.removeFirst();
            for (int ver : cycle) {
                out.write(ver + 1 + " ");
            }
        } else {
            out.write("NO");
        }

        in.close();
        out.close();

    }

}
