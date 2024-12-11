import java.util.*;

class PrimJarnikAlgorithm {

    static class Edge {
        int target, weight;
        Edge(int target, int weight) {
            this.target = target;
            this.weight = weight;
        }
    }

    public static void prim(Map<Integer, List<Edge>> graph, int start) {
        int[] key = new int[graph.size()];
        Arrays.fill(key, Integer.MAX_VALUE); // Initialize all keys to infinity
        key[start] = 0;

        boolean[] inMST = new boolean[graph.size()]; // Track if a node is in MST
        int[] parent = new int[graph.size()]; // To store the MST structure
        Arrays.fill(parent, -1); // Initially no parent nodes

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.add(new int[]{start, 0}); // Start from the 'start' vertex with 0 weight

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int u = current[0];

            if (inMST[u]) continue; // Skip if node u is already in MST
            inMST[u] = true; // Mark u as included in MST

            // Explore all the neighbors of u
            for (Edge edge : graph.get(u)) {
                int v = edge.target;
                int weight = edge.weight;

                // If v is not in MST and weight is less than current key[v]
                if (!inMST[v] && weight < key[v]) {
                    key[v] = weight;
                    pq.add(new int[]{v, key[v]});
                    parent[v] = u; // Set u as the parent of v
                }
            }
        }

        // Output the MST
        System.out.println("Edge \tWeight");
        for (int i = 1; i < graph.size(); i++) {
            System.out.println(parent[i] + " - " + i + "\t" + key[i]);
        }
    }

    public static void main(String[] args) {
        // Create a graph
        Map<Integer, List<Edge>> graph = new HashMap<>();
        for (int i = 0; i < 5; i++) {
            graph.put(i, new ArrayList<>());
        }

        // Add edges (node1 -> node2 with weight)
        graph.get(0).add(new Edge(1, 2));
        graph.get(0).add(new Edge(3, 6));
        graph.get(1).add(new Edge(0, 2));
        graph.get(1).add(new Edge(2, 3));
        graph.get(1).add(new Edge(3, 8));
        graph.get(2).add(new Edge(1, 3));
        graph.get(2).add(new Edge(3, 7));
        graph.get(3).add(new Edge(0, 6));
        graph.get(3).add(new Edge(1, 8));
        graph.get(3).add(new Edge(2, 7));

        // Run Prim's Algorithm starting from vertex 0
        prim(graph, 0);
    } }
