package Lab1;

import java.io.*;
import java.util.Vector;

/**
 * Created by Николай on 11.03.2017.
 */
public class taskD {

    private static int n;
    private static Vector<Integer>[] graph;
    private static Vector<Integer> comp;
    private static boolean[] visited;

    /**
     * Depth-first search
     * @param v
     */
    private static void dfs (int v)
    {
        visited[v] = true;
        comp.add(v);
        for(int i=0; i<graph[v].size(); i++){
            int to = graph[v].get(i);
            if (!visited[to])
                dfs(to);
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

        BufferedReader in = new BufferedReader(new FileReader("src/Lab1/components.in"));
        BufferedWriter out = new BufferedWriter(new FileWriter("src/Lab1/components.out"));

        String[] str;
        int a, b;

        str = in.readLine().split("[ ]");
        n = Integer.parseInt(str[0]);
        int m = Integer.parseInt(str[1]);

        graph = (Vector<Integer>[]) new Vector[n];
        for(int i = 0; i < graph.length; i++)
            graph[i] = new Vector<Integer>();

        comp = new Vector(n);
        visited = new boolean[n];

        for (int i = 0; i < m; i++) {
            str = in.readLine().split("[ ]");
            a = Integer.parseInt(str[0]) - 1;
            b = Integer.parseInt(str[1]) - 1;
            graph[a].add(b);
            graph[b].add(a);
        }

        //print_graph();

        int[] res = new int[n];
        int counter = 1;
        for (int i = 0; i < n; i++) {
            if (!visited[i]){
                comp.clear();
                dfs(i);
                for (Integer aComp : comp) res[aComp] = counter;
                counter++;
            }
        }

        //System.out.print(counter-1 + "\n");
        out.write(counter-1 + "\n");
        for (int i = 0; i < n; i++) {
            //System.out.print(res[i] + " ");
            out.write(res[i] + " ");
        }

        in.close();
        out.close();

    }

}
