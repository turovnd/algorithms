package Lab2;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Николай on 27.03.2017.
 */
public class TaskD {

    public enum Color { WHITE, GREY, BLACK }

    private static class Graph {
        Color[] color;

        int ver;
        int rib;
        ArrayList<LinkedList<Integer>> adj;

        LinkedList<Integer> order;
        LinkedList<Integer> comps;
        int compNum;

        public Graph(int ver, int rib) {
            this.ver = ver;
            this.rib = rib;
            this.compNum = 0;
            color = new Color[ver];
            order = new LinkedList();
            comps = new LinkedList();
            adj = new ArrayList<>();
            for (int i = 0; i < ver; i++) {
                adj.add(new LinkedList());
            }
        }

        public void addRib(int from, int to) {
            adj.get(from).add(to);
        }

        public void __T () {
            ArrayList<LinkedList<Integer>> adj__T = new ArrayList(adj.size());
            for (int i = 0; i<ver; i++) {
                adj__T.add(new LinkedList<>());
            }

            for (int i = 0; i < adj.size(); i++) {
                LinkedList<Integer> list = adj.get(i);
                for (int j = 0; j<list.size(); j++) {
                    int ver = list.get(j);
                    LinkedList<Integer> list__T = adj__T.get(ver);
                    list__T.add(i);
                }
            }
            adj = adj__T;
        }

    }


    private static void DFS (Graph graph) {
        for (int i = 0; i < graph.ver; i++) {
            graph.color[i] = Color.WHITE;
        }
        for (int i = 0; i < graph.ver; i++) {
            if (graph.color[i] == Color.WHITE) {
                DFS_Visit(graph, i);
            }
        }
    }


    private static void DFS_Visit(Graph graph, int i) {
        graph.color[i] = Color.GREY;
        for (Integer ver : graph.adj.get(i)) {
            if (graph.color[ver] == Color.WHITE) {
                DFS_Visit(graph, ver);
            }
        }
        graph.color[i] = Color.BLACK;
        graph.order.addFirst(i);
    }


    private static void DFS__T (Graph graph) {
        for (int i = 0; i < graph.ver; i++) {
            graph.color[i] = Color.WHITE;
        }
        for (int i = 0; i < graph.ver; i++) {
            if (graph.color[graph.order.get(i)] == Color.WHITE) {
                graph.compNum++;
                DFS_Visit__T(graph, graph.order.get(i));
            }
        }

    }

    private static void DFS_Visit__T(Graph graph, int i) {
        graph.color[i] = Color.GREY;
        for (Integer ver : graph.adj.get(i)) {
            if (graph.color[ver] == Color.WHITE) {
                DFS_Visit__T(graph, ver);
            }
        }
        graph.color[i] = Color.BLACK;
        graph.comps.add(graph.compNum);
    }


    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("cond.in"));
        BufferedWriter out = new BufferedWriter(new FileWriter("cond.out"));

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

        DFS(graph);
        graph.__T();
        DFS__T(graph);

        out.write(graph.compNum + "\n");
        for (int v = 0; v < graph.ver; v++) {
            out.write(graph.comps.get(v) + " ");
        }
        //System.out.println(graph.order.toString());
        //System.out.println(graph.comps.toString());

        in.close();
        out.close();
    }

}
