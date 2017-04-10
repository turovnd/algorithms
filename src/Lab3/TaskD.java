package Lab3;

import java.io.*;

import static java.lang.Math.random;

/**
 * Created by Николай on 10.04.2017
 */
public class TaskD {

    private static class Rib {
        int begin;
        int end;
        int weight;

        Rib (int b, int e, int w) {
            this.begin = b;
            this.end = e;
            this.weight = w;
        }
    }

    private static class Graph {
        int ver;
        int rib;
        Rib[] ribs;
        int[] parent;

        Graph(int ver, int rib) {
            this.ver = ver;
            this.rib = rib;
            this.ribs = new Rib[rib];
            this.parent = new int[ver];
            for (int i = 0; i < ver; i++) {
                this.parent[i] = i;
            }
        }

        void sort(int first, int last) {
            int i = first, j = last;
            Rib x = ribs[(first + last) >> 1];
            while (i <= j) {
                while (ribs[i].weight < x.weight) i++;
                while (ribs[j].weight > x.weight) j--;

                if(i <= j) {
                    if (ribs[i].weight > ribs[j].weight) {
                        Rib temp = ribs[i];
                        ribs[i] = ribs[j];
                        ribs[j] = temp;
                    }
                    i++;
                    j--;
                }

            }

            if (i < last)
                sort(i, last);
            if (first < j)
                sort(first,j);
        }

        int find_set (int v) {
            if (v == parent[v])
                return v;

            return find_set(parent[v]);
        }

        void union_set (int a, int b) {
            a = find_set(a);
            b = find_set(b);

            if ((random() % 2) == 0) {
                int t = a;
                a = b;
                b = t;
            }

            if (a != b) {
                parent[a] = b;
            }
        }

    }

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("src/Lab3/spantree2.in"));
        BufferedWriter out = new BufferedWriter(new FileWriter("src/Lab3/spantree2.out"));

        String[] str = in.readLine().split(" ");

        int n = Integer.parseInt(str[0]);
        int m = Integer.parseInt(str[1]);

        Graph graph = new Graph(n, m);

        int b, e, w;

        for (int i = 0; i < m; i++) {
            str = in.readLine().split(" ");
            b = Integer.parseInt(str[0]) - 1;
            e = Integer.parseInt(str[1]) - 1;
            w = Integer.parseInt(str[2]);

            graph.ribs[i] = new Rib(b, e, w);
        }

        graph.sort(0, m);

        int res = 0;
        for (int i = 0; i < m; i++) {
            b = graph.ribs[i].begin;
            e = graph.ribs[i].end;
            w = graph.ribs[i].weight;

            if (graph.find_set(b) != graph.find_set(e)) {
                res += w;
                graph.union_set (b, e);
            }
        }


        System.out.println(Integer.toString(res));
        out.write(Integer.toString(res));

        in.close();
        out.close();

    }
}
