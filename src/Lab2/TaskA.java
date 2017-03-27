package Lab2;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Николай on 27.03.2017.
 */
public class TaskA {

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
        int verLeft;

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


    private static void DFS (Graph graph, int[] list) {

        for (int i = 0; i < graph.color.length; i++) {
            graph.color[i] = Color.WHITE;
            graph.parent[i] = -1;
        }
        int time = 0;
        graph.verLeft = graph.ver;
        for (int i = 0; i < graph.ver; i++) {
            if (graph.color[i] == Color.WHITE)
                DFS_Visit(graph, i, time, list);
        }
    }



    private static void DFS_Visit(Graph graph, int i, int time, int[] list) {

        if (graph.isCycled) {
            return;
        }

        graph.color[i] = Color.GREY;
        time++;
        graph.open[i] = time;

        for (Integer ver : graph.adj.get(i)) {
            if (graph.color[ver] == Color.WHITE) {
                graph.parent[ver] = i;
                DFS_Visit(graph, ver, time, list);
            } else {
                if (graph.color[ver] == Color.GREY) {
                    graph.isCycled = true;
                    return;
                }
            }
        }
        graph.color[i] = Color.BLACK;
        graph.close[i] = time;
        list[--graph.verLeft] = i;
    }


    private static int[] Topological_Sort(Graph graph) {
        int[] list = new int[graph.ver];
        DFS(graph, list);
        return list;
    }


    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("src/Lab2/topsort.in"));
        BufferedWriter out = new BufferedWriter(new FileWriter("src/Lab2/topsort.out"));

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

        int[] list = Topological_Sort(graph);

        if (graph.isCycled) {
            out.write("-1");
        } else {
            for (int ver : list) {
                out.write(ver + 1 + " ");
            }
        }

        in.close();
        out.close();

    }

}
