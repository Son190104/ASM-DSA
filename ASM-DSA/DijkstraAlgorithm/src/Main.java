import java.util.*;

class DijkstraAlgorithm {
    static class Edge {
        int target, weight;
        Edge(int target, int weight) {
            this.target = target;
            this.weight = weight;
        }
    }

    public static void dijkstra(Map<Integer, List<Edge>> graph, int source) {
        int[] distances = new int[graph.size()];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[source] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.add(new int[]{source, 0});

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int node = current[0];
            int currentDistance = current[1];

            if (currentDistance > distances[node]) continue;

            for (Edge edge : graph.get(node)) {
                int newDist = currentDistance + edge.weight;
                if (newDist < distances[edge.target]) {
                    distances[edge.target] = newDist;
                    pq.add(new int[]{edge.target, newDist});
                }
            }
        }

        // Print shortest distances
        System.out.println("Shortest distances from source " + source + ":");
        for (int i = 0; i < distances.length; i++) {
            System.out.println("To node " + i + ": " + distances[i]);
        }
    }

    public static void main(String[] args) {
        // Create a graph
        Map<Integer, List<Edge>> graph = new HashMap<>();
        for (int i = 0; i < 5; i++) {
            graph.put(i, new ArrayList<>());
        }

        // Add edges
        graph.get(0).add(new Edge(1, 1));
        graph.get(0).add(new Edge(2, 4));
        graph.get(1).add(new Edge(2, 2));
        graph.get(1).add(new Edge(3, 6));
        graph.get(2).add(new Edge(3, 3));
        graph.get(3).add(new Edge(4, 2));

        // Run Dijkstra's Algorithm
        dijkstra(graph, 0);
    }
}
