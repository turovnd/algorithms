package Lab3;

import java.io.*;
import javafx.util.Pair;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by Николай on 10.04.2017
 */
public class TaskC {

    private static class Graph {
        int vertex;
        int edges;
        ArrayList<ArrayList<Pair<Integer, Integer>>> adj;

        Graph(int vertex, int edges) {
            this.vertex = vertex;
            this.edges = edges;
            adj = new ArrayList<>(vertex);
            for (int i = 0; i < vertex; i++) {
                adj.add(new ArrayList<Pair<Integer, Integer>>());
            }
        }
    }

    private static int mstPrim(Graph graph) {
        
        int result = 0;
        boolean[] used = new boolean[graph.vertex];
        int[] key = new int[graph.vertex];
        int[] parent = new int[graph.vertex];
        
        Comparator<Pair<Integer, Integer>> Pair_Comparator = new Comparator<Pair<Integer, Integer>>() {
            @Override
            public int compare(Pair<Integer, Integer> o1, Pair<Integer, Integer> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        };
        
        PriorityQueue<Pair<Integer, Integer>> queue = new PriorityQueue<Pair<Integer, Integer>>(Pair_Comparator);
        
        key[0] = 0;
        parent[0] = -1;
        queue.add(new Pair<>(0, 0));
        
        for (int i = 1; i < graph.vertex; i++) {
            key[i] = Integer.MAX_VALUE;
            parent[i] = -1;
            queue.add(new Pair<>(i, key[i]));
        }
        
        Pair<Integer, Integer> weight,ver;
        
        while (queue.size() != 0) {

            weight = queue.poll();
            if (weight.getValue() != key[weight.getKey()]) continue;
            used[weight.getKey()] = true;

            result += weight.getValue();

            for (int i = 0; i < graph.adj.get(weight.getKey()).size(); i++) {
                ver = graph.adj.get(weight.getKey()).get(i);
                if ((!used[ver.getKey()]) && (ver.getValue() < key[ver.getKey()]))  {
                    key[ver.getKey()] = ver.getValue();
                    parent[ver.getKey()] = weight.getKey();
                    queue.add(new Pair(ver.getKey(), key[ver.getKey()]));
                }
            }

        }

        return result;
    }


    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("src/Lab3/spantree2.in"));
        BufferedWriter out = new BufferedWriter(new FileWriter("src/Lab3/spantree2.out"));

        String[] str = in.readLine().split("\\s");

        int n = Integer.parseInt(str[0]);
        int m = Integer.parseInt(str[1]);

        Graph graph = new Graph(n, m);

        Pair<Integer, Integer> pair1, pair2;
        int b, e, w;

        for (int i = 0; i < m; i++) {
            str = in.readLine().split("\\s");
            b = Integer.parseInt(str[0]) - 1;
            e = Integer.parseInt(str[1]) - 1;
            w = Integer.parseInt(str[2]);
            pair1 = new Pair<>(e, w);
            pair2 = new Pair<>(b, w);

            graph.adj.get(b).add(pair1);
            graph.adj.get(e).add(pair2);
        }


        int result = mstPrim(graph);

        //System.out.println(Integer.toString(result));
        out.write(Integer.toString(result));

        in.close();
        out.close();

    }

}
