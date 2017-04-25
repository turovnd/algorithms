package Lab4;

import javafx.util.Pair;
import java.io.*;
import java.util.*;

/**
 * Created by Николай on 25.04.2017.
 */
public class testC {

        private ArrayList<ArrayList<Pair<Integer, Long>>> arrayAdjacency;

        private int countVertices, countEdges;
        long[] dist;


        public static void main(String[] args) throws IOException {
            new testC().resolve();
        }

        public void resolve() throws IOException {
            enterData();
            outputData();
        }

        public void enterData() throws IOException {
            BufferedReader in = new BufferedReader(new FileReader("pathbgep.in"));

            String graphSize = in.readLine();
            StringTokenizer st = new StringTokenizer(graphSize);

            countVertices = Integer.parseInt(st.nextToken());
            countEdges = Integer.parseInt(st.nextToken());

            arrayAdjacency = new ArrayList<ArrayList<Pair<Integer, Long>>>(countVertices);
            for (int i = 0; i < countVertices; i++) {
                arrayAdjacency.add(new ArrayList<Pair<Integer, Long>>());
            }

            int originVertex, targetVertex;
            long weight;
            Pair<Integer, Long> pair1, pair2;
            String value;
            for (int i = 0; i < countEdges; i++) {
                value = in.readLine();
                st = new StringTokenizer(value);
                originVertex = Integer.parseInt(st.nextToken());
                targetVertex = Integer.parseInt(st.nextToken());
                weight = Long.parseLong(st.nextToken());
                pair1 = new Pair<>(targetVertex - 1, weight);
                pair2 = new Pair<>(originVertex - 1, weight);
                arrayAdjacency.get(originVertex - 1).add(pair1);
                arrayAdjacency.get(targetVertex - 1).add(pair2);
            }
            in.close();
        }

        public void outputData() throws FileNotFoundException {
            PrintWriter out = new PrintWriter("pathbgep.out");
            findMinPath();
            for (int i = 0; i < countVertices; i++) {
                out.print(dist[i] + " ");
            }
            out.close();
        }

        public void findMinPath() {
            dist = new long[countVertices];
            Arrays.fill(dist, Long.MAX_VALUE);
            dist[0] = 0;
            Comparator<Pair<Integer, Long>> pairComparator = new Comparator<Pair<Integer, Long>>() {
                @Override
                public int compare(Pair<Integer, Long> o1, Pair<Integer, Long> o2) {
                    return o1.getValue().compareTo(o2.getValue());
                }
            };
            PriorityQueue<Pair<Integer, Long>> queue = new PriorityQueue<Pair<Integer, Long>>(pairComparator);
            queue.add(new Pair<Integer, Long>(0, (long) 0));
            Pair<Integer, Long> vertexWeight, to;
            while (queue.size() != 0) {
                vertexWeight = queue.poll();
                if (vertexWeight.getValue() > dist[vertexWeight.getKey()]) continue;

                for (int j = 0; j < arrayAdjacency.get(vertexWeight.getKey()).size(); j++) {
                    to = arrayAdjacency.get(vertexWeight.getKey()).get(j);
                    if (dist[vertexWeight.getKey()] + to.getValue() < dist[to.getKey()]) {
                        dist[to.getKey()] = dist[vertexWeight.getKey()] + to.getValue();
                        queue.add(new Pair(to.getKey(), dist[to.getKey()]));
                    }
                }
            }
        }




}
