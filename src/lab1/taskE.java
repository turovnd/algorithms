package Lab1;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;

/**
 * Created by Николай on 11.03.2017.
 */
public class taskE {

    private static int n;
    private static Vector<Integer>[] graph;
    private static int[] depth;
    private static boolean[] visited;


    /**
     * Breadth-first search
     * @param v
     */
    private static void bfs (int v) {

        Queue<Integer> queue = new LinkedList();
        queue.add(v);
        visited[v] = true;

        while (!queue.isEmpty())
        {
            v = queue.peek();
            queue.poll();
            for (int i = 0; i < graph[v].size(); i++)
            {
                int to = graph[v].get(i);
                if (!visited[to])
                {
                    visited[to] = true;
                    queue.add(to);
                    depth[to] = depth[v] + 1;
                }
            }
        }
    }

    /**
     * display graph
     */
    private static void print_graph() {
        for (int i = 0; i < n; i++) {
            System.out.print(i + ":");
            for (int j = 0; j < graph[i].size(); ++j) {
                System.out.print(" " + graph[i].get(j));
            }
            System.out.println();
        }
    }


    /**
     * Main Function
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("src/Lab1/pathbge1.in"));
        BufferedWriter out = new BufferedWriter(new FileWriter("src/Lab1/pathbge1.out"));

        String[] str;
        int a, b;

        str = in.readLine().split("[ ]");
        n = Integer.parseInt(str[0]);
        int m = Integer.parseInt(str[1]);

        graph = (Vector<Integer>[]) new Vector[n];
        for(int i = 0; i < graph.length; i++)
            graph[i] = new Vector<Integer>();

        visited = new boolean[n];
        depth = new int[n];

        for (int i = 0; i < m; i++) {
            str = in.readLine().split("[ ]");
            a = Integer.parseInt(str[0]) - 1;
            b = Integer.parseInt(str[1]) - 1;
            graph[a].add(b);
            graph[b].add(a);
        }

        //print_graph();

        bfs(0);

        for (int i = 0; i < n; i++) {
            System.out.print(depth[i] + " ");
            out.write(depth[i] + " ");
        }

        in.close();
        out.close();

    }

}
