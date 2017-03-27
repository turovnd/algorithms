package Lab2;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * Created by Николай on 27.03.2017.
 */
public class TaskD {

    public enum Color { WHITE, GREY, BLACK }

    private static class Graph {
        Color[] color;
        boolean[] visited;
        int[] parent;
        int[] open;
        int[] close;
        int ver;
        int rib;
        ArrayList<LinkedList<Integer>> adj;

        LinkedList<Integer> order;
        LinkedList<Integer> comp;
        int[] components;
        int compNum;

        public Graph(int ver, int rib) {
            this.ver = ver;
            this.rib = rib;
            visited = new boolean[ver];
            color = new Color[ver];
            parent = new int[ver];
            open = new int[ver];
            close = new int[ver];
            components = new int[ver];
            order = new LinkedList();
            comp = new LinkedList();
            adj = new ArrayList<>();
            for (int i = 0; i < ver; i++) {
                adj.add(new LinkedList());
            }
        }

        public void addRib(int from, int to) {
            adj.get(from).add(to);
        }

        public void sortVer(){
            Arrays.sort(close);
        }

        public void __T () {
            ArrayList<LinkedList<Integer>> adj__T = new ArrayList(adj.size());
            for (int i = 0; i<ver; i++)
                adj__T.add(new LinkedList<>());

            for (int i = 0; i < adj.size(); i++) {
                LinkedList<Integer> list = adj.get(i);
                for (int j = 0; j<list.size(); j++) {
                    int vertix = list.get(j);
                    LinkedList<Integer> list__T = adj__T.get(vertix);
                    list__T.add(i);
                }
            }
            adj = adj__T;
        }

    }


    private static void DFS (Graph graph) {
        for (int i = 0; i < graph.ver; i++) {
            graph.color[i] = Color.WHITE;
            graph.parent[i] = -1;
        }
        int time = 0;
        for (int i = 0; i < graph.ver; i++) {
            if (graph.color[i] == Color.WHITE) {
                time = DFS_Visit(graph, i, time);
            }
        }
    }


    private static int DFS_Visit(Graph graph, int i, int time) {

        graph.color[i] = Color.GREY;
        time++;
        graph.open[i] = time;

        for (Integer ver : graph.adj.get(i)) {
            if (graph.color[ver] == Color.WHITE) {
                graph.parent[ver] = i;
                time = DFS_Visit(graph, ver, time);
            }
        }
        graph.color[i] = Color.BLACK;
        graph.close[i] = time;
        return time + 1;
    }


    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("src/Lab2/cond.in"));
        BufferedWriter out = new BufferedWriter(new FileWriter("src/Lab2/cond.out"));

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
        graph.sortVer();
        graph.__T();
        System.out.println(graph.order.toString());

        in.close();
        out.close();

    }

}
