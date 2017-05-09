package Lab4;

import javafx.util.Pair;
import java.io.*;
import java.util.*;

/**
 * Created by Николай on 25.04.2017.
 */
public class TaskC {

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("pathbgep.in"));
        PrintWriter out = new PrintWriter("pathbgep.out");

        String graphSize = in.readLine();
        StringTokenizer st = new StringTokenizer(graphSize);

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        ArrayList<ArrayList<Pair<Integer, Long>>> adj = new ArrayList<ArrayList<Pair<Integer, Long>>>(n);
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<Pair<Integer, Long>>());
        }

        int s, e;
        long weight;
        String value;

        for (int i = 0; i < m; i++) {
            value = in.readLine();
            st = new StringTokenizer(value);
            s = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());
            weight = Long.parseLong(st.nextToken());

            adj.get(s - 1).add(new Pair<>(e - 1, weight));
            adj.get(e - 1).add(new Pair<>(s - 1, weight));
        }



        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);

        dist[0] = 0;
        Comparator<Pair<Integer, Long>> pair_comparator = new Comparator<Pair<Integer, Long>>() {
            @Override
            public int compare(Pair<Integer, Long> o1, Pair<Integer, Long> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        };

        PriorityQueue<Pair<Integer, Long>> queue = new PriorityQueue<Pair<Integer, Long>>(pair_comparator);

        queue.add(new Pair<Integer, Long>(0, (long) 0));

        Pair<Integer, Long> vertexWeight, to;

        while (queue.size() != 0) {

            vertexWeight = queue.poll();

            if (vertexWeight.getValue() > dist[vertexWeight.getKey()]) continue;

            for (int j = 0; j < adj.get(vertexWeight.getKey()).size(); j++) {
                to = adj.get(vertexWeight.getKey()).get(j);
                if (dist[vertexWeight.getKey()] + to.getValue() < dist[to.getKey()]) {
                    dist[to.getKey()] = dist[vertexWeight.getKey()] + to.getValue();
                    queue.add(new Pair(to.getKey(), dist[to.getKey()]));
                }
            }

        }

        for (int i = 0; i < n; i++) {
            out.print(dist[i] + " ");
        }

        out.close();
        in.close();

    }
}
